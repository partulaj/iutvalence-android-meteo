package iutvalence_android_meteo.tp_meteo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import DAO.StationDAO;
import classes.Station;
import worker.updateChecker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Variables représentant les valeurs des TextView
    private TextView txtValueStation, txtValueLibellé, txtValueLongitude, txtValueLatitude, txtValueAltitude;
    public SharedPreferences preferences;
    public Station maStation;
    public updateChecker update = new updateChecker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        //this.getApplicationContext().deleteDatabase("AppStation");

        StationDAO stationAcces = new StationDAO(getApplicationContext());
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        //updateChecker update = new updateChecker();
        update.checkUpdate(getApplicationContext());

        maStation = stationAcces.get(preferences.getString("preferences", "Montélimar"));

        if (maStation != null) {
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
        super.onResume();
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

        if (id == R.id.action_getDonnees) {
            update.forceUpdate(getApplicationContext());
            onResume();
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
