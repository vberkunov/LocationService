package ua.com.locationservice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ua.com.locationservice.R;
import ua.com.locationservice.ui.MapView;

public class MapFragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static FragmentManager fragmentManager;
    private static MapView mapView;
    private static LinearLayout linearLayout;

    public MapFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void setListeners() {
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        linearLayout = view.findViewById(R.id.location);
        mapView = new MapView(view.getContext());
        linearLayout.addView(mapView);


    }


    @Override
    public void onClick(View v) {

    }
}
