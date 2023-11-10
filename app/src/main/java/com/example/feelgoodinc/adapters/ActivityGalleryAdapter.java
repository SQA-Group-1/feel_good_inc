package com.example.feelgoodinc.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.models.Activity;
import com.example.feelgoodinc.activityRecyclerView.ActivityViewHolder;
import com.example.feelgoodinc.activityRecyclerView.ClickListener;

import java.util.List;

/**
 * This class binds the {@link Activity} data provided in a list to the {@link ActivityViewHolder} which is displayed in the recyclerView layout
 */
public class ActivityGalleryAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    List<Activity> list;
    Context context;
    ClickListener listener;
    public ActivityGalleryAdapter(List<Activity> list, Context context, ClickListener listener){
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.activity_card,parent,false);
        return new ActivityViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(final ActivityViewHolder viewHolder, final int position){
        final int index = viewHolder.getAdapterPosition();
        viewHolder.activityName.setText(list.get(position).activityTitle);
        viewHolder.description.setText(list.get(position).description);
        String estimatedTime = list.get(position).estimatedTime + " minutes";
        viewHolder.estimatedTime.setText(estimatedTime);
        Drawable drawable = ResourcesCompat.getDrawable(viewHolder.view.getResources(), list.get(position).imageID, null);
        viewHolder.activityPicture.setImageDrawable(drawable);
        viewHolder.view.setOnClickListener(v -> listener.click(index));
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
