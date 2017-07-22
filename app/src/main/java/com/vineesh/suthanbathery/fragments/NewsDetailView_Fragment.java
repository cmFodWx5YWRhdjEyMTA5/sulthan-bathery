package com.vineesh.suthanbathery.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.utils.Constant;

import org.w3c.dom.Text;


public class NewsDetailView_Fragment extends Fragment {

        Model m;
    TextView detailnews;
    ImageView detailnews_img;
    public NewsDetailView_Fragment() {
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
        View root = inflater.inflate(R.layout.fragment_news_detail_view_, container, false);
        m = new Model();
        m= (Model) getArguments().getSerializable("OBJECT");

        detailnews = (TextView)root.findViewById(R.id.detailnews);
        detailnews_img = (ImageView) root.findViewById(R.id.detailnews_img);

        detailnews.setText(m.getDescription());
        Glide.with(getActivity()).load(Constant.IMGE_URL+m.getImage_name()).into(detailnews_img);

        return root;
    }


}
