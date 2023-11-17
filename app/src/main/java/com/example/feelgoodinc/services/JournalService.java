package com.example.feelgoodinc.services;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.feelgoodinc.models.Journal;
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

import com.example.feelgoodinc.models.User;

public class JournalService extends Service {

    public static final String TAG = "COMP3013";
    private FirebaseFirestore firestore;
    CollectionReference journalsRef;

    // Binder object that others connect to
    private final IBinder binder = new JournalService.LocalBinder();

    public class LocalBinder extends Binder {
        public JournalService getService() {
            return JournalService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Created");
        super.onCreate();
        firestore = FirebaseFirestore.getInstance();

        // point to which directory the journals should be placed in the cloud firestore
        journalsRef = firestore.collection("users").document(UserService.getCurrentUserKey()).collection("journals");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * reads in all the {@link} Journal for the input month
     * @param date should be a {@link Date} within the month you are trying to get the moods for
     * @return a list of {@link Journal} showing all user journals for the current month
     */
    public List<Journal> getJournalsForMonth(Date date) {
        ArrayList<Journal> results = new ArrayList<>();

        // get epochs for start and end of month
        ZoneId zone = ZoneId.of("Europe/London"); //FIXME: might regret defaulting to London time
        ZonedDateTime dateTime = date.toInstant().atZone(zone);
        YearMonth yearMonth = YearMonth.of(dateTime.getYear(), dateTime.getMonth());

        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        long start = startOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        long end = endOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        // make a FB query for everything between the epochs
        journalsRef.whereGreaterThanOrEqualTo("createdWhen", start).whereLessThanOrEqualTo("createdWhen", end).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Journal journal = Journal.fromMap(document.getData());
                    Log.d("Feel Good Inc", document.getId() + " => " + document.getData());
                    results.add(journal);
                }
            } else {
                Log.d("Feel Good Inc", "Error getting documents: ", task.getException());
            }
        });
        return results;
    }

    /**
     * puts a new {@link Journal} into Firebase
     * @param journal a {@link Journal} to be added to Firebase DB
     * @param activity the currently showing {@link Activity} to make a toast if adding is sucessful
     */
    public void addNewJournal(Journal journal, Activity activity) {
        //TODO: determine what kind of error handling should go here
        journalsRef.add(journal.toMap()).addOnSuccessListener(
                        documentReference -> Toast.makeText(activity.getApplicationContext(), "Success", Toast.LENGTH_LONG).show())
                .addOnFailureListener(e ->
                        Toast.makeText(activity.getApplicationContext(), "Failure", Toast.LENGTH_LONG).show()
        );
    }
}