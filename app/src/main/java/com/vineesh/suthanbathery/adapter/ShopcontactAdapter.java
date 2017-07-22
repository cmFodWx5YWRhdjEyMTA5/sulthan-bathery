package com.vineesh.suthanbathery.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Model;

import java.util.List;

/**
 * Created by vineesh on 11/07/2017.
 */

public class ShopcontactAdapter extends BaseAdapter {

    private Activity activity;
    private List<Model> data;
    private static LayoutInflater inflater=null;
    private String KEY;


    public ShopcontactAdapter (Activity activity, List data,String KEY) {
        this. activity = activity;
        this.data=data;
        this.KEY = KEY;

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
        TextView address,title,number,model;
        ImageView icon,callme;

        if (KEY.equals("SHOP") || KEY.equals("BLOOD"))
        {
            if (convertView == null)
                vi = inflater.inflate(R.layout.sublist_items, null);

            title = (TextView) vi.findViewById(R.id.name);
            title.setText(data.get(position).getName());
            number = (TextView) vi.findViewById(R.id.number);
            number.setText(data.get(position).getPhone());

            callme = (ImageView) vi.findViewById(R.id.callme);
            callme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:123456789"));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    activity.startActivity(callIntent);
                }
            });
        }

        else if (KEY.equals("EDUCATION"))
        {
            if (convertView == null)
                vi = inflater.inflate(R.layout.bank_layout_contact, null);

            title = (TextView) vi.findViewById(R.id.name);
            title.setText(data.get(position).getName());
            number = (TextView) vi.findViewById(R.id.number);
            number.setText(data.get(position).getPhone());
            address = (TextView) vi.findViewById(R.id.address);
            address.setText(data.get(position).getAddress());

            callme = (ImageView) vi.findViewById(R.id.callme);
            callme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:123456789"));
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    activity.startActivity(callIntent);
                }
            });
        }







         /*   title = data.get(position);*/

        // Setting all values in listview


        return vi;
    }
}

