package DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by QtoR2 on 11/02/2016.
 */
public abstract class connectDAO<T> {

    private static String base = "AppStation";
    private static int version = 1;
    private static SharedPreferences preferences;
    protected BD_SQLiteOpenHelper accesBD;

    public connectDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public abstract T get(String id);

    public abstract ArrayList<T> getAll();

    public abstract ArrayList<T> cursorToArrayList(Cursor cursor);

    public abstract long add(T object);

    public abstract void deleteAll();

}
