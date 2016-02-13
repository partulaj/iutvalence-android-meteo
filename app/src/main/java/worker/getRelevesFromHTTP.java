package worker;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

/**
 * Created by QtoR2 on 13/02/2016.
 */
public class getRelevesFromHTTP extends AsyncTask<String, String, String> {
    private String idStation;

    public getRelevesFromHTTP(String id) {
        this.idStation = id;
    }

    private JSONParser jsonParser = new JSONParser();
    private static final String GET_RELEVES = "http://intranet.iut-valence.fr/~partulaj/MesTPs/Casir/TP-Meteo/controller/RequestController.php";

    @Override
    protected String doInBackground(String... args) {

        List<String> params = Arrays.asList("id", "relever");
        List<String> values = Arrays.asList(idStation, "true");

        JSONArray jsonReleves = jsonParser.makeHttpRequestArray(
                GET_RELEVES, params, values);
        return jsonReleves.toString();
    }
}
