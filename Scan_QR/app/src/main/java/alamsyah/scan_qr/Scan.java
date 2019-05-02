package alamsyah.scan_qr;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.internal.SnackbarContentLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;
import com.sdsmdg.tastytoast.TastyToast;

import alamsyah.scan_qr.api.ApiClient;
import alamsyah.scan_qr.api.ApiInterface;
import alamsyah.scan_qr.model.ResponsModel;
import cn.pedant.SweetAlert.SweetAlertDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    public ZXingScannerView mScannerView;
    PrefManager prefManager;
    public String message;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        prefManager = new PrefManager(this);
        pd = new ProgressDialog(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        finish();

        final String result = rawResult.getText().toString();
        mScannerView.resumeCameraPreview(this);
        String place = prefManager.getCHOSE();

        validasiStatus(result);
//        pd.setCancelable(false);
//        pd.setMessage("Loading...");
//        pd.show();

//        if (prefManager.getCHOSE().equals("absen")){
//            validasiStatus(result);
//        }else{
////
//////            validasiPlace(result,place);
//////            if (prefManager.getCHOSE().equals("1")){
//////                validasiPlace(result,place);
//////            }else if(prefManager.getCHOSE().equals("2")){
//////                dua(result,place);
//////            }else{
//////                Toast.makeText(Scan.this, "Not Valid", Toast.LENGTH_SHORT).show();
//////            }
//////            validasi(result,place);
////
//        }
    }

    public void validasiStatus(final String id){

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsModel> valid = api.validasiStatus(id);
        valid.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body().getKode();
                if (kode.equals("200")){

                    /*Validasi Data*/
                    UpdateData(id);
                    /*End*/

//                    Intent i = new Intent(Scan.this, alamsyah.scan_qr.Result.class);
//                    i.putExtra("id", id);
//                    startActivity(i);
                }else{
                    Log.d("Message","Data Sudah Ada!");
                    prefManager.setMessage("Data Sudah Ada!");
                    prefManager.setStatus("failed");
                    Intent i = new Intent(Scan.this, MainActivity.class);
                    startActivity(i);
//                    TastyToast.makeText(Scan.this, "Opps!, Data Sudah Ada.", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                Log.d("Message", "Check Your Connection");
//                Toast.makeText(Scan.this, "Check Your Connection!", Toast.LENGTH_SHORT).show();
                TastyToast.makeText(Scan.this, "Opps!, Periksa Koneksi Anda!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            }
        });
    }

    public void UpdateData(final String id){

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsModel> save = api.insertData(id);
        save.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                String kode = response.body().getKode();
                String nama = response.body().getNama();

                if (kode.equals("200")){
//                    pd.hide();
                    TastyToast.makeText(Scan.this, "Selamat Datang", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    Log.d("Message", "Success");
                    Intent i = new Intent(Scan.this, alamsyah.scan_qr.Result.class);
                    i.putExtra("id", id);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
//                    prefManager.setMessage("Data Tidak Ada!");
//                    prefManager.setStatus("failed");
//                    Intent i = new Intent(Scan.this, MainActivity.class);
//                    startActivity(i);
//                    pd.hide();
                    Log.d("Message", "Failed");
                    TastyToast.makeText(Scan.this, "Oopss!, Data Tidak Ada!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);


                }
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                Log.d("Message", "Check Your Connection");
                TastyToast.makeText(Scan.this, "Opps!, Periksa Koneksi Anda!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                Intent i = new Intent(Scan.this, MainActivity.class);
                startActivity(i);
//                pd.hide();
            }
        });
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Exit from Activity!")
                .setCancelText("No")
                .setConfirmText("Yes!")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        finish();
                        Intent i = new Intent(Scan.this, MainActivity.class);
                        startActivity(i);
                    }
                })
                .show();
    }
}
