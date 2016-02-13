package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import classes.Station;

/**
 * Created by QtoR2 on 11/02/2016.
 */
public class StationDAO extends connectDAO<Station> {

    public StationDAO(Context ct) {
        super(ct);
    }

    @Override
    public Station get(String id) {
        Station laStation = null;
        Cursor curseur;
        curseur = super.accesBD.getReadableDatabase().rawQuery("select id,libellé,latitude,longitude,altitude from Station where id='" + id + "';", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laStation = new Station(curseur.getString(0), curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getString(4));
        }
        return laStation;
    }

    @Override
    public ArrayList<Station> getAll() {
        Cursor curseur;
        String req = "select id, libellé, latitude, longitude, altitude from Station";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        return cursorToArrayList(curseur);
    }

    @Override
    public ArrayList<Station> cursorToArrayList(Cursor curseur) {
        ArrayList<Station> listeStations = new ArrayList<Station>();

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            Station laStation = null;
            laStation = new Station(curseur.getString(0), curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getString(4));
            listeStations.add(laStation);
            curseur.moveToNext();

        }

        return listeStations;
    }

    public long add(Station uneStation) {
        long ret;
        SQLiteDatabase bd = super.accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put("id", uneStation.getId());
        value.put("libellé", uneStation.getLibellé());
        value.put("latitude", uneStation.getLatitude());
        value.put("longitude", uneStation.getLongitude());
        value.put("altitude", uneStation.getAltitude());

        ret = bd.insert("Station", null, value);
        return ret;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase bd = super.accesBD.getWritableDatabase();
        bd.delete("Station", null, null);
    }
}
