package ua.com.locationservice.entity;

public class SignUpRequest {
    private String fullName;
    private String email;
    private String phone;
    private String city;
    private String password;

    public SignUpRequest(String fullName, String email, String phone, String city, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
