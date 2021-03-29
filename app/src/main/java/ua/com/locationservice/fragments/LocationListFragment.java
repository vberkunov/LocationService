package ua.com.locationservice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import ua.com.locationservice.R;
import ua.com.locationservice.adapter.BoxAdapter;
import ua.com.locationservice.utils.Utils;

public class LocationListFragment extends Fragment implements View.OnClickListener {

    private static View view;
    private ArrayList<String> locations = new ArrayList<>();
    private BoxAdapter boxAdapter;
    private Button chooseButton;
    private ListView lvMain;
    private static FragmentManager fragmentManager;
    
    public LocationListFragment(){
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_of_location, container, false);
        fillData();
        initViews();
        setListeners();


        return view;
    }

    private void setListeners() {
        chooseButton.setOnClickListener(this);
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        boxAdapter = new BoxAdapter(view.getContext(), locations);
        chooseButton = view.findViewById(R.id.chooseBtn);
        lvMain = view.findViewById(R.id.list);
        lvMain.setAdapter(boxAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseBtn:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.frameContainer,
                                new ConfirmFragment(),
                                Utils.ConfirmFragment).commit();
                break;
        }

        
    }


    void fillData() {
        for (int i = 1; i <= 3; i++) {
            locations.add("Location " + i);

        }
    }
}
