package ua.com.locationservice.location;

import android.icu.text.Transliterator;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.com.locationservice.entity.Location;
import ua.com.locationservice.entity.Position;
import ua.com.locationservice.entity.Tag;
import ua.com.locationservice.retrofit.RetrofitClient;
import ua.com.locationservice.service.TagService;

public class LocationProvider {

    private HashMap<Integer, Position> history;

    private static LocationProvider instanse = null;


    private LocationProvider (){
        this.history = new HashMap<>();
    }

    public static LocationProvider getInstanse(){
        if(instanse == null) {
            instanse = new LocationProvider();
        }
        return instanse;
    }


        public Position getCurrentPosition(int id){
            return history.get(id);
        }

    public void setPosition(Tag tag) {
        history.put(tag.getId(), new Position(tag.getX(), tag.getY()));
    }
}