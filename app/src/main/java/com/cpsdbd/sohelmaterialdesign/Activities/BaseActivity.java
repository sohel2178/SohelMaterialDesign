package com.cpsdbd.sohelmaterialdesign.Activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.cpsdbd.sohelmaterialdesign.R;

/**
 * Created by Genius 03 on 1/15/2018.
 */

public class BaseActivity extends AppCompatActivity {
    private TextView tvTitle;

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void setTitle(String title){
        if(tvTitle!=null){
        }else {
            tvTitle = findViewById(R.id.title);
        }
        tvTitle.setText(title);
    }



}
