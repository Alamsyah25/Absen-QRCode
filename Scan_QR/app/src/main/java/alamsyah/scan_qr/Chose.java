package alamsyah.scan_qr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class Chose extends AppCompatActivity {
    CardView absen,satu,dua;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        absen = findViewById(R.id.absen);
        satu = findViewById(R.id.satu);
        dua = findViewById(R.id.dua);
        prefManager = new PrefManager(this);

        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setChose("absen");
                Intent i = new Intent(Chose.this, MainActivity.class);
                startActivity(i);
            }
        });

        satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setChose("1");
                Intent i = new Intent(Chose.this, MainActivity.class);
                startActivity(i);
            }
        });

        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.setChose("2");
                Intent i = new Intent(Chose.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
