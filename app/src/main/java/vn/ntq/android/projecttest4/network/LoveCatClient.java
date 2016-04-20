package vn.ntq.android.projecttest4.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Allen on 19-Apr-16.
 */
public class LoveCatClient {
    public static final String base_url = "http://thecatapi.com/api/";

    public static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(base_url)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        return retrofit;
    }


}
