package com.hebin.mduse.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hebin.mduse.ui.fragment.BackHandledFragment;
import com.hebin.mduse.R;
import com.hebin.mduse.ui.fragment.TextInputLayout;
import com.hebin.mduse.ui.fragment.Snackbar;
import com.hebin.mduse.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BackHandledFragment.BackHandlerInterface {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.frame_content)
    FrameLayout frameContent;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private BackHandledFragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);
        switchToMain();
    }

    private void switchToMain() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        toolbarTitle.setText("主页");
    }

    private void switchToExample() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new TextInputLayout()).commit();
        toolbarTitle.setText("MD输入框");
    }

    private void switchToBlog() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new Snackbar()).commit();
        toolbarTitle.setText("Snackbar");
    }



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_item_main:
                                switchToMain();
                                break;
                            case R.id.navigation_item_tl:
                                switchToExample();
                                break;
                            case R.id.navigation_item_snackbar:
                                switchToBlog();
                                break;
                        }
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public void setSelectedFragment(BackHandledFragment backHandledFragment) {
        this.selectedFragment = backHandledFragment;
    }


    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (selectedFragment == null || !selectedFragment.onBackPressed()) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                doExitApp();
            }
        }
    }
}
