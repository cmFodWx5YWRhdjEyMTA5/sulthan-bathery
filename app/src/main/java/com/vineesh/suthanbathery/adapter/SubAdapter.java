package com.vineesh.suthanbathery.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vineesh.suthanbathery.InnerMainActivity;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.fragments.SubFragment;
import com.vineesh.suthanbathery.model.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vineesh on 04/07/2017.
 */


public class SubAdapter extends BaseAdapter {

    private Activity activity;
    private List<Model> data;
    private static LayoutInflater inflater=null;
    private int FRAGMENT_ID;


    public SubAdapter(Activity activity, List data, int FRAGMENT_ID) {
        this. activity = activity;
        this.data=data;
        this.FRAGMENT_ID=FRAGMENT_ID;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public SubAdapter() {

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

    public View getView(final int position, View convertView, ViewGroup parent) {


        View vi=convertView;
        TextView address,title,number,model;
        ImageView icon,callme;

        if (convertView == null)
            vi = inflater.inflate(R.layout.sublist_items, null);

        title = (TextView) vi.findViewById(R.id.name);
        number = (TextView) vi.findViewById(R.id.number);// title

        number.setText(data.get(position).getPhone());
        title.setText(data.get(position).getName());

           if (FRAGMENT_ID ==2 || FRAGMENT_ID == 8) {
                if (convertView == null)
                    vi = inflater.inflate(R.layout.bank_layout_contact, null);
                address = (TextView) vi.findViewById(R.id.address);// title
                title = (TextView) vi.findViewById(R.id.name);
                number = (TextView) vi.findViewById(R.id.number);// title

                address.setText(data.get(position).getAddress());
                title.setText(data.get(position).getName());
                number.setText(data.get(position).getPhone());


               callme = (ImageView) vi.findViewById(R.id.callme);

               callme.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Intent callIntent = new Intent(Intent.ACTION_CALL);
                       callIntent.setData(Uri.parse(data.get(position).getPhone()));
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


        if (FRAGMENT_ID == 1 || FRAGMENT_ID == 10 ||FRAGMENT_ID == 9){
            if (convertView == null)
                vi = inflater.inflate(R.layout.sublist_items, null);

            title = (TextView) vi.findViewById(R.id.name);
            number = (TextView) vi.findViewById(R.id.number);// title

            number.setText(data.get(position).getPhone());
            title.setText(data.get(position).getName());

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

        if (FRAGMENT_ID == 4){
            if (convertView == null)
                vi = inflater.inflate(R.layout.taxi_sub_layout, null);

            title = (TextView) vi.findViewById(R.id.name);
            model = (TextView) vi.findViewById(R.id.model);
            number = (TextView) vi.findViewById(R.id.number);// title

            number.setText(data.get(position).getPhone());
            title.setText(data.get(position).getName());
            model.setText(data.get(position).getModel());

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


    /*    if (FRAGMENT_ID == 7){
            if (convertView == null)
                vi = inflater.inflate(R.layout.shopecatogory, null);

            title = (TextView) vi.findViewById(R.id.name);
            title.setText(data.get(position).getName());


        }*/





         /*   title = data.get(position);*/

        // Setting all values in listview


        return vi;
    }
}
