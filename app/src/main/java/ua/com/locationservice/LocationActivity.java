package ua.com.locationservice;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.com.locationservice.adapter.DrawerItemCustomAdapter;
import ua.com.locationservice.entity.Location;
import ua.com.locationservice.fragments.MapFragment;
import ua.com.locationservice.location.LocationProvider;
import ua.com.locationservice.retrofit.RetrofitClient;
import ua.com.locationservice.service.LocationService;
import ua.com.locationservice.utils.Utils;

public class LocationActivity extends AppCompatActivity  {

    private static FragmentManager fragmentManager;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    ActionBarDrawerToggle mDrawerToggle;
    private String email;
    private List<Location> list = new ArrayList<>();
    private int position = 0;
    private LocationProvider provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            email = extras.getString("email");
        }

        getLocationInfo();
        provider = LocationProvider.getInstanse();
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_row_item_row, list);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    private void getLocationInfo() {
        LocationService locService =
                RetrofitClient.createLocationService();
        Call<List<Location>> call = locService.getLocationByEmail();
        call.enqueue(new Callback<List<Location>>() {


            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                for (Location l: response.body()) {
                    list.add(l);
                    provider.setPosition(l.getTag());
                }
                onLocationAvailable();

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable throwable) {
                System.out.println(throwable.getMessage() );
            }
        });

    }


    public void onLocationAvailable() {
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_row_item_row, list);
        mDrawerList.setAdapter(adapter);
        Bundle bundle = new Bundle();
        bundle.putInt("tagId", list.get(position).getTag().getId());
        bundle.putString("name", list.get(position).getName());
        bundle.putDouble("width", list.get(position).getWidth());
        bundle.putDouble("height", list.get(position).getHeight());
        bundle.putString("tag", list.get(position).getTag().getName());
        MapFragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment,
                            Utils.GameFragment).commit();
        }



    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        this.position = position;
        onLocationAvailable();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    public void menuClick(View v){
        mDrawerLayout.openDrawer(mDrawerList);
    }
}