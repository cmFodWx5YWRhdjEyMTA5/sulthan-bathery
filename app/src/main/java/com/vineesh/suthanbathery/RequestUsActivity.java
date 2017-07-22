package com.vineesh.suthanbathery;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.vineesh.suthanbathery.adapter.AlbumsAdapter;
import com.vineesh.suthanbathery.adapter.SpinnerAdapter;
import com.vineesh.suthanbathery.fragments.MailusFragment;
import com.vineesh.suthanbathery.model.Icons;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestUsActivity extends AppCompatActivity {
    TextInputLayout sTxInputname,sTxInputMob;
    EditText sEtxtName,sEtxtNum;
    Spinner sp_cat,sp_subcat;
    InnerMainActivity innermainactivity;


    public List<Icons> albumList;
    public List<String> SUB_LIST;
    String CATOGORY_NAME,SUB_CATOGORY_NAME;
   public   int  Visible_count = 0;
    List<Model> ModelList;
    int ADAPTER_POSITION;

    Boolean isInternetPresent = false;
    private ApiClient mApiClient;
    // Connection detector class
    ConnectionDetector cd;
    ProgressDialog progressDoalog;


    List<String> SUB_CATOGORY;
    LinearLayout btn_send,selectarea,selectcatogory,selectsubcatogory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        sEtxtName = (EditText)findViewById(R.id.etxt_name);
        sEtxtNum = (EditText) findViewById(R.id.etxt_mob);

        btn_send = (LinearLayout)findViewById(R.id.submit_request);

        selectarea = (LinearLayout)findViewById(R.id.selectarea);
        selectcatogory = (LinearLayout)findViewById(R.id.selectcatogory);
        selectsubcatogory = (LinearLayout)findViewById(R.id.selectsubcatogory);

        selectarea.setVisibility(View.INVISIBLE);
        selectsubcatogory.setVisibility(View.INVISIBLE);

        sTxInputname = (TextInputLayout) findViewById(R.id.text_input_name);
        sTxInputMob = (TextInputLayout) findViewById(R.id.text_input_mob);
        sp_cat = (Spinner)findViewById(R.id.spinner_catogory);
        sp_subcat = (Spinner)findViewById(R.id.spinner_sub_catogory);
        albumList = new ArrayList<>();
        SUB_LIST = new ArrayList<>();
        SUB_CATOGORY = new ArrayList<>();
        // Spinner Drop down elements
        prepareAlbums();

        // Creating adapter for spinner
        SpinnerAdapter customAdapter=new SpinnerAdapter(getApplicationContext(),albumList);
        sp_cat.setAdapter(customAdapter);

        sp_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CATOGORY_NAME = albumList.get(position).getTitle();

                ADAPTER_POSITION = position;
                Visible_count = 1;
                SUB_CATOGORY = prepreSubCatogory(ADAPTER_POSITION);

                    callTheSecondSpinner(Visible_count);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /*sEtxtName.addTextChangedListener(new MailusFragment.MyTextWatcher(sEtxtName));
        sEtxtNum.addTextChangedListener(new MailusFragment.MyTextWatcher(sEtxtNum));*/


    }

    private void callTheSecondSpinner(int visible_count) {


        if (visible_count == 1)
        {

            selectsubcatogory.setVisibility(View.VISIBLE);


            ArrayAdapter<String> dataAdap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SUB_CATOGORY);

            // Drop down layout style - list view with radio button
            dataAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            sp_subcat.setAdapter(dataAdap);




            sp_subcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    SUB_CATOGORY_NAME = parent.getItemAtPosition(position).toString();
                    Visible_count = 1;
                    selectarea.setVisibility(View.VISIBLE);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        else
        {
            selectsubcatogory.setVisibility(View.INVISIBLE);
        }
    }


    private List<String> prepreSubCatogory(int pos) {

        if (pos == 0)
        {
            SUB_LIST.add("NO SUBCATOGORY");
        }

        else if (pos == 1)
        {
            SUB_LIST.add("NO SUBCATOGORY");
        }

        else if (pos == 2)
        {
            SUB_LIST.add("NO SUBCATOGORY");
        }

        else if (pos == 3)
        {
            innermainactivity.getWorkersList();
            ModelList = InnerMainActivity.autoitems;
            for (int i = 0;i<ModelList.size();i++)
            {
                SUB_LIST.add(ModelList.get(i).getName());
            }
        }


       /* switch(catogory_name)
        {
            case "Auto" : SUB_LIST.add("NO SUBCATOGORY");

                          break;

            case "Taxi" : SUB_LIST.add("NO SUBCATOGORY");
                         break;

            case "Goods" : SUB_LIST.add("NO SUBCATOGORY");
                break;

            case "Worker" :
                            ((InnerMainActivity)getApplicationContext()).getWorkersList();
                            ModelList = InnerMainActivity.autoitems;
                            for (int i = 0;i<ModelList.size();i++)
                            {
                            SUB_LIST.add(ModelList.get(i).getName());
                            }
                            break;
        }
*/
        return SUB_LIST;

    }


    public void getWorkersList() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            innermainactivity.progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getWorkList();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        innermainactivity.prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","getSendIntrestedu:"+statusCode);
                        List<Model> m =new ArrayList<Model>();

                        m =response.body().getWorkCategory();
                        for (int i = 0;i<m.size();i++)
                        {
                            SUB_LIST.add(m.get(i).getName());
                        }

                        Log.w("autoitemsedu", String.valueOf(SUB_LIST.size()));







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
                    innermainactivity.prograssHide();

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.w("hai", " Error: " + t.getMessage());
                    innermainactivity.prograssHide();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(),"Please check your internet connection",Toast.LENGTH_LONG).show();

        }

    }


    public void prepareAlbums() {

        int[] covers = new int[]{
                R.drawable.svg_aouto,
                R.drawable.ic_taxi,
                R.drawable.ic_goods,
                R.drawable.ic_workers,
                R.drawable.ic_shope,
                R.drawable.ic_blood,
                R.drawable.ic_education,
                R.drawable.ic_hospital,
                R.drawable.ic_bank,
                R.drawable.ic_others};


        Icons a = new Icons("Auto",  covers[0]);
        albumList.add(a);

        a = new Icons("Taxi", covers[1]);
        albumList.add(a);

        a = new Icons("Goods", covers[2]);
        albumList.add(a);


        a = new Icons("Worker", covers[3]);
        albumList.add(a);


        a = new Icons("Shop", covers[4]);
        albumList.add(a);

        a = new Icons("Blood", covers[5]);
        albumList.add(a);

        a = new Icons("Education", covers[6]);
        albumList.add(a);


        a = new Icons("Hospitals", covers[7]);
        albumList.add(a);

        a = new Icons("Bank", covers[8]);
        albumList.add(a);

        a = new Icons("Others", covers[9]);
        albumList.add(a);









    }






}
