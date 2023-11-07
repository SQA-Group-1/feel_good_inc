package com.example.feelgoodinc.activityRecyclerView;

public class ActivityData {
    public String activityTitle;
    public String description;
    public int estimatedTime;
    public int imagePath;


    public ActivityData(String title,String description,int estimatedTime, int imageID){
        this.activityTitle = title;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.imagePath = imageID;
    }
}
