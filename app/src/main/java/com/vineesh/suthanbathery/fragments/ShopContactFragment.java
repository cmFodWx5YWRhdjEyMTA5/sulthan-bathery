package com.vineesh.suthanbathery.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.adapter.ShopcontactAdapter;
import com.vineesh.suthanbathery.adapter.SubDetailsAdapter;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.util.List;

public class ShopContactFragment extends Fragment {


    public ShopContactFragment() {
        // Required empty public constructor
    }
    int areaid;
    ApiClient mApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ProgressDialog progressDoalog;
    ListView list;
    List<Model> autoitems,shopsubitems;
    ShopcontactAdapter adapter;
    public  String KEY;
    int FRAGMENT_ID;

    String number,name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_shop_contact, container, false);
        mApiClient = new ApiClient();
        cd = new ConnectionDetector(getActivity());


        autoitems = (List<Model>) getArguments().getSerializable("arraylist");
        Log.d("arraylistsubshop", String.valueOf(autoitems.size()));
        // FRAGMENT_ID = getArguments().getInt("FRAGMENT_ID");

        list = (ListView)root.findViewById(R.id.listshopdetails);
        KEY = getArguments().getString("KEY");


        adapter=new ShopcontactAdapter(getActivity(), autoitems,KEY);
        list.setAdapter(adapter);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, final View v,
                                           final int index, long arg3) {
                number = autoitems.get(index).getPhone();
                name = autoitems.get(index).getName();
                showPopupMenu(v);


                /*final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Do you want to save this number")
                        .setMessage(autoitems.get(index).getPhone())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //  deleteSuggestions(position);

                                Intent addContactIntent = new Intent(Intent.ACTION_INSERT);
                                addContactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                                addContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE,autoitems.get(index).getPhone());
                                startActivity(addContactIntent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();*/


                //Toast.makeText(getContext(),autoitems.get(index).getPhone(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return root;
    }

    private void showPopupMenu(View v) {


        PopupMenu popup = new PopupMenu(getActivity(), v,Gravity.CENTER);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.call_and_save_layout, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());


        popup.show();
    }


    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.save:
                    Intent addContactIntent = new Intent(Intent.ACTION_INSERT);
                    addContactIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                    addContactIntent.putExtra(ContactsContract.Intents.Insert.PHONE,number);
                    addContactIntent.putExtra(ContactsContract.Intents.Insert.NAME,name);
                    startActivity(addContactIntent);

                    //Toast.makeText(getContext(), "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.call:
                    //Toast.makeText(getContext(), "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}
