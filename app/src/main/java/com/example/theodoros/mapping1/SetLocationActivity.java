package com.example.theodoros.mapping1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.app.AlertDialog;

import org.osmdroid.util.GeoPoint;

public class SetLocationActivity extends AppCompatActivity implements OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        Button setlocation = (Button)findViewById(R.id.btn2);
        setlocation.setOnClickListener(this);

        Bundle bundle = this.getIntent().getExtras();
        Double lat = bundle.getDouble("com.example.latitude");
        Double lon = bundle.getDouble("com.example.longitude");

        EditText vla = (EditText)findViewById(R.id.vla1);
        vla.setText(lat.toString());
        EditText vlo = (EditText)findViewById(R.id.vlo1);
        vlo.setText(lon.toString());
    }

    public void onClick(View v)
    {
        TextView la = (TextView)findViewById(R.id.la1);
        EditText vla = (EditText)findViewById(R.id.vla1);
        TextView lo = (TextView)findViewById(R.id.lo1);
        EditText vlo = (EditText)findViewById(R.id.vlo1);
        double lat = Double.parseDouble(vla.getText().toString());
        double lon = Double.parseDouble(vlo.getText().toString());

        if (lat>180 || lat <-180 || lon >90 || lon<-90){
            new AlertDialog.Builder(this).setMessage("invalid latitude or longitude values").
                    setPositiveButton("OK", null).show();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            boolean setlocation = false;

            bundle.putDouble("com.example.latitude", lat);
            bundle.putDouble("com.example.longitude", lon);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
