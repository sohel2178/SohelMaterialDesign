package com.cpsdbd.sohelmaterialdesign.Activities;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.cpsdbd.sohelmaterialdesign.Adapter.MainPagerAdapter;
import com.cpsdbd.sohelmaterialdesign.CustomView.Fab;
import com.cpsdbd.sohelmaterialdesign.R;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

public class FabSheetActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private MaterialSheetFab materialSheetFab;
    private int statusBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_sheet);

        setTitle(R.string.notes);
        setupActionBar();
        setupDrawer();
        setupFab();
        setupTabs();
    }

    private void setupActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer,
                R.string.closedrawer);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setupTabs() {
        // Setup view pager
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new MainPagerAdapter(this, getSupportFragmentManager()));
        viewpager.setOffscreenPageLimit(MainPagerAdapter.NUM_ITEMS);
        updatePage(viewpager.getCurrentItem());

        // Setup tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                updatePage(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void updatePage(int selectedPage) {
        updateFab(selectedPage);
        updateSnackbar(selectedPage);
    }

    /**
     * Updates the FAB based on the selected page
     *
     * @param selectedPage selected page
     */
    private void updateFab(int selectedPage) {
        switch (selectedPage) {
            case MainPagerAdapter.ALL_POS:
                materialSheetFab.showFab();
                break;
            case MainPagerAdapter.SHARED_POS:
                materialSheetFab.showFab(0,
                        -getResources().getDimensionPixelSize(R.dimen.snackbar_height));
                break;
            case MainPagerAdapter.FAVORITES_POS:
            default:
                materialSheetFab.hideSheetThenFab();
                break;
        }
    }

    /**
     * Updates the snackbar based on the selected page
     *
     * @param selectedPage selected page
     */
    private void updateSnackbar(int selectedPage) {
        View snackbar = findViewById(R.id.snackbar);
        switch (selectedPage) {
            case MainPagerAdapter.SHARED_POS:
                snackbar.setVisibility(View.VISIBLE);
                break;
            case MainPagerAdapter.ALL_POS:
            case MainPagerAdapter.FAVORITES_POS:
            default:
                snackbar.setVisibility(View.GONE);
                break;
        }
    }


    /**
     * Sets up the Floating action button.
     */
    private void setupFab() {

        Fab fab = (Fab) findViewById(R.id.fab);
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.overlay);
        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.colorAccent);

        // Create material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);

        // Set material sheet event listener
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                // Save current status bar color
                statusBarColor = getStatusBarColor();
                // Set darker status bar color to match the dim overlay
                setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }

            @Override
            public void onHideSheet() {
                // Restore status bar color
                setStatusBarColor(statusBarColor);
            }
        });

        // Set material sheet item click listeners
        findViewById(R.id.fab_sheet_item_recording).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_reminder).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_photo).setOnClickListener(this);
        findViewById(R.id.fab_sheet_item_note).setOnClickListener(this);
    }

    private int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, R.string.sheet_item_pressed, Toast.LENGTH_SHORT).show();
        materialSheetFab.hideSheet();

    }
}
