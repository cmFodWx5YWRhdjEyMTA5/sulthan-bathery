package com.vineesh.suthanbathery;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vineesh.suthanbathery.adapter.AreaAdapter;
import com.vineesh.suthanbathery.fragments.SubFragment;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InnerMainActivity extends AppCompatActivity {
    String title;
    // flag for Internet connection status
    Boolean isInternetPresent = false;
    private ApiClient mApiClient;
    // Connection detector class
    ConnectionDetector cd;
    ProgressDialog progressDoalog;
    List<Model> area;

    public static  List<Model> autoitems ;

    ListView list;
    AreaAdapter adapter;
    public  int internet;

    LinearLayout batheryText;
    Handler mHandler;
    int areaId;
    int FRAGMENT_ID;
    TextView nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        title = getIntent().getExtras().getString("title");
        Log.d("title",title);
        getSupportActionBar().setTitle(title);
        batheryText = (LinearLayout)findViewById(R.id.listitem);






        if (title.equals("Auto rickshaw"))
        {
            getAutoDetails();
            FRAGMENT_ID=1;

        }
        else if(title.equals("Bank")){
            getAllBankDetails();
            FRAGMENT_ID=2;

        }

        else if(title.equals("Taxi")){
            getAllTaxiDEtails();
            FRAGMENT_ID=4;

        }
        else if(title.equals("Education")){

            getEducations();

            FRAGMENT_ID=5;

        }

        else if(title.equals("Workers")){

            getWorkersList();

            FRAGMENT_ID=6;

        }
        else if(title.equals("Shops")){

            getShopeCategoryDEtails();

            FRAGMENT_ID=7;

        }
        else if(title.equals("Hospitals")){

            getHospitalDEtails();
            FRAGMENT_ID=8;

        }


        else if(title.equals("Goods")){

            getGoods();
            FRAGMENT_ID=9;

        }

        else if(title.equals("Emergency")){
            getEmergencyContacts();
            FRAGMENT_ID=10;

        }

        else if(title.equals("Bloodbank")){

            getBloodTypes();
            FRAGMENT_ID=11;

        }




        if (FRAGMENT_ID != 12)
        {
            batheryText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    areaId = 1;

                    if (checkConnectivity() == 1) {


                        Fragment f = new SubFragment();
                        Bundle b = new Bundle();
                        b.putInt("AREAID", 1);
                        b.putInt("FRAGMENT_ID",FRAGMENT_ID);
                        Log.d("FRAGMENT_IDfgf", String.valueOf(FRAGMENT_ID));
                        b.putSerializable("arraylist", (Serializable) autoitems);
                        f.setArguments(b);


                        LoadFragments(f);

                    }


                }
            });
        }






    }

    private void getBloodTypes() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getBloodList();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrestedu:"+statusCode);

                        autoitems =response.body().getBloodType();

                        Log.w("autoiteblood", String.valueOf(autoitems.size()));



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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }



    private void getGoods() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getGoods(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrestedu:"+statusCode);

                        autoitems =response.body().getGoodsContact();

                        Log.w("autoitemsedu", String.valueOf(autoitems.size()));







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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }
    }



    public void getWorkersList() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getWorkList();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrestedu:"+statusCode);

                        autoitems =response.body().getWorkCategory();

                        Log.w("autoitemsedu", String.valueOf(autoitems.size()));







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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }


    private void getEducations() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getEducationList();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrestedu:"+statusCode);

                        autoitems =response.body().getEducationcat();

                        Log.w("autoitemsedu", String.valueOf(autoitems.size()));







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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }


    private void getShopeCategoryDEtails() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getShopCategory();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        autoitems =response.body().getShopeCatogory();

                        Log.w("autoitems2", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerestedbank",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }



    private void getHospitalDEtails() {
        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getHospitalContacts(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        autoitems =response.body().getHospital();

                        Log.w("autoitems2", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerestedbank",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }



    private void getEmergencyContacts() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getEmergencyContacts(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        autoitems =response.body().getEmergency();

                        Log.w("autoitems2", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerestedbank",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }



    private void getAllTaxiDEtails() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getTaxiContacts(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        autoitems =response.body().getTaxiContact();

                        Log.w("autoitems2", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerestedbank",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }
    }





    private void getAllBankDetails() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getBankContacts(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrest codebank:"+statusCode);

                        autoitems =response.body().getBankContact();

                        Log.w("autoitems2", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerestedbank",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }




    private void getAutoDetails() {
        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getAuotoDetails(1);
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();
                        Log.w("hai","getSendIntrest code:"+statusCode);
                        autoitems =response.body().getAutolist();
                        Log.w("autoitems1", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"Inerested",Toast.LENGTH_LONG).show();





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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }
    }




    public void LoadFragments(final Fragment f) {


        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("none");
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.innerContainer, f);
            fragmentTransaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


    }



    public int checkConnectivity() {
        boolean enabled = true;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            internet = 0;//sin conexion
            Toast.makeText(getApplicationContext(), "Sin conexión a Internet...", Toast.LENGTH_SHORT).show();
            enabled = false;
        } else {
            internet = 1;//conexión
        }

        return internet;
    }




/*
    private void getAreaDetails() {
        Log.w("hai", "Work: ");
        //check internet connection
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getAreaAPI();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {
                   // Log.w("haistatus","statusCode:"+response.body());
                    if (response.isSuccessful()) {

                        Log.w("hai", "statusCode: "+response.code());

                        prograssHide();
                        int statusCode = response.code();

                        Log.w("hai","statusCode:"+statusCode);
                        area = response.body().getAreaList();
                        setAdapter();

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
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }
*/

  /*  private void setAdapter() {

        adapter=new AreaAdapter(this, area);
        list.setAdapter(adapter);
    }*/

    public void prograssHide(){

        if(progressDoalog != null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }

    }

    public void progressShow(){

        progressDoalog = new ProgressDialog(InnerMainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Please wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.stay, R.anim.slide_down);
        return true;
    }
}
