package com.vineesh.suthanbathery.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
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
import com.vineesh.suthanbathery.adapter.BloodAdapter;
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


public class SubFragment extends Fragment {



    public SubFragment() {
        // Required empty public constructor
    }



    int areaid;
    ApiClient mApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ProgressDialog progressDoalog;
    ListView list;
    List<Model> autoitems,shopsubitems;
    SubAdapter adapter;
    ShopAdapter shopadapter;
    public  String KEY;
    int FRAGMENT_ID;


    public BloodAdapter badapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sub, container, false);

        mApiClient = new ApiClient();
        cd = new ConnectionDetector(getActivity());
        areaid = getArguments().getInt("AREAID");
        autoitems = (List<Model>) getArguments().getSerializable("arraylist");

        FRAGMENT_ID = getArguments().getInt("FRAGMENT_ID");
        Log.d("FRAGMENT_ID", String.valueOf(FRAGMENT_ID));
        list = (ListView)root.findViewById(R.id.listauto);



      adapter=new SubAdapter(getActivity(), autoitems,FRAGMENT_ID);
        list.setAdapter(adapter);




        if (FRAGMENT_ID == 7)
        {

            shopadapter=new ShopAdapter(getActivity(), autoitems);
            list.setAdapter(shopadapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int selected_ID = Integer.parseInt(autoitems.get(position).getId());


                    getShopSubItems(selected_ID);

                    //Log.d("selected_id", String.valueOf(selected_ID));



                }
            });


        }

         else if (FRAGMENT_ID == 5)
        {
            shopadapter=new ShopAdapter(getActivity(), autoitems);
            list.setAdapter(shopadapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int selected_ID = Integer.parseInt(autoitems.get(position).getId());



                    getEducationSubitems(selected_ID);





                }
            });

        }

        else if (FRAGMENT_ID == 6)
        {
            shopadapter=new ShopAdapter(getActivity(), autoitems);
            list.setAdapter(shopadapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int selected_ID = Integer.parseInt(autoitems.get(position).getId());


                    getWorkContacts(selected_ID);

                    Log.d("selected_id", String.valueOf(selected_ID));




                }
            });


        }

        else if (FRAGMENT_ID == 11)
        {
            badapter=new BloodAdapter(getActivity(), autoitems);
            list.setAdapter(badapter);


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int SelectID = Integer.parseInt(autoitems.get(position).getId());


                            getBloodContacts(SelectID);

                }
            });


        }


        else
        {
            adapter=new SubAdapter(getActivity(), autoitems,FRAGMENT_ID);
            list.setAdapter(adapter);
        }









        return root;
    }

    private void getBloodContacts(int selectID) {

        Log.d("selected_id2", String.valueOf(selectID));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getBloodContacts(1,selectID);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank2:"+statusCode);

                        shopsubitems =response.body().getBloodcontact();
                        if (shopsubitems.size() != 0)
                        {
                            Fragment f = new ShopContactFragment();
                            Bundle b = new Bundle();
                            b.putSerializable("arraylist", (Serializable) shopsubitems);
                            f.setArguments(b);
                            b.putString("KEY","BLOOD");
                            Log.d("arraylistshopes", String.valueOf(shopsubitems.size()));
                            ((InnerMainActivity)getContext()).LoadFragments(f);

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
                    prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }
    }

    private void getWorkContacts(int selected_id) {

        Log.d("selected_id2", String.valueOf(selected_id));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getWorkersContacts(1,selected_id);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank2:"+statusCode);

                        shopsubitems =response.body().getWorkerContact();
                        if (shopsubitems.size() != 0)
                        {
                            CallTheFragment("WORKERS");
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
                    prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }
    }

    private void getEducationSubitems(int selected_id) {

        Log.d("selected_id2", String.valueOf(selected_id));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getEducationContacts(selected_id,1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

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
                    prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }

    private void getShopSubItems(int selected_id) {

        Log.d("selected_id2", String.valueOf(selected_id));
        cd = new ConnectionDetector(getActivity());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getShopSubCategory(selected_id);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank2:"+statusCode);

                        shopsubitems =response.body().getSubShoCategory();
                        if (shopsubitems.size() != 0)
                        {
                            CallTheFragment("SHOP");
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
                    prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getActivity(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }


    }




    public void CallTheFragment(String title) {


        Fragment f = new SubDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("arraylist", (Serializable) shopsubitems);
        f.setArguments(b);
        b.putString("KEY",title);
        Log.d("arraylistshopes", String.valueOf(shopsubitems.size()));
        ((InnerMainActivity)getContext()).LoadFragments(f);

    }


    public void progressShow(){

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Please wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

    }


    /**
     * Progress dialog Hide
     *
     * */
    public void prograssHide(){

        if(progressDoalog != null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }

    }





}
