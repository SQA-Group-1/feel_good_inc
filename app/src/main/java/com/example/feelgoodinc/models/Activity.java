package com.example.feelgoodinc.models;

public class Activity {
    public String activityTitle;
    public String description;
    public int estimatedTime;
    public int imageID;


    /**
     * The Activity class holds the data for the ActivityRecyclerView
     * @param title this is the title of the activity
     * @param description this is the description of the activity (i.e. what it involves)
     * @param estimatedTime estimated time is in minutes and represents how long the activity should take to complete
     * @param imageID the image ID is the drawable ID of the image displayed for the activity, images should be stored in the drawable resource folder
     */
    public Activity(String title, String description, int estimatedTime, int imageID){
        this.activityTitle = title;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.imageID = imageID;
    }
}
