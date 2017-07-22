package com.vineesh.suthanbathery.fragments;

import android.app.ProgressDialog;
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
import com.vineesh.suthanbathery.MainActivity;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.adapter.NewsAdapter;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {



    ListView list;
    List<Model> autoitems;
    NewsAdapter adapter;

    public NewsFragment() {
        // Required empty public constructor
    }


    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_news, container, false);



        list = (ListView)root.findViewById(R.id.listnews);
        autoitems = (List<Model>) getArguments().getSerializable("arraylist");

        adapter=new NewsAdapter(getActivity(),autoitems);
        list.setAdapter(adapter);
        Log.w("adapterbegin","adapterbegin");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Model m =new Model();
                m.setDescription(autoitems.get(position).getDescription());
                m.setImage_name(autoitems.get(position).getImage_name());

                NewsDetailView_Fragment f = new NewsDetailView_Fragment();
                Bundle b=new Bundle();
                b.putSerializable("OBJECT",m);
                f.setArguments(b);

                ((MainActivity)getActivity()).loadHomeFragment(f);

            }
        });

        return root;
    }






}
