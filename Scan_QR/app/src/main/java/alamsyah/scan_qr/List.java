package alamsyah.scan_qr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import alamsyah.scan_qr.Adapter.AdapterData;
import alamsyah.scan_qr.api.ApiClient;
import alamsyah.scan_qr.api.ApiInterface;
import alamsyah.scan_qr.model.ResponsModel;
import alamsyah.scan_qr.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private java.util.List<User> mItems = new ArrayList<>();
    ProgressDialog pd;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        done = findViewById(R.id.done);
        pd = new ProgressDialog(this);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(List.this, MainActivity.class);
                startActivity(i);
            }
        });
        Refresh();
    }

    public void Refresh(){

        mRecycler = (RecyclerView) findViewById(R.id.list);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsModel> getdata = api.getBiodata();
        getdata.enqueue(new Callback<ResponsModel>() {
            @Override
            public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                pd.hide();
                Log.d("Message", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(List.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModel> call, Throwable t) {
                pd.hide();
                Log.d("Message", "RESPONSE : Failed");

            }
        });
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent i = new Intent(List.this, MainActivity.class);
        startActivity(i);
    }
}
