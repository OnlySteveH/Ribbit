package com.thetechguys.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by TheBank on 17/9/14.
 */
public class RibbitApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "QCct2yMELlmi6Kr0C1L9LpNL47cffBmXTraFeaIW", "D9pEOd5WX8puJAYroo5ktt2qBrsxdtBMwb9hXx46");


    }
}
