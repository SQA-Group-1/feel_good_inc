package com.example.feelgoodinc.models;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a model class, which contains the mood of the User
 *
 */
public class Mood {

    /// this enum encapsulates the possible moods
    public enum MoodType {
        RAD,
        GOOD,
        MEH,
        BAD,
        AWFUL;
    }

    private MoodType moodType;
    private Date moodWhen;

    public Mood(MoodType moodType, Date moodWhen) {
        this.moodType = moodType;
        this.moodWhen = moodWhen;
    }

    public MoodType getMoodType() {
        return moodType;
    }

    public void setMoodType(MoodType moodType) {
        this.moodType = moodType;
    }

    public Date getMoodWhen() {
        return moodWhen;
    }

    public void setMoodWhen(Date moodWhen) {
        this.moodWhen = moodWhen;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> moods = new HashMap<>();
        moods.put("mood", moodType);
        moods.put("moodWhen",  moodWhen.toInstant().getEpochSecond());

        return moods;
    }

    public static Mood fromMap(Map<String, Object> map) {
        return new Mood(MoodType.valueOf(map.get("mood").toString()), Date.from(Instant.ofEpochSecond((Long) map.get("moodWhen"))));


    }
}
