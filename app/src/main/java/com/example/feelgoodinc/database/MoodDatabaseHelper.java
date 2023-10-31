package com.example.feelgoodinc.database;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.feelgoodinc.models.Mood;
import com.example.feelgoodinc.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This handles how {@link Mood}s are stored/retrieving when communicating with
 * Firebase's cloud firestore
 *
 *
 * @see Mood
 * @see com.example.feelgoodinc.models.Mood.MoodType
 */
public class MoodDatabaseHelper {
    private FirebaseFirestore firestore;

    //TODO reference to the collection
    CollectionReference moodsRef;

    public MoodDatabaseHelper() {
        firestore = FirebaseFirestore.getInstance();
        moodsRef = firestore.collection("users").document(User.getCurrentUserKey()).collection("moods");
    }

    /**
     * This method updates {@link MoodDatabaseHelper}'s list of moods for the input month.
     *
     * @param date should be a {@link Date} within the month you are trying to get the moods for
     */
    public void getMoodsForMonth(Date date) {
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
//        firestore.collection("users").whereGreaterThanOrEqualTo("moodWhen", start).whereLessThanOrEqualTo("moodWhen", end).get().addOnCompleteListener(task -> {
        firestore.collection("users").document(User.getCurrentUserKey()).collection("moods").get().addOnCompleteListener(task -> {
             if (task.isSuccessful()) {
                 for (QueryDocumentSnapshot document : task.getResult()) {
                     Mood mood = Mood.fromMap(document.getData());
                     Log.d("Feel Good Inc", document.getId() + " => " + document.getData());
//                     results.add(Mood.fromMap(document.get));

                     //TODO: make a fromMap method for mood
                 }
             } else {
                 Log.d("Feel Good Inc", "Error getting documents: ", task.getException());
             }
         });


    }

    /**
     * puts a new {@link Mood} into Firebase
     * @param mood
     * @param activity
     */
    public void addNewMood(Mood mood, Activity activity) {
        //TODO: determine what kind of error handling should go here
        moodsRef.add(mood.toMap()).addOnSuccessListener(
                        documentReference -> Toast.makeText(activity.getApplicationContext(), "Success", Toast.LENGTH_LONG).show())
                .addOnFailureListener(e ->
                        Toast.makeText(activity.getApplicationContext(), "Failure", Toast.LENGTH_LONG).show()
                );
    }


}
