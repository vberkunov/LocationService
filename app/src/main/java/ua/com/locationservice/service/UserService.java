package ua.com.locationservice.service;

import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ua.com.locationservice.entity.LoginRequest;
import ua.com.locationservice.entity.SignUpRequest;
import ua.com.locationservice.entity.User;

public interface UserService {

    @GET("user/")
    Call<List<User>> getUsers();

    @POST("add/")
    Call<User> addUser(@Body User user);

    @PUT("update/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("delete/{id}")
    Call<User> deleteUser(@Path("id") int id);

    @POST
    Call<User> signUp (@Body SignUpRequest request);

    @POST("/login")
    Call<User> basicLogin();
    @GET("/getPass")
    Call<User> getPassByEmail(@Body String emailId);
}