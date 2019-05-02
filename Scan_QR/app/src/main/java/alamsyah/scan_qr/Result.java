package alamsyah.scan_qr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.*;
import java.util.List;

import alamsyah.scan_qr.api.ApiClient;
import alamsyah.scan_qr.api.ApiInterface;
import alamsyah.scan_qr.model.ResponsModel;
import alamsyah.scan_qr.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result extends AppCompatActivity {
    TextView nama,company,email,tgl_hadir;
    CardView done;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        pd = new ProgressDialog(this);
        nama = findViewById(R.id.tvNama);
        company = findViewById(R.id.company);
        email = findViewById(R.id.email);
        tgl_hadir = findViewById(R.id.tgl_hadir);
        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                TastyToast.makeText(Result.this, "Terima Kasih", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            }
        });
        result();
    }
// Update Data Tamu
    public void submit(){
        Intent data = getIntent();
        final String id = data.getStringExtra("id");

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsModel> submit = api.insertData(id);
        submit.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body().getKode();
                if (kode.equals("1")){
                    Intent i = new Intent(Result.this, MainActivity.class);
                    startActivity(i);
                    TastyToast.makeText(Result.this, "Terima Kasih", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                }else{
                    Intent i = new Intent(Result.this, MainActivity.class);
                    startActivity(i);
                    TastyToast.makeText(Result.this, "Opps!, Terjadi Kesalahan", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                TastyToast.makeText(Result.this, "Opps!, Terjadi Kesalahan", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            }
        });
    }

//    Result Data Tamu
    public void result(){

        Intent data = getIntent();
        String id = data.getStringExtra("id");

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsModel> up = api.resultData(id);
        up.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                      String Skode = response.body().getKode();

//                        Toast.makeText(Result.this, "ID : " + id, Toast.LENGTH_SHORT).show();
                        String Sname = response.body().getNama();
                        String Semail = response.body().getEmail();
                        String Scompany = response.body().getCompany();
                        String Stgl_hadir = response.body().getTgl_hadir();
                        //nama.setText(Sname);
                        company.setText(Scompany);
                        email.setText(Semail);
                        tgl_hadir.setText(Stgl_hadir);
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
//                Toast.makeText(Result.this, "Oops!, Data Tidak Ada", Toast.LENGTH_SHORT).show();
                TastyToast.makeText(Result.this, "Oopss!, Data Tidak Ada!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                Intent i = new Intent(Result.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent i = new Intent(Result.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

}
