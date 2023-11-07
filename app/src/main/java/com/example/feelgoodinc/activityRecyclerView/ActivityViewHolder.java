package com.example.feelgoodinc.activityRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelgoodinc.R;

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    public TextView activityName;
    public TextView description;
    public TextView estimatedTime;
    public ImageView activityPicture;
    public View view;
    public ActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        activityName = (TextView) itemView.findViewById(R.id.activityTitle);
        description = itemView.findViewById(R.id.description);
        estimatedTime = itemView.findViewById(R.id.timeLeft);
        activityPicture = (ImageView) itemView.findViewById(R.id.activityPicture);
        view = itemView;
    }
}
