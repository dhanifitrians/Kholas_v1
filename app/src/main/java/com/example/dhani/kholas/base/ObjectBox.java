package com.example.dhani.kholas.base;

import android.content.Context;

import com.example.dhani.kholas.BuildConfig;
import com.example.dhani.kholas.MyObjectBox;

import org.jetbrains.annotations.NotNull;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import timber.log.Timber;

/**
 * Created by Muhammad Natsir on 15,August,2019
 * Jakarta, Indonesia.
 */
public class ObjectBox {
    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

        //Todo menampilkan objectbox di browser
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(@NotNull StackTraceElement element) {
                    return super.createStackElementTag(element) + " >> " + element.getLineNumber();
                }
            });
            Timber.d("Started..: %s ObjectBox version: %s", true, BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")");

            boolean started = new AndroidObjectBrowser(boxStore).start(context);
            Timber.d("ObjectBox browser: %s ,box store: %s", started, boxStore);
        } else {
            Timber.plant(new BaseLogger());
        }
    }

    public static BoxStore get() {
        if(boxStore == null);
        return boxStore;
    }


}
