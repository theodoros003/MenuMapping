package com.example.theodoros.mapping1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.osmdroid.util.GeoPoint;

public class SetLocationActivity extends AppCompatActivity implements OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        Button setlocation = (Button)findViewById(R.id.btn1);
        setlocation.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        TextView la = (TextView)findViewById(R.id.la1);
        EditText vla = (EditText)findViewById(R.id.vla1);
        TextView lo = (TextView)findViewById(R.id.lo1);
        EditText vlo = (EditText)findViewById(R.id.vlo1);
        double lat = Double.parseDouble(vla.getText().toString());
        double lon = Double.parseDouble(vlo.getText().toString());

        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean setlocation=false;
        if (v.getId()==R.id.btn1)
        {
            setlocation=true;
        }
        bundle.putBoolean("com.example.setlocation",setlocation);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}
