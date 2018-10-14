package za.ac.cut.hockeyapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import za.ac.cut.hockeyapplication.R;
import za.ac.cut.hockeyapplication.adapter.ViewPagerAdapter;
import za.ac.cut.hockeyapplication.fragment.OpponentsFragment;
import za.ac.cut.hockeyapplication.fragment.TeamsFragment;

public class TeamsOpponentsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_opponents);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_teams_opponents);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Init view pager
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new ViewPagerAdapter(getFragments(), getSupportFragmentManager()));

        // Init tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Init floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }
    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new TeamsFragment());
        fragments.add(new OpponentsFragment());
        return fragments;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TODO
    }

    @Override
    public void onPageSelected(int position) {
        // TODO
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO
    }

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, TeamsOpponentsActivity.class));
    }
}
