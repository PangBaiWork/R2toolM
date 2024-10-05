package work.pangbai.r2toolm.global;

import android.annotation.SuppressLint;
import android.app.Application;

import com.google.android.material.color.DynamicColors;

import java.io.File;

public class App extends Application {
    @SuppressLint("UnsafeDynamicallyLoadedCode")
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        String dir = getApplicationInfo().nativeLibraryDir;
        File[] libs = new File(dir).listFiles();

        assert libs != null;
        for (File lib : libs) {
            if (!lib.getAbsolutePath().contains("exec")) {
                //   Log.e("Load lib", lib.getAbsolutePath());
                System.load(lib.getAbsolutePath());
            }
        }
    }
}
