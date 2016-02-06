package iutvalence_android_meteo.tp_meteo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import worker.JSONParser;

public class MainActivity extends AppCompatActivity {

    private JSONParser jsonParser = new JSONParser();
    private static final String GET_ONE_STATION = "http://intranet.iut-valence.fr/~partulaj/MesTPs/Casir/TP-Meteo/controller/RequestController.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Récupération d'une station
        String test = null;
        try {
            test = new getUser().execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void toListStationActivity(View view) {
        Intent intent = new Intent(this, ListStationActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class getUser extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            List<String> params = Arrays.asList("id");
            List<String> values = Arrays.asList("Montélimar");
            JSONObject json = jsonParser.makeHttpRequest(
                    GET_ONE_STATION, params, values);
            Log.d("getOneStation", json.toString());
            return json.toString();
        }

    }
}
