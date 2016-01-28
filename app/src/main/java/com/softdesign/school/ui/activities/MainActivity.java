package com.softdesign.school.ui.activities;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String VISIBLE_KEY = "visible";
    public final static String STATUS_BAR_COLOR_KEY = "statusBarColor";
    public final static String ACTION_BAR_COLOR_KEY = "actionBarColor";
    private final Lg log = Lg.getLogger(MainActivity.class, true);

    int mStatusBarColor = 0;
    int mActionBarColor = 0;

    CheckBox mCheckBox;
    EditText mEditText;
    EditText mEditText2;
    Toolbar mToolbar;
    Button mRedButton;
    Button mGreenButton;
    Button mBlueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Lg.e(this.getClass().getName(), "onCreate");
        log.e("onCreate");
        super.onCreate(savedInstanceState);

//        ThemeChanger.onActivityCreateSetTheme(this); // handler

        setContentView(R.layout.activity_main);
        setTitle("Homework #2");

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setupToolbar();

        mRedButton = (Button) findViewById(R.id.button_red);
        mGreenButton = (Button) findViewById(R.id.button_green);
        mBlueButton = (Button) findViewById(R.id.button_blue);

        mRedButton.setOnClickListener(this);
        mGreenButton.setOnClickListener(this);
        mBlueButton.setOnClickListener(this);
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
            Toast.makeText(this, "Click by home", Toast.LENGTH_SHORT).show();
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
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);

        outState.putInt(ACTION_BAR_COLOR_KEY, mActionBarColor);
        outState.putInt(STATUS_BAR_COLOR_KEY, mStatusBarColor);

//        Lg.e(this.getClass().getName(), "onSaveInstanceState");
        log.e("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        savedInstanceState.getBoolean(VISIBLE_KEY);
        mEditText2.setVisibility(savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE);

        int statusBarColorKey = savedInstanceState.getInt(STATUS_BAR_COLOR_KEY);
        int actionBarColorKey = savedInstanceState.getInt(ACTION_BAR_COLOR_KEY);
        if(statusBarColorKey !=0) setStatusBarColor(statusBarColorKey);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null && actionBarColorKey !=0) setActionBarColor(actionBar, actionBarColorKey);

//        Lg.e(this.getClass().getName(), "onRestoreInstanceState");
        log.e("onRestoreInstanceState");
    }
}
