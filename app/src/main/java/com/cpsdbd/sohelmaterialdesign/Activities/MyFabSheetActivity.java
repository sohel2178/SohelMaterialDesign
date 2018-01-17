package com.cpsdbd.sohelmaterialdesign.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cpsdbd.sohelmaterialdesign.CustomView.Fab;
import com.cpsdbd.sohelmaterialdesign.R;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

public class MyFabSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialSheetFab materialSheetFab;

    private LinearLayout lltt1,lltt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fab_sheet);

        Fab fab = (Fab) findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.colorPrimary);
        int fabColor = getResources().getColor(R.color.colorPrimaryDark);

        // Initialize material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay,
                sheetColor, fabColor);


        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Called when the material sheet's "show" animation starts.

                Log.d("TTT","onShowSheet Called");
            }

            @Override
            public void onSheetShown() {
                // Called when the material sheet's "show" animation ends.
                Log.d("TTT","onSheetShown Called");
            }

            @Override
            public void onHideSheet() {
                // Called when the material sheet's "hide" animation starts.
                Log.d("TTT","onHideSheet Called");
            }

            public void onSheetHidden() {
                // Called when the material sheet's "hide" animation ends.
                Log.d("TTT","onSheetHidden Called");
            }
        });

        lltt1 = findViewById(R.id.tt1);
        lltt2 = findViewById(R.id.tt2);

        lltt1.setOnClickListener(this);
        lltt2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tt1:
                Toast.makeText(this, "Click Item 1", Toast.LENGTH_SHORT).show();
                materialSheetFab.hideSheet();
                break;

            case R.id.tt2:
                Toast.makeText(this, "Click Item 2", Toast.LENGTH_SHORT).show();
                materialSheetFab.hideSheet();
                break;
        }
    }
}
