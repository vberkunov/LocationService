package ua.com.locationservice.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.locationservice.service.LocationService;
import ua.com.locationservice.service.UserService;
import ua.com.locationservice.utils.ApiUtils;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String url){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static UserService getUserService(){
        UserService userService = getRetrofit(ApiUtils.API_URL).create(UserService.class);
        return userService;
    }

    public static LocationService getLocationService(){
        LocationService userService = getRetrofit(ApiUtils.API_URL).create(LocationService.class);
        return userService;
    }
}
