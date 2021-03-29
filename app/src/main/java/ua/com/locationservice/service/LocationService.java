package ua.com.locationservice.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ua.com.locationservice.entity.Location;
import ua.com.locationservice.entity.Tag;
import ua.com.locationservice.entity.User;

public interface LocationService {

    @GET("locations/")
    Call<List<Location>> getLocations();

    @GET("location/{id}")
    Call<User> getLocationByID(@Path("id") int id, @Body Tag tag);
}
