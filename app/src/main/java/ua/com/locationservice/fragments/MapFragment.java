package ua.com.locationservice.fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ua.com.locationservice.R;
import ua.com.locationservice.entity.Position;
import ua.com.locationservice.location.LocationProvider;
import ua.com.locationservice.ui.MapView;

public class MapFragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static FragmentManager fragmentManager;
    private static MapView mapView;
    private static RelativeLayout map;
    private static TextView name;
    private static TextView tvWidth;
    private static TextView tvLength;
    private static TextView tvTag;
    private static ImageView imageMarker;
    private static RelativeLayout layoutImg;
    private LocationProvider provider;
    private long width;
    private long height;

    public MapFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location_layout, container, false);
        provider = LocationProvider.getInstanse();
        Bundle args = getArguments();
        int tagId = args.getInt("tagId");
        String locName = args.getString("name");
        String width = String.valueOf(args.getDouble("width"));
        String height = String.valueOf( args.getDouble("height"));
        String tag = args.getString("tag");
        initViews(locName, width, height,tag, view);
        updateLocation(width, height);
        updateObjectPosition(tagId);

        setListeners();
        return view;
    }

    private void updateObjectPosition(int tagId) {
       Position current =  provider.getCurrentPosition(tagId);

        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) layoutImg.getLayoutParams();
        imageParams.leftMargin =(int) current.getX();
        imageParams.topMargin = (int) current.getY();

    }

    private void updateLocation(String widthStr, String heightStr) {
        width = Math.round(Double.parseDouble(widthStr));
        height = Math.round(Double.parseDouble(heightStr));
        long ratio = height/width;
        ViewGroup.LayoutParams params = map.getLayoutParams();
        params.width = map.getMeasuredWidth();
        if(width==height){
        params.height = params.width;

        }
        if(width> height){
            params.height = (int)(params.width + (ratio*100));
        }
        if(width< height){
            params.height = (int)(params.width + (ratio*100));
        }
    }

    private void setListeners() {
    }

    private void initViews(String locName, String width, String height, String tag, View v) {
        fragmentManager = getActivity().getSupportFragmentManager();
        map = v.findViewById(R.id.map);
        name = (TextView) v.findViewById(R.id.name);
        name.setText(locName);
        tvWidth = v.findViewById(R.id.width);
        tvWidth.setText(width);
        tvLength = v.findViewById(R.id.lenth);
        tvLength.setText(height);
        tvTag = v.findViewById(R.id.tag);
        tvTag.setText(tag);
        imageMarker = v.findViewById(R.id.imgMarker);
        layoutImg = v.findViewById(R.id.layoutImg);





    }


    @Override
    public void onClick(View v) {

    }
}
