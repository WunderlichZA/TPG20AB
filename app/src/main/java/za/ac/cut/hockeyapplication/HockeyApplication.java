package za.ac.cut.hockeyapplication;

import android.app.Application;

import com.backendless.Backendless;

public class HockeyApplication extends Application {

    public static final String APPLICATION_ID = "9B57459C-448D-D5B5-FF27-4F0A80FC8A00";
    public static final String API_KEY = "3B98AFBF-EADB-A2A7-FF48-8479A04CF700";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
    }
}
