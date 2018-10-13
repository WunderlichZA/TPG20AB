package za.ac.cut.hockeyapplication.helper;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HockeyDBHelper {

    private static HockeyDBHelper dbManager;
    private static Realm realm;
    private String dbName;

    private HockeyDBHelper(Context context, String dbName) {
        this.dbName = dbName;
        if (realm == null) {
            //Create Realm object
            Realm.init(context);
            RealmConfiguration configuration = new RealmConfiguration.Builder().name(this.dbName)
                                                                               .deleteRealmIfMigrationNeeded()
                                                                               .build();
            realm = Realm.getInstance(configuration);
        }
    }

    public static HockeyDBHelper getRealmInstance(Context context, String dbName) {
        if (dbManager == null) {
            //Create DBManager object
            dbManager = new HockeyDBHelper(context, dbName);
        }
        return dbManager;
    }

    /* CRUD Methods */
}
