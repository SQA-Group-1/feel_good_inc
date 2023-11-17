package com.example.feelgoodinc.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.feelgoodinc.database.MoodDatabaseHelper;
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

public class MoodService extends Service {
    // instance of the Firebase Firestore (DB) to connect to
    private FirebaseFirestore firestore;
    // directory in the DB, where the collections will be stored
    CollectionReference moodsRef;
    // Binder object that others connect to
    private final IBinder binder = new MoodService.LocalBinder();

    public class LocalBinder extends Binder {
        public MoodService getService() {
            return MoodService.this;
        }
    }

    public MoodService() {
        firestore = FirebaseFirestore.getInstance();
        moodsRef = firestore.collection("users").document(UserService.getCurrentUserKey()).collection("moods");
    }

    /**
     * This method updates {@link MoodDatabaseHelper}'s list of moods for the input month.
     *
     * @param date should be a {@link Date} within the month you are trying to get the moods for
     */
    public List<Mood> getMoodsForMonth(Date date) {
        ArrayList<Mood> results = new ArrayList<>();

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
                    Mood mood = Mood.fromMap(document.getData());
                    Log.d("Feel Good Inc", document.getId() + " => " + document.getData());
                    results.add(mood);
                }
            } else {
                Log.d("COMP3013", "Error getting documents");
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