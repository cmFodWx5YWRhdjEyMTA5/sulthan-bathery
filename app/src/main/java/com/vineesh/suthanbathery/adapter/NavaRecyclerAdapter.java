package com.vineesh.suthanbathery.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.Icons;

import java.util.List;

/**
 * Created by vineesh on 13/07/2017.
 */

public class NavaRecyclerAdapter  extends RecyclerView.Adapter<NavaRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Icons> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail,img_cover;





        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);


            // img_cover =(ImageView) view.findViewById(R.id.img_cover_d);

        }
    }


    public NavaRecyclerAdapter(Context mContext, List<Icons> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public NavaRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row, parent, false);

        return new NavaRecyclerAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final NavaRecyclerAdapter.MyViewHolder holder, int position) {

        Icons album = albumList.get(position);
        holder.title.setText(album.getTitle());

        // loading album cover using Glide library
        // Glide.with(mContext).load(album.getCover_img()).into(holder.thumbnail);

        holder.thumbnail.setImageResource(album.getCover_img());
        // holder.img_cover.setImageResource(R.drawable.img);
        /*Glide.with(mContext).load(R.drawable.img)
                .bitmapTransform(new CircleTransform(mContext))
                .into(holder.img_cover);*/




    }



    @Override
    public int getItemCount() {
        return albumList.size();
    }
}