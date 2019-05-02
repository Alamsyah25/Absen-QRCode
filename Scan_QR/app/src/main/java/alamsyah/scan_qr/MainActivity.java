package alamsyah.scan_qr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;
import java.util.List;

import alamsyah.scan_qr.R;
import alamsyah.scan_qr.Adapter.AdapterData;
import alamsyah.scan_qr.Scan;
import alamsyah.scan_qr.api.ApiClient;
import alamsyah.scan_qr.api.ApiInterface;
import alamsyah.scan_qr.model.ResponsModel;
import alamsyah.scan_qr.model.User;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<User> mItems = new ArrayList<>();
    ProgressDialog pd;
    Button add;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PrefManager
//        prefManager.setChose("absen");
//        ==
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefManager = new PrefManager(this);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String place = prefManager.getCHOSE();
        pd = new ProgressDialog(this);
        add = (Button) findViewById(R.id.addData);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Scan.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        String pesan = prefManager.getMESSAGE();
        String status = prefManager.getStatus();

        if(status.equals("failed")){
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(pesan)
                    .show();
            prefManager.setStatus("empty");
            prefManager.setMessage("empty");
        }else if(status.equals("success")){
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success!")
                    .setContentText(pesan)
                    .show();
            prefManager.setStatus("empty");
            prefManager.setMessage("empty");
        }else{
            Log.d("Message", "Pesan");
        }

//        Refresh();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.info) {
            new FancyGifDialog.Builder(this)
                    .setTitle("AR&Co")
                    .setMessage("Copyright 2018 (c) All Right Reserved. \n ")
//                    .setNegativeBtnText("Cancel")
                    .setPositiveBtnBackground("#FF4081")
                    .setPositiveBtnText("Ok")
//                    .setNegativeBtnBackground("#FFA9A7A8")
//                    .setGifResource(R.drawable.gif)
                    .setGifResource(R.drawable.gif_font)
//                    .isCancellable(true)
                    .OnPositiveClicked(new FancyGifDialogListener() {
                        @Override
                        public void OnClick() {
//                            Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        }
                    })
//                    .OnNegativeClicked(new FancyGifDialogListener() {
//                        @Override
//                        public void OnClick() {
//                            Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
//                        }
//                    })
                    .build();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Exit from Application")
                .setCancelText("No")
                .setConfirmText("Yes!")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        finish();
                        prefManager.setChose("empty");
                        prefManager.setMessage("empty");
                        prefManager.setStatus("empty");
                        MainActivity.this.finishAffinity();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .show();

//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        finish();
//                        prefManager.setChose("empty");
//                        prefManager.setMessage("empty");
//                        prefManager.setStatus("empty");
//                        MainActivity.this.finishAffinity();
//                        android.os.Process.killProcess(android.os.Process.myPid());
//                    }
//                }).create().show();
    }

}
