package ua.com.locationservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ua.com.locationservice.R;
import ua.com.locationservice.entity.Location;

public class DrawerItemCustomAdapter extends ArrayAdapter<Location> {
    Context mContext;
    int layoutResourceId;
    List <Location> locations  = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, List<Location> locations) {

        super(mContext, layoutResourceId, locations);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.locations = locations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        Location folder = locations.get(position);

        textViewName.setText(folder.name);

        return listItem;
    }
}
