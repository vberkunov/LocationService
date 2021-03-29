package ua.com.locationservice.entity;
import com.google.gson.annotations.*;
public class User {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("location")
    @Expose
    private String location;

    public User() {
    }

    public User(int id, String name, String email, String password, String location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }
}
