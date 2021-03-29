package ua.com.locationservice.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import ua.com.locationservice.R;

public class BoxAdapter extends ArrayAdapter<String> {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<String> locations;

    public BoxAdapter(Context context, ArrayList<String> locations) {
        super(context,R.layout.item, locations);

        this.ctx = context;
        this.locations = locations;
        this.lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View rowView = lInflater.inflate(R.layout.item, null, true);
        TextView name = rowView.findViewById(R.id.nameOfLocation);
        Button confirm = rowView.findViewById(R.id.choiceBtn);
        name.setText(locations.get(position));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm.setText("done");


            }
        });

        return rowView;
    }





}
