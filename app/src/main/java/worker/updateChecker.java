package worker;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import DAO.ReleveDAO;
import DAO.StationDAO;
import classes.Releve;
import classes.Station;

/**
 * Created by QtoR2 on 13/02/2016.
 */
public class updateChecker {


    public void checkUpdate(Context ct) {

        String jsonStation = null;
        String jsonReleves = null;
        StationDAO stationAcces = new StationDAO(ct);
        ReleveDAO releveAcces = new ReleveDAO(ct);

        SharedPreferences preferences = ct.getSharedPreferences("preferences", Context.MODE_PRIVATE);

        Calendar dateY = Calendar.getInstance();
        dateY.add(Calendar.DATE, -1);

        String sty = dateY.getTime().toString().substring(0, 10);
        String stbdd = preferences.getString("dateVersionBDD", "0").substring(0, 10);

        if (!stbdd.equals(sty)) {
            Log.d("debug", "maj non necessaire");
        } else {
            Log.d("debug", "maj");
            releveAcces.deleteAll();
            stationAcces.deleteAll();
            try {
                jsonStation = new getStationsFromHTTP().execute().get();
                JSONArray jsonArrayStations = new JSONArray(jsonStation);
                for (int i = 0; i < jsonArrayStations.length(); i++) {
                    JSONObject jObjS = jsonArrayStations.getJSONObject(i);
                    Station maStation = new Gson().fromJson(jObjS.toString(), Station.class);
                    stationAcces.add(maStation);

                    jsonReleves = new getRelevesFromHTTP(maStation.getId()).execute().get();
                    JSONArray jsonArrayReleves = new JSONArray(jsonReleves);
                    for (int j = 0; j < jsonArrayReleves.length(); j++) {
                        JSONObject jObjR = jsonArrayReleves.getJSONObject(j);
                        Releve monReleve = new Gson().fromJson(jObjR.toString(), Releve.class);
                        releveAcces.add(monReleve);
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void forceUpdate(Context ct) {

        String jsonStation = null;
        String jsonReleves = null;
        StationDAO stationAcces = new StationDAO(ct);
        ReleveDAO releveAcces = new ReleveDAO(ct);

        releveAcces.deleteAll();
        stationAcces.deleteAll();
        try {
            jsonStation = new getStationsFromHTTP().execute().get();
            JSONArray jsonArrayStations = new JSONArray(jsonStation);
            for (int i = 0; i < jsonArrayStations.length(); i++) {
                JSONObject jObjS = jsonArrayStations.getJSONObject(i);
                Station maStation = new Gson().fromJson(jObjS.toString(), Station.class);
                stationAcces.add(maStation);

                jsonReleves = new getRelevesFromHTTP(maStation.getId()).execute().get();
                JSONArray jsonArrayReleves = new JSONArray(jsonReleves);
                for (int j = 0; j < jsonArrayReleves.length(); j++) {
                    JSONObject jObjR = jsonArrayReleves.getJSONObject(j);
                    Releve monReleve = new Gson().fromJson(jObjR.toString(), Releve.class);
                    releveAcces.add(monReleve);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
