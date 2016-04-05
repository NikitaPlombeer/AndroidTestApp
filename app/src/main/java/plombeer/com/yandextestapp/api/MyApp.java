package plombeer.com.yandextestapp.api;

import android.app.Application;
import android.content.Context;

/**
 * Created by Никита on 05.04.2016.
 */
public class MyApp extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
