package kmv.com.blitz;

import android.annotation.SuppressLint;
import android.app.Application;

import shortbread.Shortbread;

/**
 * Created by Nirnay Mittal on 19-09-2018.
 */

@SuppressLint("Registered")
public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Shortbread.create(this);
    }
}
