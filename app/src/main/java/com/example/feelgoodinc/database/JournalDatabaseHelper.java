package com.example.feelgoodinc.database;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.feelgoodinc.models.Journal;
import com.example.feelgoodinc.models.User;
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
 * This handles how {@link Journal}s are stored/retrieving when communicating with
 * Firebase's cloud firestore
 *
 *
 * @see Journal
 */
public class JournalDatabaseHelper {
    private FirebaseFirestore firestore;
    CollectionReference journalsRef;

    public JournalDatabaseHelper() {
        firestore = FirebaseFirestore.getInstance();
        journalsRef = firestore.collection("users").document(User.getCurrentUserKey()).collection("journals");
    }

    /**
     *
     * @param date should be a {@link Date} within the month you are trying to get the moods for
     * @return a list of {@link Journal} showing all user journals for the current month
     */
    public List<Journal> getJournalsForMonth(Date date) {
        ArrayList<Journal> results = new ArrayList<>();

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
