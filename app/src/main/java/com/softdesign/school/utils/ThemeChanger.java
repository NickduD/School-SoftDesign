package com.softdesign.school.utils;

/**
 * Created by Nik on 28.01.16.
 */
import android.app.Activity;
import android.content.Intent;

import com.softdesign.school.R;

public class ThemeChanger {

        private static int sTheme;
        public final static int THEME_RED = 0;
        public final static int THEME_GREEN = 1;
        public final static int THEME_BLUE = 2;
        /**
         * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
         */
        public static void changeToTheme(Activity activity, int theme)
        {
            sTheme = theme;
            activity.finish();
            activity.startActivity(new Intent(activity, activity.getClass()));
        }
        /** Set the theme of the activity, according to the configuration. */
        public static void onActivityCreateSetTheme(Activity activity)
        {
            switch (sTheme)
            {
                default:
                    activity.setTheme(R.style.AppTheme);
                case THEME_RED:
                    activity.setTheme(R.style.RedTheme);
                    break;
                case THEME_GREEN:
                    activity.setTheme(R.style.GreenTheme);
                    break;
                case THEME_BLUE:
                    activity.setTheme(R.style.BlueTheme);
                    break;
            }
        }

}
