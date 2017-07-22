package com.vineesh.suthanbathery.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
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
 * Created by vineesh on 05/07/2017.
 */

public class NewsAdapter extends BaseAdapter {

    private Activity activity;
    private List<Model> data;
    private static LayoutInflater inflater=null;



    public NewsAdapter(Activity activity, List data) {
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

        TextView newsheading;
        ImageView newsimage;


            if (convertView == null)
                vi = inflater.inflate(R.layout.news_single_item, null);

        newsimage = (ImageView) vi.findViewById(R.id.newsimage);// title
        newsheading = (TextView) vi.findViewById(R.id.newsheading);


        newsheading.setText(data.get(position).getName());
        Log.d("Description",data.get(position).getName());
        Log.d("Image_name",Constant.IMGE_URL+data.get(position).getImage_name());

        Glide.with(activity).load(Constant.IMGE_URL+data.get(position).getImage_name()).into(newsimage);




        return vi;
    }
}
