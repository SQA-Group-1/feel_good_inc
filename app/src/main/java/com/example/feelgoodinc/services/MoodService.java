package com.example.feelgoodinc.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.Mood;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Accesses the firebase database to recieve and add user data </p>
 * <br>
 * <p>The firebase database has a collection of users, with each user having a seperate
 * {@link Mood}, so that users cannot access each other's {@link Mood}s. </p>
 *
 * <p>This can be used to add/retrieve the Moods.</p>
 * <br>
 * Usage example:
 *
 * <pre>
 *     // this is in an Activity/Fragment class
 *    private final ServiceConnection serviceConnection = new ServiceConnection() {
 *      //@Override
 *      public void onServiceConnected(ComponentName name, IBinder binder {
 *
 *          MoodService.LocalBinder binder (MoodService.LocalBinder) service;
 *          //journalService is a member variable of {@link MoodService}
 *          journalService = binder.getService();
 *          // isBound is a member variable, to make sure journalService isn't used before it's bound
 *          isBound = true;
 *
 *      }
 *
 *       //@Override
 *       public void onServiceDisconnected(ComponentName name) {
 *            isBound = false;
 *       }
 *    }
 *        <br>
 *     // @Override
 *     public void onStart() {
 *         super.onStart();
 *         Intent intent = new Intent(this, UserService.class);
 *         bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
 *     }
 *
 *     // @Override
 *     public void onCreate() {
 *     // add a new journal
 *         journalService.addNewMood(journal, this);
 *
 *     // get all the existing journals
 *         journalService.getMoodsForMonth(new Date(2023, 11, 05));
 *     }
 *
 * </pre>
 *
 */
public class MoodService extends Service {
    // instance of the Firebase Firestore (DB) to connect to
    private FirebaseFirestore firestore;
    // directory in the DB, where the collections will be stored
    CollectionReference moodsRef;
    // Binder object that others connect to
    private final IBinder binder = new MoodService.LocalBinder();

    /***
     * bind an {@link Activity} or {@link Fragment} (or multiple) to this {@link Service}
     */
    public class LocalBinder extends Binder {
        public MoodService getService() {
            return MoodService.this;
        }
    }

    public MoodService() {
        firestore = FirebaseFirestore.getInstance();
        moodsRef = firestore.collection("users").document(UserService.getCurrentUserKey()).collection("moods");
    }

    public interface OnMoodsFetchedListener {
        void onMoodsFetched(List<Mood> moods);
    }

    /**
     * This method updates a list of {@link Mood}s for the input month.
     *
     * @param date should be a {@link Date} within the month you are trying to get the moods for
     */
    public List<Mood> getMoodsForMonth(Date date, OnMoodsFetchedListener listener) {
        ArrayList<Mood> results = new ArrayList<>();
        if (moodsRef == null) {
            listener.onMoodsFetched(results);
        }
        // get the start of the month epoch
        // get the end of the month epoch
        ZoneId zone = ZoneId.of("Europe/London"); //FIXME: might regret defaulting to London time
        ZonedDateTime dateTime = date.toInstant().atZone(zone);
        YearMonth yearMonth = YearMonth.of(dateTime.getYear(), dateTime.getMonth());

        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        long start = startOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long end = endOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        // make a FB query for everything between the epochs
        moodsRef.whereGreaterThanOrEqualTo("moodWhen", start).whereLessThanOrEqualTo("moodWhen", end).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    if(document.exists()){
                        Mood mood = Mood.fromMap(document.getData());
                        Log.d("Feel Good Inc", document.getId() + " => " + document.getData());
                        results.add(mood);
                    }
                }
                listener.onMoodsFetched(results);
            } else {
                Log.d("COMP3013", "Error getting documents");
                listener.onMoodsFetched(results);
            }
        });

        return results;
    }

    /**
     * puts a new {@link Mood} into Firebase
     * @param mood the {@link Mood} object to add to the database
     * @param activity is the {@link Activity} adding this to the database
     */
    public void addNewMood(Mood mood, Activity activity) {
        //TODO: determine what kind of error handling should go here
        moodsRef.add(mood.toMap()).addOnSuccessListener(
                        documentReference -> Toast.makeText(activity.getApplicationContext(), "Success", Toast.LENGTH_LONG).show())
                .addOnFailureListener(e ->
                        Toast.makeText(activity.getApplicationContext(), "Failure", Toast.LENGTH_LONG).show()
                );
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}