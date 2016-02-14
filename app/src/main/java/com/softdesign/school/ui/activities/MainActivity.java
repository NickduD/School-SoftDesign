package com.softdesign.school.ui.activities;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingsFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String VISIBLE_KEY = "visible";
    public final static String STATUS_BAR_COLOR_KEY = "statusBarColor";
    public final static String ACTION_BAR_COLOR_KEY = "actionBarColor";
    public final static String CURRENT_MENU_ITEM = "menuItem";

    private final Lg log = Lg.getLogger(MainActivity.class, true);

    int mStatusBarColor = 0;
    int mActionBarColor = 0;
    int mMenuItem = 0;

    CheckBox mCheckBox;
    EditText mEditText;
    EditText mEditText2;
    Toolbar mToolbar;

    NavigationView mNavigationView;
    DrawerLayout mNavigationDrawer;

    Fragment mFragment;
    FrameLayout mFrameLayout;
    MenuItem mPreviousItem;

    AppBarLayout mAppBarLayout;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    AppBarLayout.LayoutParams params = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Lg.e(this.getClass().getName(), "onCreate");
        log.e("onCreate");
        log.e("onCreate OnMenu" + mMenuItem);
        super.onCreate(savedInstanceState);

//        ThemeChanger.onActivityCreateSetTheme(this); // handler

        setContentView(R.layout.activity_main);
        setTitle("Homework #4");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);


        mNavigationDrawer =(DrawerLayout) findViewById(R.id.navigation_drawer);

        mNavigationView =(NavigationView) findViewById(R.id.navigation_view);
        setupToolbar();
        setupDrawer();
        if(savedInstanceState!=null){

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit();
        }

    }
    public void lockBar(boolean collapse) {

        params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams(); // get ViewElement parameters

        if (collapse) {
            mAppBarLayout.setExpanded(false);
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout mAppBarLayout, int verticalOffset) {
                    if (mCollapsingToolbarLayout.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbarLayout) + getStatusBarHeight()) {
                        params.setScrollFlags(0); // remove scroll attributes
                        mCollapsingToolbarLayout.setLayoutParams(params);
                        mAppBarLayout.removeOnOffsetChangedListener(this);
                    }
                }
            };
            mAppBarLayout.addOnOffsetChangedListener(mListener);
        } else {
            mAppBarLayout.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
            mCollapsingToolbarLayout.setLayoutParams(params);
        }
    }
    /**
     * Returns toolbar heigth
     */

    public int getStatusBarHeight() {
        /*int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;*/
        return 0;
    }

    /**
     * Set text into CollapsToolBar
     */
    public void setCollapsToolbarTitle(CharSequence title) {
        mCollapsingToolbarLayout.setTitle(title);
        mCollapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));
    }

    /**
     * Setup navigation drawer
     */
    private void setupDrawer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.navigation_contacts).setChecked(true);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.navigation_team).setChecked(true);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_tasks:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.navigation_tasks).setChecked(true);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_settings:
                        mFragment = new SettingsFragment();
                        mNavigationView.getMenu().findItem(R.id.navigation_settings).setChecked(true);
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_frame_container, mFragment).addToBackStack(null).commit();
                }
                if(mPreviousItem != null){
                    mPreviousItem.setChecked(false);
                }
                mPreviousItem = item;
                mMenuItem = item.getItemId();

                log.e("saveInOnMenu" + mMenuItem);

                mNavigationDrawer.closeDrawers();
                return false;
            }
        });

    }


    /**
     * Setup custom toolbar
     */
    private void setupToolbar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){ // system reserved button "Home"
//            Toast.makeText(this, "Click by home", Toast.LENGTH_SHORT).show();
            mNavigationDrawer.openDrawer(Gravity.LEFT);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Lg.e(this.getClass().getName(), "onStart");
        log.e("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Lg.e(this.getClass().getName(), "onResume");
        log.e("onResume");
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

        } else {
            setTitle("Homework #4");
            finish();
        }
//        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Lg.e(this.getClass().getName(), "onPause");
        log.e("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Lg.e(this.getClass().getName(), "onStop");
        log.e("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Lg.e(this.getClass().getName(), "onRestart");
        log.e("onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Lg.e(this.getClass().getName(), "onDestroy");
        log.e("onDestroy");
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        ActionBar actionBar = getSupportActionBar();
        switch (viewId) {
            case R.id.checkBox:
                if (mCheckBox.isChecked()) {
                    Toast.makeText(this, "Checked", Toast.LENGTH_SHORT).show();
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(this, "Unchecked", Toast.LENGTH_SHORT).show();
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.button_blue:
                Toast.makeText(this, "Blue Btn", Toast.LENGTH_SHORT).show();

             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_BLUE);

                setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
                setActionBarColor(actionBar, R.color.dark_blue);
                break;
            case R.id.button_green:

                Toast.makeText(this, "Green Btn", Toast.LENGTH_SHORT).show();

                setStatusBarColor(ContextCompat.getColor(this, R.color.green));
                setActionBarColor(actionBar, R.color.dark_green);
             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_GREEN);
                //mToolbar.setBackground(new ColorDrawable(getColor(R.color.green)));
                break;
            case R.id.button_red:
                Toast.makeText(this, "Red Btn", Toast.LENGTH_SHORT).show();

                setStatusBarColor(ContextCompat.getColor(this, R.color.red));
                setActionBarColor(actionBar, R.color.dark_red);

             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_RED);

                break;
        }
    }

    /**
     * Sets menu item is checked
     * @param menuId is id of menu item
     */
    public void setMenuItemChecked(int menuId){
        log.e("setMenu" + menuId);
        if(mPreviousItem != null){
            mPreviousItem.setChecked(false);
        }
        mPreviousItem =  mNavigationView.getMenu().findItem(menuId);
        mNavigationView.getMenu().findItem(menuId).setChecked(true);
//        mNavigationView.setCheckedItem(menuId);
    }

    /**
     * Sets a new color of {@link ActionBar}
     * @param actionBar is object for setting a color
     * @param color is a new color of actionBar
     */

    private void setActionBarColor(ActionBar actionBar, int color){
        if(actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, color)));
            mActionBarColor = color;
        }
    }

    /**
     * Sets a new color of StatusBar
     * @param color is a new color of StatusBar
     */
    private void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
            mStatusBarColor = color;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);

        outState.putInt(ACTION_BAR_COLOR_KEY, mActionBarColor);
        outState.putInt(STATUS_BAR_COLOR_KEY, mStatusBarColor);
