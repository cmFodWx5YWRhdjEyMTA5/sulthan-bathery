package com.vineesh.suthanbathery.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.utils.Constant;

import java.util.List;

/**
 * Created by vineesh on 06/07/2017.
 */

public class Feedback_adapter extends BaseAdapter {

    private Activity activity;
    private List<Model> data;
    private static LayoutInflater inflater=null;



    public Feedback_adapter(Activity activity, List data) {
        this. activity = activity;
        this.data=data;

        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View vi=convertView;
        TextView name,message;



        if (convertView == null)
            vi = inflater.inflate(R.layout.feedback_item_list, null);


        name = (TextView) vi.findViewById(R.id.name);
        message = (TextView) vi.findViewById(R.id.message);


        name.setText(data.get(position).getName());
        message.setText(data.get(position).getMessage());


        return vi;
    }
}
