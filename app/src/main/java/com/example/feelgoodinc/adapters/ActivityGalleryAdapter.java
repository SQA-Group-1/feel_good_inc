package com.example.feelgoodinc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feelgoodinc.R;
import com.example.feelgoodinc.activityRecyclerView.ActivityData;
import com.example.feelgoodinc.activityRecyclerView.ActivityViewHolder;
import com.example.feelgoodinc.activityRecyclerView.ClickListener;

import java.util.List;

public class ActivityGalleryAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    List<ActivityData> list;
    Context context;
    ClickListener listener;
    public ActivityGalleryAdapter(List<ActivityData> list, Context context, ClickListener listener){
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
        viewHolder.activityPicture.setImageDrawable(list.get(position).imagePath);
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
