package tv.kinochi.kinochi.model.api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class ApiFactory {

    private static final boolean ENABLE_LOG = true;

    private static final int CONNECT_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 60;
    private static final int TIMEOUT = 60;



    private static final OkHttpClient httpClient = new OkHttpClient();


    static {
        httpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClient.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        httpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    @NonNull
    public static ApiInterface getApiInterface(String apiEndPoint) {
        return createApiInterface(null, null, apiEndPoint);
    }

    public static ApiInterface createApiInterface(String login, String password, String apiEndpoint) {

        if (login != null && password != null) {
            String credentials = login + ":" + password;
            final String basic =
                    "Basic " + HashPassword.getSha256(credentials);
            httpClient.interceptors().add(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Basic " + basic)
                        //.header("X-CSRF-Token", ) //токен должен быть в хедере обязательно аутентификация будет делаться через простой POST-запрос с POJO-объектом
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            });
        }


        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);
        }


        return getRetrofit(httpClient, apiEndpoint).create(ApiInterface.class);

    }

    @NonNull
    private static Retrofit getRetrofit(OkHttpClient httpClient, String apiEndpoint) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiEndpoint)
                .client(httpClient)
                .build();
    }

}
