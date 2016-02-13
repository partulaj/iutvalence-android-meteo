package worker;

import android.os.AsyncTask;

import org.json.JSONArray;

/**
 * Created by QtoR2 on 13/02/2016.
 */
public class getStationsFromHTTP extends AsyncTask<String, String, String> {

    private JSONParser jsonParser = new JSONParser();
    private static final String GET_STATIONS = "http://intranet.iut-valence.fr/~partulaj/MesTPs/Casir/TP-Meteo/controller/RequestController.php";

    @Override
    protected String doInBackground(String... args) {
        JSONArray jsonStations = jsonParser.makeHttpRequestArray(
                GET_STATIONS, null, null);
        return jsonStations.toString();
    }

}