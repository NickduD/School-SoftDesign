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

    private Lg(String loggerName, boolean shouldLog){
        this.tag = loggerName;
        this.shouldLog = shouldLog;
    }

    /**
     *
     * @param clazz can be used as the tag of the logger
     * @param shouldLog used to enable or disable logging
     * @return new Lg object
     */
    public static Lg getLogger(Class clazz, boolean shouldLog){
        return getLogger(clazz.getName(), shouldLog);
    }

    /**
     *
     * @param loggerName can be used as the tag of the logger
     * @param shouldLog used to enable or disable logging
     * @return new Lg object
     */
    public static Lg getLogger(String loggerName, boolean shouldLog){
        return new Lg(loggerName, shouldLog);
    }

    private static final String PREFIX = "HTC ";
    /**
     * Constant for split message for logging into blocks
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     * Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     */
    private String tag;
    /**
     * Enable or disable logging
     */
    private boolean shouldLog;

    /**
     * Set status of logging: should log or no
     * @return status of logging
     */
//    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
//        return true;
//        return true;
//    }

    private void log(Object message, int loggingLevel){
        if(shouldLog){
            String text = message.toString();
            if (text.length() > LOGCAT_BUFFER_SIZE){
                String s = text;
                while (s.length() > LOGCAT_BUFFER_SIZE){
                    String sub = s.substring(0, LOGCAT_BUFFER_SIZE);
                    s = s.substring(LOGCAT_BUFFER_SIZE);
                    Log.println(loggingLevel, PREFIX + tag, sub);
                }
                Log.println(loggingLevel, PREFIX + tag, s);
            } else {
                Log.println(loggingLevel, PREFIX + tag, text);
            }
        }
    }

    /**
     * Send an INFO log message.
     * @param message The message you would like logged.
     */
    public void i (Object message){
        log(message, Log.INFO);
    }
    /**
     * Send an ERROR log message.
     * @param message The message you would like logged.
     */
    public void e (Object message){
        log(message, Log.ERROR);
    }
    /**
     * Send a WARN log message.
     * @param message The message you would like logged.
     */
    public void w (Object message){
        log(message, Log.WARN);
    }
    /**
     * Send a VERBOSE log message.
     * @param message The message you would like logged.
     */
    public void v (Object message){
        log(message, Log.VERBOSE);
    }
    /**
     * Send a DEBUG log message.
     * @param message The message you would like logged.
     */
    public void d (Object message){
        log(message, Log.DEBUG);
    }
    /**
     * Send an ASSERT log message.
     * @param message The message you would like logged.
     */
    public void a (Object message){
        log(message, Log.ASSERT);
    }

}
