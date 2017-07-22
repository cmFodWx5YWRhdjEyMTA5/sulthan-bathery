package com.vineesh.suthanbathery.fragments;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.vineesh.suthanbathery.adapter.SubAdapter;
import com.vineesh.suthanbathery.adapter.SubDetailsAdapter;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubDetailFragment extends Fragment {

    int areaid;
    ApiClient mApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ProgressDialog progressDoalog;
    ListView list;
    List<Model> autoitems,shopsubitems;
    SubDetailsAdapter adapter;
    public  String KEY;
    int FRAGMENT_ID;


    public SubDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sub_detail, container, false);

        mApiClient = new ApiClient();
        cd = new ConnectionDetector(getActivity());


        autoitems = (List<Model>) getArguments().getSerializable("arraylist");
        Log.d("arraylistsubshop", String.valueOf(autoitems.size()));
        KEY = getArguments().getString("KEY");
       // FRAGMENT_ID = getArguments().getInt("FRAGMENT_ID");

        list = (ListView)root.findViewById(R.id.listauto);


        adapter=new SubDetailsAdapter(getActivity(), autoitems,KEY);
        list.setAdapter(adapter);

        if (KEY.equals("EDUCATION"))
        {
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int Selected_ID = Integer.parseInt(autoitems.get(position).getId());

                    getContacts(Selected_ID);
                }
            });
        }

        if ( KEY.equals("SHOP"))
        {
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int Selected_ID = Integer.parseInt(autoitems.get(position).getId());

                    getShopContacts(Selected_ID);
                }
            });
        }






        return root;
    }

    private void getShopContacts(int Selected_ID) {

        Log.d("selected_id2", String.valueOf(Selected_ID));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            ((InnerMainActivity)getContext()). progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getShopContacts(Selected_ID,1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        ((InnerMainActivity)getContext()). prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank2:"+statusCode);

                        shopsubitems =response.body().getShop();
                        if (shopsubitems.size() != 0)
                        {
                            CallTheShopFragment("SHOP");
                        }
                        else {
                            Toast.makeText(getActivity(),"There is no Items to Display",Toast.LENGTH_LONG).show();
                        }

                       /* Log.w("hai","shopsubitems size: "+shopsubitems.size());

                        Toast.makeText(getActivity(),"autoitemsshopdetails",Toast.LENGTH_LONG).show();*/



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
                    ((InnerMainActivity)getContext()). prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    ((InnerMainActivity)getContext()). prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }

    private void CallTheShopFragment(String title) {

        Fragment f = new ShopContactFragment();
        Bundle b = new Bundle();
        b.putSerializable("arraylist", (Serializable) shopsubitems);
        f.setArguments(b);
        b.putString("KEY",title);
        Log.d("arraylistshopes", String.valueOf(shopsubitems.size()));
        ((InnerMainActivity)getContext()).LoadFragments(f);
    }


    public void CallTheFragment(String title) {


        Fragment f = new ShopContactFragment();
        Bundle b = new Bundle();
        b.putSerializable("arraylist", (Serializable) shopsubitems);
        f.setArguments(b);
        b.putString("KEY",title);
        Log.d("arraylistshopes", String.valueOf(shopsubitems.size()));
        ((InnerMainActivity)getContext()).LoadFragments(f);

    }


    private void getContacts(int Selected_ID) {

        Log.d("selected_id2", String.valueOf(Selected_ID));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            ((InnerMainActivity)getContext()). progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getEducationContacts(Selected_ID,1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        ((InnerMainActivity)getContext()). prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank2:"+statusCode);

                        shopsubitems =response.body().getEducationcontact();
                        if (shopsubitems.size() != 0)
                        {
                            CallTheFragment("EDUCATION");
                        }
                        else {
                            Toast.makeText(getActivity(),"There is no Items to Display",Toast.LENGTH_LONG).show();
                        }

                       /* Log.w("hai","shopsubitems size: "+shopsubitems.size());

                        Toast.makeText(getActivity(),"autoitemsshopdetails",Toast.LENGTH_LONG).show();*/



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
                    ((InnerMainActivity)getContext()). prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    ((InnerMainActivity)getContext()). prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }


    }



}
