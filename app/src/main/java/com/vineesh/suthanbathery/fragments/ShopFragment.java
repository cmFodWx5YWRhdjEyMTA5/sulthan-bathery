package com.vineesh.suthanbathery.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vineesh.suthanbathery.InnerMainActivity;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.adapter.ShopAdapter;
import com.vineesh.suthanbathery.adapter.SubAdapter;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopFragment extends Fragment {

    public ShopFragment() {
        // Required empty public constructor
    }
    public  int internet;
    int areaid;
    ApiClient mApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ProgressDialog progressDoalog;
    ListView list;
    List<Model> autoitems,shopsubitems;
    ShopAdapter adapter;
    public  String KEY;
    int FRAGMENT_ID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_shop, container, false);



        mApiClient = new ApiClient();
        cd = new ConnectionDetector(getActivity());



        autoitems = (List<Model>) getArguments().getSerializable("arraylist");


        list = (ListView)root.findViewById(R.id.listview);


        adapter=new ShopAdapter(getActivity(), autoitems);
        list.setAdapter(adapter);


       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {





                if (checkConnectivity() == 1) {
                    //do something

                    int selected_ID = Integer.parseInt(autoitems.get(position).getId());

                    getShopSubItems(selected_ID);

                    Log.d("selected_id", String.valueOf(selected_ID));

                }
                else
                {
                    Toast.makeText(getContext(), "No Internet", Toast.LENGTH_SHORT).show();
                }



            }
        });


        Fragment f = new SubDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("arraylist", (Serializable) shopsubitems);
        f.setArguments(b);

        ((InnerMainActivity)getActivity()).LoadFragments(f);*/


        return root;
    }

    private void getShopSubItems(int selected_id) {

        Log.d("selected_id2", String.valueOf(selected_id));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            ((InnerMainActivity)getActivity()).progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getShopSubCategory(selected_id);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        ((InnerMainActivity)getActivity()).prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        shopsubitems =response.body().getSubShoCategory();

                        Log.w("autoitemsshopdetails", String.valueOf(shopsubitems.size()));

                        Toast.makeText(getActivity(),"autoitemsshopdetails",Toast.LENGTH_LONG).show();





                    } else {
                        int sc = response.code();
                        switch (sc) {
                            case 400:
                                Log.e("Error 400", "Bad Request");
                                break;
                            case 404:
                                Log.e("Error 404", "Not Found");
                                break;
                            default:
                                Log.e("Error", "Generic Error");
                        }

                    }
                    ((InnerMainActivity)getActivity()).prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    ((InnerMainActivity)getActivity()).prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }


    }

    public int checkConnectivity() {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            internet = 0;//sin conexion
            Toast.makeText(getActivity(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            enabled = false;
        } else {
            internet = 1;//conexión
        }

        return internet;
    }


}
