package com.example.feelgoodinc.activityRecyclerView;

import android.graphics.drawable.Drawable;

public class ActivityData {
    public String activityTitle;
    public Drawable imagePath;

    ActivityData(String title, Drawable image){
        this.activityTitle = title;
        this.imagePath = image;
    }
}
