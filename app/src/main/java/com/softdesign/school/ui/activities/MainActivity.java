package com.softdesign.school.ui.activities;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        Lg.e(this.getClass().getName(), "onCreate");
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
        Lg.e(this.getClass().getName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getClass().getName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getClass().getName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getClass().getName(), "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getClass().getName(), "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getClass().getName(), "onDestroy");
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
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.test_color_blue)));
             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_BLUE);

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().setStatusBarColor(Color.BLUE);
                    mStatusBarColor = 1;
                }*/

                setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
                setActionBarColor(actionBar, R.color.dark_blue);
                /*if(actionBar != null) {
                    int test =
                    actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.dark_blue)));
                    mActionBarColor = R.color.dark_blue;
                }*/
                /*if(actionBar != null) {
                    actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.dark_blue)));
                    mActionBarColor = 1;
                }*/
                //changing statusbar
              //  getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

               /* if (android.os.Build.VERSION.SDK_INT >= 21){
                    Window window = this.getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    //window.setStatusBarColor(this.getResources().getColor(android.R.color.holo_blue_dark, getTheme()));//).getColor(R.color.primary_dark));
                    window.setStatusBarColor(this.getResources().getColor(android.R.color.white));
                }*/
                break;
            case R.id.button_green:

                Toast.makeText(this, "Green Btn", Toast.LENGTH_SHORT).show();
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().setStatusBarColor(Color.GREEN);
                    mStatusBarColor = 2;
                }
                if(actionBar != null) {
                    actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.dark_green)));
                    mActionBarColor = 2;
                }*/

                setStatusBarColor(ContextCompat.getColor(this, R.color.green));
                setActionBarColor(actionBar, R.color.dark_green);
             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_GREEN);
                //mToolbar.setBackground(new ColorDrawable(getColor(R.color.green)));
                break;
            case R.id.button_red:
                Toast.makeText(this, "Red Btn", Toast.LENGTH_SHORT).show();

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    getWindow().setStatusBarColor(Color.RED);
                    mStatusBarColor = 3;
                }
                if(actionBar != null) {
                    actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.dark_red)));
                    mActionBarColor = 3;
                }*/
                setStatusBarColor(ContextCompat.getColor(this, R.color.red));
                setActionBarColor(actionBar, R.color.dark_red);

             //   ThemeChanger.changeToTheme(this, ThemeChanger.THEME_RED);

                break;
        }
    }

    private void setActionBarColor(ActionBar actionBar, int color){
        if(actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, color)));
            mActionBarColor = color;
        }
    }

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

        Lg.e(this.getClass().getName(), "onSaveInstanceState");
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

        Lg.e(this.getClass().getName(), "onRestoreInstanceState");

    }
}
