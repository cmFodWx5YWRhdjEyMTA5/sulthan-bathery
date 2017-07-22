package com.vineesh.suthanbathery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Icons;

import java.util.List;

/**
 * Created by vineesh on 12/07/2017.
 */

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    private List<Icons> albumList;

    public SpinnerAdapter() {
    }


    public SpinnerAdapter(Context context,List<Icons> albumList) {
        this.context = context;
        inflter = (LayoutInflater.from(context));
        this.albumList = albumList;
    }


    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {



        view = inflter.inflate(R.layout.custom_spinner, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(albumList.get(position).getCover_img());
        names.setText(albumList.get(position).getTitle());
        return view;
    }
}
