package alamsyah.scan_qr;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import alamsyah.scan_qr.Adapter.GridViewAdapter;

public class Booth extends AppCompatActivity {
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;
    GridView gridView;
    Context context;
    ArrayList arrayList;
    CardView absen;
    PrefManager prefManager;

    public static String[] gridViewStrings = {
            "(1) HOLOLENS",
            "(2) VR",
            "(3) NEURO",
            "(4) ASTROBOY",
            "(5) KINECT",
            "OTHER",

    };
    public static int[] gridViewImages = {
            R.drawable.ar,
            R.drawable.augmented_reality,
            R.drawable.artificial_intelligence,
            R.drawable.cityscape,
            R.drawable.qr_code_phone,
            R.drawable.qr_code_search
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booth);
        prefManager = new PrefManager(this);
//        gridView = (GridView) findViewById(R.id.grid);
        absen = findViewById(R.id.absen);
        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setChose("absen");
                Intent i = new Intent(Booth.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
//        gridView.setAdapter(new GridViewAdapter(this, gridViewStrings, gridViewImages));
//        Chose();

    }


    public void Chose(){

        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setChose("absen");
                Intent i = new Intent(Booth.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                final Intent intent;
                switch(position)
                {
                    case 0:
                        prefManager.setChose("1");
                        intent =  new Intent(Booth.this, MainActivity.class);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        Toast.makeText(Booth.this, " " + gridViewStrings[0], Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        prefManager.setChose("2");
                        intent =  new Intent(Booth.this, MainActivity.class);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                        Toast.makeText(Booth.this, " " + gridViewStrings[1], Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        intent =  new Intent(Booth.this, MainActivity.class);
                        break;
                }
                startActivity(intent);


            }
        });
    }
}
