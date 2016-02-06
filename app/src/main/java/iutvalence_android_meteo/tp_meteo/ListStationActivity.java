package iutvalence_android_meteo.tp_meteo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import classes.Station;
import worker.JSONParser;

public class ListStationActivity extends AppCompatActivity {
    public JSONArray json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_station);

        stationAdapter maListeStation = new stationAdapter();

        Log.d("test", maListeStation.toString());
        ListView lv = (ListView) findViewById(R.id.lvListStationActivity);
        lv.setAdapter(maListeStation);
    }

    public class getListStationsFromJSON extends AsyncTask<String, String, String> {

        private static final String GET_STATIONS = "http://intranet.iut-valence.fr/~partulaj/MesTPs/Casir/TP-Meteo/controller/RequestController.php";

        public JSONParser jsonParser = new JSONParser();

        public List<Station> listeStations;

        @Override
        protected String doInBackground(String... args) {
            Intent intent;
            Bundle extras = new Bundle();

            List<String> params = null;
            List<String> values = null;

            listeStations = null;
            json = jsonParser.makeHttpRequestArray(GET_STATIONS, params, values);
            return json.toString();
        }

        protected void onPostExecute(String file_url) {

            if (file_url != null) {
                Toast.makeText(ListStationActivity.this, "Liste chargée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class stationAdapter extends BaseAdapter {

        List<Station> listeStations = getStations();

        @Override
        public int getCount() {
            return listeStations.size();
        }

        @Override
        public Object getItem(int position) {
            return listeStations.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.activity_list_station, parent, false);
            }

            TextView txtLibelléStation = (TextView) convertView.findViewById(R.id.txtLibelleStationListStationActivity);
            TextView txtLongitudeStation = (TextView) convertView.findViewById(R.id.txtLongitudeStationListStationActivity);
            TextView txtLatitudeStation = (TextView) convertView.findViewById(R.id.txtLatitudeStationListStationActivity);
            TextView txtAltitudeStation = (TextView) convertView.findViewById(R.id.txtAltitudeStationListStationActivity);

            Station uneStation = listeStations.get(position);
            txtLibelléStation.setText(uneStation.getLibellé());
            txtLongitudeStation.setText(uneStation.getLongitude());
            txtLatitudeStation.setText(uneStation.getLatitude());
            txtAltitudeStation.setText(uneStation.getAltitude());

            return convertView;
        }

        public List<Station> getStations() {
            Log.d("test", "test");
            String monJSON = null;
            List<Station> listeStations = new ArrayList<Station>();
            try {
                monJSON = new getListStationsFromJSON().execute().get();
                if (monJSON == null) {
                    return listeStations;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("json", monJSON.toString());
            Gson unGson = new Gson();

            try {
                for (int i = 0; i <= json.length() - 1; i++) {
                    JSONObject monObjetJSON = json.getJSONObject(i);
                    String monStringJSON = monObjetJSON.toString();
                    Station uneStation = unGson.fromJson(monStringJSON, Station.class);
                    listeStations.add(uneStation);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listeStations;
        }
    }
}
