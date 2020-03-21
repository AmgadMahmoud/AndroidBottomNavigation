package com.example.androidbottomnavigation.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.androidbottomnavigation.Fragments.HomeFragment;
import com.example.androidbottomnavigation.Fragments.MoreFragment;
import com.example.androidbottomnavigation.Fragments.MyBookingsFragment;
import com.example.androidbottomnavigation.Fragments.MyOrdersFragment;
import com.example.androidbottomnavigation.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private DrawerLayout drawer;

    private HomeFragment homeFragment;
    private MyOrdersFragment myOrdersFragment;
    private MyBookingsFragment myBookingsFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.bottom_bar_menu);
        drawer = findViewById(R.id.drawer_layout);

        homeFragment = new HomeFragment();
        myOrdersFragment = new MyOrdersFragment();
        myBookingsFragment = new MyBookingsFragment();
        moreFragment = new MoreFragment();

        replaceFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_bottom_home:
                        mMainNav.setItemBackgroundResource(R.color.design_default_color_primary);
                        replaceFragment(homeFragment);
                        return true;
                    case R.id.nav_bottom_orders:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        replaceFragment(myOrdersFragment);
                        return true;
                    case R.id.nav_bottom_bookings:
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        replaceFragment(myBookingsFragment);
                        return true;
                    case R.id.nav_bottom_more:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        drawer.openDrawer(GravityCompat.START);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
