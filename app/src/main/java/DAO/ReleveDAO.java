package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import classes.Releve;

/**
 * Created by QtoR2 on 11/02/2016.
 */
public class ReleveDAO extends connectDAO<Releve> {

    public ReleveDAO(Context ct) {
        super(ct);
    }

    @Override
    public Releve get(String station) {
        Releve leReleve = null;
        Cursor curseur;
        curseur = super.accesBD.getReadableDatabase().rawQuery("select station, quand, temp1, temp2, pressure, lux, hygro, windSpeed, windDir from RELEVE where station='" + station + "';", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leReleve = new Releve(curseur.getString(0), curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getString(4), curseur.getString(5), curseur.getString(6), curseur.getString(7), curseur.getString(8));
        }
        accesBD.close();
        return leReleve;
    }

    @Override
    public ArrayList<Releve> getAll() {
        Cursor curseur;
        String req = "select station, quand, temp1, temp2, pressure, lux, hygro, windSpeed, windDir from Releve";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        accesBD.close();
        return cursorToArrayList(curseur);
    }

    @Override
    public ArrayList<Releve> cursorToArrayList(Cursor curseur) {
        ArrayList<Releve> listeReleves = new ArrayList<Releve>();

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            Releve leReleve = null;
            leReleve = new Releve(curseur.getString(0), curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getString(4), curseur.getString(5), curseur.getString(6), curseur.getString(7), curseur.getString(8));
            listeReleves.add(leReleve);
            curseur.moveToNext();
        }

        return listeReleves;
    }

    public long add(Releve unReleve) {
        long ret;
        SQLiteDatabase bd = super.accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put("station", unReleve.getStation());
        value.put("quand", unReleve.getQuand());
        value.put("temp1", unReleve.getTemp1());
        value.put("temp2", unReleve.getTemp2());
        value.put("pressure", unReleve.getPressure());
        value.put("lux", unReleve.getLux());
        value.put("hygro", unReleve.getHygro());
        value.put("windSpeed", unReleve.getWindSpeed());
        value.put("windDir", unReleve.getWindDir());

        ret = bd.insert("Releve", null, value);
        accesBD.close();
        return ret;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase bd = super.accesBD.getWritableDatabase();
        bd.delete("Releve", null, null);
        accesBD.close();
    }

    public ArrayList<Releve> getAllFromStation(String station) {
        Cursor curseur;
        String req = "select station, quand, temp1, temp2, pressure, lux, hygro, windSpeed, windDir from Releve where station = '" + station + "'";
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        accesBD.close();
        return cursorToArrayList(curseur);
    }
}
