package com.example.theodoros.mapping1;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.content.Intent;
        import android.widget.Button;
        import android.view.View;
        import android.view.View.OnClickListener;
        import org.osmdroid.tileprovider.tilesource.TileSourceFactory;


public class MapChooseActivity extends AppCompatActivity implements OnClickListener
{

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose);
        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button hikebikemap = (Button)findViewById(R.id.btnHikeBikeMap);
        hikebikemap.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean hikebikemap=false;
        if (v.getId()==R.id.btnHikeBikeMap)
        {
            hikebikemap=true;
        }
        bundle.putBoolean("com.example.hikebikemap",hikebikemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
}

