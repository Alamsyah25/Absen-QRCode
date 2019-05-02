package alamsyah.scan_qr.api;

import alamsyah.scan_qr.model.ResponsModel;
import alamsyah.scan_qr.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ASUS on 18/09/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendData(@Field("nama") String nama);

    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel>  deleteData(@Field("id") String id);

/*API Local*/

//    @FormUrlEncoded
//    @POST("save.php")
//    Call<ResponsModel> insertData(@Field("id") String id);
//
//
//    @FormUrlEncoded
//    @POST("result.php")
//    Call<ResponsModel> resultData(@Field("id") String id);
//
//    @FormUrlEncoded
//    @POST("validasi.php")
//    Call<ResponsModel> validasi(@Field("id") String id);



    /*API*/
    @FormUrlEncoded
    @POST("update")
    Call<ResponsModel> insertData(@Field("hash_code") String hash_code);

    @FormUrlEncoded
    @POST("selectWhere")
    Call<ResponsModel> resultData(@Field("hash_code") String hash_code);

    @FormUrlEncoded
    @POST("place")
    Call<ResponsModel> insertTmp(@Field("hash_code") String hash_code,
                                 @Field("place") String place);

    @FormUrlEncoded
    @POST("validasiPlace")
    Call<ResponsModel> validasi(@Field("hash_code") String hash_code,
                                @Field("place") String place);

    @FormUrlEncoded
    @POST("validasiStatus")
    Call<ResponsModel> validasiStatus(@Field("hash_code") String hash_code);


}
