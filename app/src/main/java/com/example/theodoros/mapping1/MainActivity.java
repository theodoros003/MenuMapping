package com.example.theodoros.mapping1;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View.OnClickListener;

import android.preference.PreferenceActivity;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity
{
    MapView mv;
    Double lat = 51.05;
    Double lon =-0.72;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(lat,lon));

        TextView la = (TextView)findViewById(R.id.la1);
        TextView vla = (TextView)findViewById(R.id.vla1);
        TextView lo = (TextView)findViewById(R.id.lo1);
        TextView vlo = (TextView)findViewById(R.id.vlo1);
        vla.setText(lat.toString());
        vlo.setText(lon.toString());
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }
        if(item.getItemId() == R.id.setlocation)
        {
            // react to the menu item being selected...
            Intent intent = new Intent(this,SetLocationActivity.class);

            Bundle bundle = new Bundle();
            bundle.putDouble("com.example.latitude", lat);
            bundle.putDouble("com.example.longitude", lon);
            intent.putExtras(bundle);
            startActivityForResult(intent,1);

            return true;
        }
        return false;

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("com.example.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
        if(requestCode==1)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                Double lat = extras.getDouble("com.example.latitude");
                Double lon = extras.getDouble("com.example.longitude");

                mv.getController().setCenter(new GeoPoint(lat,lon));

                TextView vla = (TextView)findViewById(R.id.vla1);
                vla.setText(lat.toString());

                TextView vlo = (TextView)findViewById(R.id.vlo1);
                vlo.setText(lon.toString());

            }
        }
    }
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        boolean autodownload = prefs.getBoolean("autodownload", true);
        String pizzaCode = prefs.getString("pizza", "NONE");

        // do something with the preference data...
    }
    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean ("isRecording", isRecording);
        editor.commit();
    }
    public void onSaveInstanceState (Bundle savedInstanceState)
    {
        savedInstanceState.putBoolean("isRecording", isRecording);
    }
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        if (savedInstanceState != null)
        {
            isRecording = savedInstanceState.getBoolean ("isRecording");
        }
    }


}