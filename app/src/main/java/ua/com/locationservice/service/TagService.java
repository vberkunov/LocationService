package ua.com.locationservice.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;

import retrofit2.http.Path;

import ua.com.locationservice.entity.Tag;
import ua.com.locationservice.entity.User;

public interface TagService {
    @GET("tag/")
    Call<List<User>> getTags();

    @GET("tag/{id}")
    Call<User> getTagByID(@Path("id") int id, @Body Tag tag);

}
