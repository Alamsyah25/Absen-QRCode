package alamsyah.scan_qr.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 18/09/2018.
 */

public class ApiClient {
//    Local
//    private static final String base_url = "http://192.168.137.1:81/Android/qr/";
//    API
    //arsky-innovation.com/c0d3qr
    private static final String base_url = "http://arsky-innovation.com/c0d3qr/Post_tamu/";
//    private static final String base_url = "http://192.168.137.1:81/qrcode/Post_tamu/";
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
