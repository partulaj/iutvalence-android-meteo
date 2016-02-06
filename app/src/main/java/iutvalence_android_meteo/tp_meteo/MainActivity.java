package iutvalence_android_meteo.tp_meteo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import classes.Station;
import worker.getStationFromJSON;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Variables représentant les valeurs des TextView
    private TextView txtValueStation, txtValueLibellé, txtValueLongitude, txtValueLatitude, txtValueAltitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Récupération d'une station
        String uneStation = null;
        try {
            uneStation = new getStationFromJSON().execute().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Création d'un objet Station à partir du json récupéré
        Station maStation = new Gson().fromJson(uneStation, Station.class);

        txtValueStation = (TextView) findViewById(R.id.txtvalueStationMainActivity);
        txtValueLibellé = (TextView) findViewById(R.id.txtvalueLibelleStationMainActivity);
        txtValueLongitude = (TextView) findViewById(R.id.txtvalueLongitudeStationMainActivity);
        txtValueLatitude = (TextView) findViewById(R.id.txtvalueLatitudeStationMainActivity);
        txtValueAltitude = (TextView) findViewById(R.id.txtvalueAltitudeMainActivity);

        txtValueStation.setText(maStation.getId());
        txtValueLibellé.setText(maStation.getLibellé());
        txtValueLongitude.setText(maStation.getLongitude());
        txtValueLatitude.setText(maStation.getLatitude());
        txtValueAltitude.setText(maStation.getAltitude());

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToListStationActivity:
                toListStationActivity(v);
        }
    }
}
