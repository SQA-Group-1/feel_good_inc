package com.example.feelgoodinc.models;

import java.util.Date;

/**
 * This is a model class, containg the mood of the User
 */
public class Mood {

    /// this enum encapsulates the possible moods
    enum MoodType {
        RAD,
        GOOD,
        MEH,
        BAD,
        AWFUL
    }

    private MoodType moodType;
    private Date moodWhen;

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
}
