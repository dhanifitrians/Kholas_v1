package com.example.dhani.kholas.base;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

/**
 * Created by Muhammad Natsir on 16,April,2019
 * Jakarta, Indonesia.
 */
public class BaseLogger extends Timber.Tree {
    private static final int MAX_LOG_LENGTH = 4000;

    @Override
    protected boolean isLoggable(String tag, int priority) {
        switch (priority) {
            case Log.VERBOSE:
            case Log.DEBUG:
            case Log.INFO:
                return false;
            default:
                //Only Log Warn, Error, WTF
                return true;
        }
    }

    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        if (isLoggable(tag, priority)) {

            //Report Caught exception to your CrashAnalytic Library
//            if(priority == Log.ERROR && t!=null){
            //CrashAnalytic.log(e);
//            }

            if (message.length() < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message);
                } else {
                    Log.println(priority, tag, message);
                }
                return;
            }

            for (int i = 0, length = message.length(); i < length; i++) {
                int newLine = message.indexOf("\n", i);
                newLine = newLine != -1 ? newLine : length;
                do {
                    int end = Math.min(newLine, i + MAX_LOG_LENGTH);
                    String part = message.substring(i, end);
                    if (priority == Log.ASSERT) {
                        Log.wtf(tag, part);
                    } else {
                        Log.println(priority, tag, part);
                    }
                    i = end;
                } while (i < newLine);
            }

        }

    }
}
