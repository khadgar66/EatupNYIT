package com.teamproject_recomm.lxl.teamapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by lxl on 5/2/2017.
 */

public class PopupAdapter implements GoogleMap.InfoWindowAdapter {
    private View popup = null;
    private LayoutInflater inflater= null;
    private Context context = null;

    PopupAdapter(LayoutInflater inflater, Context context) {
        this.inflater=inflater;
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return(null);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup = inflater.inflate(R.layout.popup, null);
        }
        ImageView iv = (ImageView) popup.findViewById(R.id.image);
        int[] images= {R.drawable.one, R.drawable.two};
        iv.setImageResource(images[(int)(Math.random()*2)]);
        TextView tv = (TextView)popup.findViewById(R.id.name);
        tv.setText(marker.getTitle());
        String[] data = marker.getSnippet().split("&&");
        tv = (TextView)popup.findViewById(R.id.snippet);
        tv.setText(data[0]);
        tv = (TextView)popup.findViewById(R.id.price);
        tv.setText(data[1]);
        tv = (TextView)popup.findViewById(R.id.rating);
        tv.setText(data[2]+"/5");
        return (popup);
    }
}

