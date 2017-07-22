package com.vineesh.suthanbathery.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vineesh.suthanbathery.R;
import com.vineesh.suthanbathery.model.CircleTransform;
import com.vineesh.suthanbathery.model.Icons;
import com.vineesh.suthanbathery.model.SquareLayout;

import java.util.List;


/**
 * Created by vineesh on 30/06/2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Icons> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail,img_cover;

        RelativeLayout squarelayout;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            squarelayout = (RelativeLayout)view.findViewById(R.id.squarelayout);

           // img_cover =(ImageView) view.findViewById(R.id.img_cover_d);

        }
    }


    public AlbumsAdapter(Context mContext, List<Icons> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Icons album = albumList.get(position);
        holder.title.setText(album.getTitle());

        // loading album cover using Glide library
       // Glide.with(mContext).load(album.getCover_img()).into(holder.thumbnail);

        holder.thumbnail.setImageResource(album.getCover_img());
       // holder.img_cover.setImageResource(R.drawable.img);
        /*Glide.with(mContext).load(R.drawable.img)
                .bitmapTransform(new CircleTransform(mContext))
                .into(holder.img_cover);*/

     holder.thumbnail.setOnTouchListener(new View.OnTouchListener() {
         @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
         @Override
         public boolean onTouch(View v, MotionEvent event) {


             if (event.getAction() == MotionEvent.ACTION_DOWN )
             {
                 holder.squarelayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.viewBg));


                 return true;
             }
             else
             {
                 holder.squarelayout.setBackground(ContextCompat.getDrawable(mContext,R.drawable.gradient));
             }
             return false;
         }
     });



    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
   /* private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }*/

    /**
     * Click listener for popup menu items
     */
    /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}