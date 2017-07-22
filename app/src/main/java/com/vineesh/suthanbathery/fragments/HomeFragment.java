package com.vineesh.suthanbathery.fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Movie;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vineesh.suthanbathery.InnerMainActivity;
import com.vineesh.suthanbathery.MainActivity;
import com.vineesh.suthanbathery.R;

import com.vineesh.suthanbathery.RequestUsActivity;
import com.vineesh.suthanbathery.adapter.AlbumsAdapter;
import com.vineesh.suthanbathery.model.HidingScrollListener;
import com.vineesh.suthanbathery.model.Icons;


import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Icons> albumList;
    private StaggeredGridLayoutManager mLayoutManager;

    private boolean NAV_Status = false;

    //Animations
    Animation show_NAV;
    Animation hide_NAV;
    public HomeFragment() {
        // Required empty public constructor

    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(getActivity(), albumList);

       /*  mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);*/

        GridLayoutManager lLayout = new GridLayoutManager(getActivity(), 3);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setHasFixedSize(true);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);

        // recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));

       /* LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setAdapter(adapter);*/


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Log.d("pos","fgf");
                Intent i;
                    switch(position)
                    {
                        case 0 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Auto rickshaw");
                                startActivity(i);
                                break;

                        case 1 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Bank");
                                startActivity(i);
                                break;

                        case 2 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Train Time");
                                startActivity(i);
                                break;

                        case 3 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Taxi");
                                startActivity(i);
                                break;

                        case 4 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Education");
                                startActivity(i);
                                break;

                        case 5 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Workers");
                                startActivity(i);
                                break;

                        case 6 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Shops");
                                startActivity(i);
                                break;

                        case 7 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Hospitals");
                                startActivity(i);
                                break;

                        case 8 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Goods");
                                startActivity(i);
                                break;

                        case 9 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Emergency");
                                startActivity(i);
                                break;

                        case 10 :
                                i = new Intent(getActivity(),InnerMainActivity.class);
                                i.putExtra("title","Bloodbank");
                                startActivity(i);
                                break;

                        case 11 :
                                i = new Intent(getActivity(),RequestUsActivity.class);
                                startActivity(i);
                                break;
                    }

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));

        prepareAlbums();

       // prepareAlbumssecondRecyclerview();


        return root;
    }


    /*   private void prepareAlbumssecondRecyclerview() {

       }
   */
    public void prepareAlbums() {

        int[] covers = new int[]{
                R.drawable.svg_aouto,
                R.drawable.ic_bank,
                R.drawable.ic_train,
                R.drawable.ic_taxi,
                R.drawable.ic_education,
                R.drawable.ic_workers,
                R.drawable.ic_shope,
                R.drawable.ic_hospital,
                R.drawable.ic_goods,
                R.drawable.ic_ambulance,
                R.drawable.ic_blood,
                R.drawable.ic_others};

        Icons a = new Icons("Aoutorikshaw",  covers[0]);
        albumList.add(a);

        a = new Icons("Bank", covers[1]);
        albumList.add(a);

        a = new Icons("Train Time", covers[2]);
        albumList.add(a);


        a = new Icons("Taxi", covers[3]);
        albumList.add(a);


        a = new Icons("Education", covers[4]);
        albumList.add(a);

        a = new Icons("Workers", covers[5]);
        albumList.add(a);

        a = new Icons("Shops", covers[6]);
        albumList.add(a);


        a = new Icons("Hospitals", covers[7]);
        albumList.add(a);

        a = new Icons("Goods", covers[8]);
        albumList.add(a);

        a = new Icons("Ambulance", covers[9]);
        albumList.add(a);

        a = new Icons("Bloodbank", covers[10]);
        albumList.add(a);

        a = new Icons("Requestus", covers[11]);
        albumList.add(a);







    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }






}
