package com.vineesh.suthanbathery.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.adapter.Feedback_adapter;
import com.vineesh.suthanbathery.adapter.NewsAdapter;
import com.vineesh.suthanbathery.model.Model;

import java.util.List;


public class FeedbackFragment extends Fragment {

    ListView list;
    List<Model> autoitems;
    Feedback_adapter adapter;

    public FeedbackFragment() {
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
        View root = inflater.inflate(R.layout.fragment_emergency, container, false);


        list = (ListView)root.findViewById(R.id.listnews);
        autoitems = (List<Model>) getArguments().getSerializable("arraylist");

        adapter=new Feedback_adapter(getActivity(),autoitems);
        list.setAdapter(adapter);


        return root;
    }

}
