package com.cpsdbd.sohelmaterialdesign;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cpsdbd.sohelmaterialdesign.Activities.BaseActivity;
import com.cpsdbd.sohelmaterialdesign.Activities.ExtendedFragmentActivity;
import com.cpsdbd.sohelmaterialdesign.Activities.FabSheetActivity;
import com.cpsdbd.sohelmaterialdesign.Activities.MyFabSheetActivity;
import com.cpsdbd.sohelmaterialdesign.Activities.TapTargetActivity;
import com.cpsdbd.sohelmaterialdesign.Adapter.ActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView rvMatDesign;

    private List<Activity> activityList;

    private ActivityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityList = new ArrayList<>();
        activityList.add(new ExtendedFragmentActivity());
        activityList.add(new FabSheetActivity());
        activityList.add(new MyFabSheetActivity());
        activityList.add(new TapTargetActivity());


        adapter = new ActivityAdapter(getApplicationContext());

        for(Activity x: activityList){
            adapter.addActivity(x);
        }




        setupToolbar();

        rvMatDesign = findViewById(R.id.rv_activity_list);
        rvMatDesign.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvMatDesign.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("My Material Design List");
    }
}