//        outState.putInt(CURRENT_MENU_ITEM, mMenuItem);
//        log.e("CURRENT_MENU_ITEM:" + mMenuItem);

//        Lg.e(this.getClass().getName(), "onSaveInstanceState");
        log.e("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState.getBoolean(VISIBLE_KEY);
//        mEditText2.setVisibility(savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE);

        int statusBarColorKey = savedInstanceState.getInt(STATUS_BAR_COLOR_KEY);
        int actionBarColorKey = savedInstanceState.getInt(ACTION_BAR_COLOR_KEY);
        if(statusBarColorKey !=0) setStatusBarColor(statusBarColorKey);
//        int currentMenuItemChecked = savedInstanceState.getInt(CURRENT_MENU_ITEM);
//        if(currentMenuItemChecked !=0) {
//            mNavigationView.getMenu().findItem(currentMenuItemChecked).setChecked(true);
//            mPreviousItem = mNavigationView.getMenu().findItem(currentMenuItemChecked);
//        }
//        log.e("onRestoreInstanceState MENU_ITEM:" + currentMenuItemChecked);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null && actionBarColorKey !=0) setActionBarColor(actionBar, actionBarColorKey);

//        Lg.e(this.getClass().getName(), "onRestoreInstanceState");
        log.e("onRestoreInstanceState");
    }
}
