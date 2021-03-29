package ua.com.locationservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.com.locationservice.entity.LoginRequest;
import ua.com.locationservice.entity.SignUpRequest;
import ua.com.locationservice.entity.User;
import ua.com.locationservice.fragments.ConfirmFragment;
import ua.com.locationservice.fragments.LoginFragment;
import ua.com.locationservice.fragments.MapFragment;
import ua.com.locationservice.fragments.SignUpFragment;
import ua.com.locationservice.retrofit.RetrofitClient;
import ua.com.locationservice.utils.Utils;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener {

    private static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new LoginFragment(),
                            Utils.LoginFragment).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });

    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_exit)
                .replace(R.id.frameContainer, new LoginFragment(),
                        Utils.LoginFragment).commit();
    }

    public void replaceMapFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_exit)
                .replace(R.id.frameContainer, new MapFragment(),
                        Utils.MapFragment).commit();
    }

    public void replaceConfirmFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_exit)
                .replace(R.id.frameContainer, new ConfirmFragment(),
                        Utils.ConfirmFragment).commit();
    }


    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUpFragment = fragmentManager
                .findFragmentByTag(Utils.SignUpFragment);
        Fragment ForgotPasswordFragment = fragmentManager
                .findFragmentByTag(Utils.ForgotPasswordFragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUpFragment != null)
            replaceLoginFragment();
        else if (ForgotPasswordFragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }


    public void login(String email, String password){

        LoginRequest loginRequest = new LoginRequest(email, password);
        Call<User> loginResponse = RetrofitClient.getUserService().userLogin(loginRequest);
        loginResponse.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                        replaceMapFragment();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signUp(String fullName, String email, String phone, String city, String password){

        SignUpRequest signUpRequest= new SignUpRequest(fullName, email, phone, city, password);
        Call<User> loginResponse = RetrofitClient.getUserService().signUp(signUpRequest);
        loginResponse.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    replaceConfirmFragment();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoginFragmentInteraction(String email, String password) {
        login(email, password);
    }


    @Override
    public void onSignUpFragmentInteraction(String fullName, String email, String phone, String city, String password) {
        signUp(fullName, email, phone, city, password);
    }
}