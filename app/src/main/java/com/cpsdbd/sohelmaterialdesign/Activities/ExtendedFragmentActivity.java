package com.cpsdbd.sohelmaterialdesign.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.andexert.library.RippleView;
import com.cpsdbd.sohelmaterialdesign.Fragments.FragmentA;
import com.cpsdbd.sohelmaterialdesign.Fragments.FragmentB;
import com.cpsdbd.sohelmaterialdesign.R;
import com.desarrollodroide.libraryfragmenttransactionextended.FragmentTransactionExtended;

public class ExtendedFragmentActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener{

    private FragmentA fragmentA;

    private Spinner mySpinner;
    private Button btnPush;

    private int optionSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_fragment);

        setupToolbar();

        initView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.transition_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);

        if(savedInstanceState==null){
            fragmentA = new FragmentA();
            getFragmentManager().beginTransaction().add(R.id.fragment_place,fragmentA).commit();

        }
    }

    private void initView() {
        btnPush = findViewById(R.id.btn_push);
        btnPush.setOnClickListener(this);
        mySpinner = findViewById(R.id.spinner);

    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle("Extended Fragment Test");
    }

    @Override
    public void onClick(View view) {

        if (getFragmentManager().getBackStackEntryCount()==0) {
            Fragment secondFragment = new FragmentB();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(this, fragmentTransaction, fragmentA, secondFragment, R.id.fragment_place);
            fragmentTransactionExtended.addTransition(optionSelected);
            fragmentTransactionExtended.commit();
            btnPush.setText("Back");
        }else{
            getFragmentManager().popBackStack();
            btnPush.setText("Push");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        optionSelected = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
