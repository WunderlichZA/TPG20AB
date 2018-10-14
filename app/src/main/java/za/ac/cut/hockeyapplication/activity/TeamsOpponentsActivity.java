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

    private static final int TAB_TEAMS_POSITION = 0;
    private static final int TAB_OPPONENTS_POSITION = 1;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_opponents);

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_teams_opponents);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Init view pager
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        viewPagerAdapter = new ViewPagerAdapter(getFragments(), getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        // Init tab layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // Init floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentTabPosition = viewPager.getCurrentItem();
                switch (currentTabPosition) {
                    case TAB_TEAMS_POSITION:
                        AddTeamActivity.start(TeamsOpponentsActivity.this);
                        break;
                    case TAB_OPPONENTS_POSITION:
                        AddOpponentActivity.start(TeamsOpponentsActivity.this);
                        break;
                }
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
