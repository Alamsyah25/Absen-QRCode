package alamsyah.scan_qr.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import alamsyah.scan_qr.MainActivity;
import alamsyah.scan_qr.Scan;
import alamsyah.scan_qr.R;
import alamsyah.scan_qr.api.ApiClient;
import alamsyah.scan_qr.api.ApiInterface;
import alamsyah.scan_qr.model.ResponsModel;
import alamsyah.scan_qr.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 13/09/2018.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private List<User> mList ;
    private Context ctx;

    public AdapterData(Context ctx, List<User> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        User dm = mList.get(position);
        holder.nama.setText(dm.getNama());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends RecyclerView.ViewHolder{
        TextView nama;
        User dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.tvNama);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final CharSequence[] dialogitem = {"Lihat Biodata", "Hapus Biodata"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int item) {
                            switch (item) {
                                case 0:
                                    Toast.makeText(ctx, "Nama " + dm.getNama().toString(), Toast.LENGTH_SHORT).show();
                                    Log.d("Message", "Nama = " + dm.getNama().toString());
                                    break;
                                case 1:
                                    ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
                                    Call<ResponsModel> del = api.deleteData(dm.getId());
                                    del.enqueue(new Callback<ResponsModel>() {
                                        @Override
                                        public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                                            String kode = response.body().getKode();
                                            if (kode.equals("1")){
                                                Toast.makeText(ctx, "Success", Toast.LENGTH_SHORT).show();
                                                Intent refresh = new Intent(ctx, alamsyah.scan_qr.List.class);
                                                ctx.startActivity(refresh);

                                            }else{
                                                Toast.makeText(ctx, "Failed", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ResponsModel> call, Throwable t) {
                                            Toast.makeText(ctx, "No Id", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
        }
    }
}