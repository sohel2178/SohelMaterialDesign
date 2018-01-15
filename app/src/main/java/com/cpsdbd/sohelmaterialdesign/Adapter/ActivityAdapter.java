package com.cpsdbd.sohelmaterialdesign.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.cpsdbd.sohelmaterialdesign.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Genius 03 on 1/15/2018.
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityHolder>{
    private Context context;
    private List<Activity> activities;
    private LayoutInflater inflater;

    public ActivityAdapter(Context context) {
        this.context = context;
        activities = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_activity,parent,false);

        ActivityHolder holder = new ActivityHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ActivityHolder holder, int position) {

        Activity activity = activities.get(position);

        holder.tvName.setText(activity.getClass().getSimpleName());

    }

    public void addActivity(Activity activity){
        activities.add(activity);
        int pos = activities.indexOf(activity);
        notifyItemInserted(pos);
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }


    public class ActivityHolder extends RecyclerView.ViewHolder implements RippleView.OnRippleCompleteListener{
        TextView tvName;
        RippleView rippleView;

        public ActivityHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.activity_name);
            rippleView = itemView.findViewById(R.id.rippleView);

            rippleView.setOnRippleCompleteListener(this);

        }

        @Override
        public void onComplete(RippleView rippleView) {
            Activity activity = activities.get(getAdapterPosition());

            context.startActivity(new Intent(context,activity.getClass()));
        }
    }
}
