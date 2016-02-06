package iutvalence_android_meteo.tp_meteo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Arrays;
import java.util.List;

import classes.Releve;
import worker.JSONParser;

public class ListReleveStationActivity extends AppCompatActivity {

    public JSONArray json;
    private String monId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_releve_station);

        Bundle extras = getIntent().getExtras();
        monId = extras.getString("id");

        releveAdapter maListeReleve = new releveAdapter();

        ListView lv = (ListView) findViewById(R.id.lvListReleveStationActivity);

        lv.setAdapter(maListeReleve);
    }

    public class getListRelevesStationFromJSON extends AsyncTask<String, String, String> {

        private static final String GET_RELEVES = "http://intranet.iut-valence.fr/~partulaj/MesTPs/Casir/TP-Meteo/controller/RequestController.php";

        public JSONParser jsonParser = new JSONParser();

        public List<Releve> listeReleves;

        @Override
        protected String doInBackground(String... args) {

            List<String> params = Arrays.asList("id", "relever");
            List<String> values = Arrays.asList(monId, "true");

            listeReleves = null;
            json = jsonParser.makeHttpRequestArray(GET_RELEVES, params, values);
            return json.toString();
        }

        protected void onPostExecute(String file_url) {

            if (file_url != null) {
                Toast.makeText(ListReleveStationActivity.this, "Liste chargée", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class releveAdapter extends BaseAdapter {

        List<Releve> listeReleves = getReleves();

        @Override
        public int getCount() {
            return listeReleves.size();
        }

        @Override
        public Object getItem(int position) {
            return listeReleves.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.activity_list_releve_station, parent, false);
            }

            TextView txtStationReleve = (TextView) convertView.findViewById(R.id.txtvalueStationListReleveStationActivity);
            TextView txtDateReleve = (TextView) convertView.findViewById(R.id.txtvalueDateListReleveStationActivity);
            TextView txtTempIntReleve = (TextView) convertView.findViewById(R.id.txtvalueTempIntListReleveStationActivity);
            TextView txtTempExtReleve = (TextView) convertView.findViewById(R.id.txtvalueTempExtListReleveStationActivity);
            TextView txtPressionReleve = (TextView) convertView.findViewById(R.id.txtvaluePressionListReleveStationActivity);
            TextView txtLuxReleve = (TextView) convertView.findViewById(R.id.txtvalueLuminositeListReleveStationActivity);
            TextView txtHygroReleve = (TextView) convertView.findViewById(R.id.txtvalueHygrometrieListReleveStationActivity);
            TextView txtVitVReleve = (TextView) convertView.findViewById(R.id.txtvalueVitesseVentListReleveStationActivity);
            TextView txtDivVReleve = (TextView) convertView.findViewById(R.id.txtvalueDirectionVentListReleveStationActivity);

            Releve unReleve = listeReleves.get(position);
            txtStationReleve.setText(unReleve.getStation());
            txtDateReleve.setText(unReleve.getQuand());
            txtTempIntReleve.setText(unReleve.getTemp1());
            txtTempExtReleve.setText(unReleve.getTemp2());
            txtPressionReleve.setText(unReleve.getPressure());
            txtLuxReleve.setText(unReleve.getLux());
            txtHygroReleve.setText(unReleve.getHygro());
            txtVitVReleve.setText(unReleve.getWindDir());
            txtDivVReleve.setText(unReleve.getWindSpeed());

            return convertView;
        }

        public List<Releve> getReleves() {
            String monJSON = null;
            List<Releve> listeReleves = new ArrayList<Releve>();
            try {
                monJSON = new getListRelevesStationFromJSON().execute().get();
                if (monJSON == null) {
                    return listeReleves;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson unGson = new Gson();

            try {
                for (int i = 0; i <= json.length() - 1; i++) {
                    JSONObject monObjetJSON = json.getJSONObject(i);
                    String monStringJSON = monObjetJSON.toString();
                    Releve unReleve = unGson.fromJson(monStringJSON, Releve.class);
                    listeReleves.add(unReleve);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listeReleves;
        }
    }
}
