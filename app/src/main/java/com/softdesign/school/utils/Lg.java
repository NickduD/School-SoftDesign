package com.softdesign.school.utils;

/**
 * Created by Nik on 19.01.16.
 */
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Зарефакторить код логера в соответствии с данными на лекции рекомендациями, исспользовать подход DRY Don’t repeat yourself (не повторяй себя) -
 * т.е. избегаем повторения уже ранее написанного кода + Javadoc,
 * логер должен исспользовать различные уровни вывода логов (Verbose, debug, info, error, warn, assert ).
 */
public class Lg {

    private static final String PREFIX = "HTC ";
    /**
     * Constant for split message for logging into blocks
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     * Set status of logging: should log or no
     * @return status of logging
     */
    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
//        return true;
        return true;
    }

    /**
     * Split text into blocks by {@value #LOGCAT_BUFFER_SIZE} symbols
     * @param text logging message
     * @return list of splitted strings
     */
    private static List<String> getSplittedText (String text){
        List <String> list = new ArrayList<String>();
        if (text.length() > LOGCAT_BUFFER_SIZE){
            String s = text;
            while (s.length() > LOGCAT_BUFFER_SIZE){
                String sub = s.substring(0, LOGCAT_BUFFER_SIZE);
                s = s.substring(LOGCAT_BUFFER_SIZE);
                list.add(sub);
            }
            list.add(s);
        } else {
            list.add(text);
        }
        return list;
    }

    /**
     * Send an INFO log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void i (String tag, String text){
        if (shouldLog()) {
            List <String> splittedText = getSplittedText(text);
            for(String s: splittedText) Log.i(PREFIX + tag, s);
        }
    }
    /**
     * Send an ERROR log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void e (String tag, String text) {
        if (shouldLog()) {
            List <String> splittedText = getSplittedText(text);
            for(String s: splittedText) Log.e(PREFIX + tag, s);
        }
    }
    /**
     * Send a WARN log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void w (String tag, String text) {
        if (shouldLog()) {
            List <String> splittedText = getSplittedText(text);
            for(String s: splittedText) Log.w(PREFIX + tag, s);
        }
    }
    /**
     * Send a VERBOSE log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void v (String tag, String text) {
        if (shouldLog()) {
            List <String> splittedText = getSplittedText(text);
            for(String s: splittedText) Log.v(PREFIX + tag, s);
        }
    }
    /**
     * Send a DEBUG log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param text The message you would like logged.
     */
    public static void d (String tag, String text) {
        if (shouldLog()) {
            List <String> splittedText = getSplittedText(text);
            for(String s: splittedText) Log.d(PREFIX + tag, s);
        }
    }

}
