package com.example.pc.alarmclock.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.alarmclock.R;
import com.example.pc.alarmclock.adapter.ViewPagerAdapter;
import com.example.pc.alarmclock.fragment.SceneFragment;
import com.example.pc.alarmclock.fragment.CalendarFragment;
import com.example.pc.alarmclock.fragment.TimeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView navBottom;
    private ViewPager pager;
    private MenuItem prevMenuItem;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navView;
    private ImageView imNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupBottomNavigation();
        setDrawerLayout();

    }

    private void initView() {
        navBottom = findViewById(R.id.nav_bot);
        pager = findViewById(R.id.pager);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        imNav = findViewById(R.id.imNav);
        imNav.setOnClickListener(lsClick);
    }


    private void setDrawerLayout() {
        mToggle = new ActionBarDrawerToggle(this, drawer, null, 0, 0);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();
        navView.setNavigationItemSelectedListener(this);
//        nav.setItemIconTintList(null);
        mToggle.syncState();
    }


    private void setupBottomNavigation() {
        navBottom.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.imAlarm:
                                pager.setCurrentItem(0);
                                return true;
                            case R.id.imCalendar:
                                pager.setCurrentItem(1);
                                return true;
                            case R.id.imScene:
                                pager.setCurrentItem(2);
                                return true;

                        }
                        return false;
                    }
                });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navBottom.getMenu().getItem(0).setChecked(false);
                }
                navBottom.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navBottom.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(pager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimeFragment());
        adapter.addFragment(new CalendarFragment());
        adapter.addFragment(new SceneFragment());
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
    }

    private View.OnClickListener lsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         switch (v.getId()){
             case R.id.imNav:
                 drawer.openDrawer(GravityCompat.START);
                 break;
         }
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.imTime:
                Intent intent1 = new Intent(MainActivity.this,TimerActivity.class);
                startActivity(intent1);
                break;
            case R.id.imStopwatch:
                Intent intent2 = new Intent(MainActivity.this,StopWatchActivity.class);
                startActivity(intent2);
                break;
            case R.id.imNight:
                Intent intent3 = new Intent(MainActivity.this,BesideNightActivity.class);
                startActivity(intent3);
                break;
            case R.id.imRate:
                break;
            case R.id.imPolicy:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
