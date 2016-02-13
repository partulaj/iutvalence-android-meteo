package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by QtoR2 on 11/02/2016.
 */
public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {

    private String createTableStation =
            "CREATE TABLE IF NOT EXISTS Station(" +
                    "id text PRIMARY KEY, " +
                    "libellé text, " +
                    "latitude text, " +
                    "longitude text, " +
                    "altitude text);";

    private String createTableReleve =
            "CREATE TABLE IF NOT EXISTS Releve(" +
                    "station text, " +
                    "quand text, " +
                    "temp1 real, " +
                    "temp2 real, " +
                    "pressure integer, " +
                    "lux integer, " +
                    "hygro integer, " +
                    "windSpeed real, " +
                    "windDir integer, " +
                    "PRIMARY KEY (station,quand), " +
                    "FOREIGN KEY (station) REFERENCES Station (id));";


    public BD_SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableStation);
        db.execSQL(createTableReleve);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Releve");
        db.execSQL("drop table if exists Station");
        onCreate(db);
    }
}
