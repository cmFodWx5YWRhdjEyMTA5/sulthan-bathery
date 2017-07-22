package com.vineesh.suthanbathery.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vineesh on 04/07/2017.
 */

public class AreaAdapter extends BaseAdapter {

private Activity activity;
private List<Model> data;
private static LayoutInflater inflater=null;


public AreaAdapter(Activity activity, List data) {
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
        if(convertView==null)
        vi = inflater.inflate(R.layout.list_row, null);

       // TextView title = (TextView)vi.findViewById(R.id.title); // title

         /*   title = data.get(position);*/

        // Setting all values in listview


        return vi;
        }
        }