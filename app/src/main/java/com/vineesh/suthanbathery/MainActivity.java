package com.vineesh.suthanbathery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vineesh.suthanbathery.adapter.AlbumsAdapter;
import com.vineesh.suthanbathery.adapter.NavaRecyclerAdapter;
import com.vineesh.suthanbathery.adapter.NewsAdapter;
import com.vineesh.suthanbathery.fragments.FeedbackFragment;
import com.vineesh.suthanbathery.fragments.HomeFragment;
import com.vineesh.suthanbathery.fragments.MailusFragment;
import com.vineesh.suthanbathery.fragments.NewsFragment;
import com.vineesh.suthanbathery.model.Icons;
import com.vineesh.suthanbathery.model.Model;
import com.vineesh.suthanbathery.rest.ApiClient;
import com.vineesh.suthanbathery.utils.ConnectionDetector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private NavaRecyclerAdapter Nav_adapter;
    private List<Icons> NAVLIST;


    private TextView mTextMessage;
    NavigationView navigationView;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Handler mHandler;
    BottomNavigationView navigation;

    Fragment f;
    Animation hide_fab_1;


    ApiClient mApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ProgressDialog progressDoalog;
    ListView list;
    List<Model> autoitems;
    List<Model> feedlist;
    NewsAdapter adapter;
    boolean progresscount =  false;
CoordinatorLayout cordinator;

    int internet;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    f = new HomeFragment();
                    loadHomeFragmentHome(f);

                    return true;
                case R.id.navigation_news:

                    if (checkConnectivity() == 1) {
                        //do something

                        f = new NewsFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("arraylist", (Serializable) autoitems);
                        f.setArguments(b);
                        loadHomeFragment(f);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                    }



                    return true;

                case R.id.navigation_feedback:



                    if (checkConnectivity() == 1) {
                        //do something

                        f = new FeedbackFragment();
                        Bundle b = new Bundle();
                        b.putSerializable("arraylist", (Serializable) feedlist);
                        f.setArguments(b);
                        loadHomeFragment(f);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                    }


                    return true;

                case R.id.navigation_mailus:

                    f = new MailusFragment();

                    loadHomeFragment(f);

                    return true;

            }


            return true;

        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cordinator = (CoordinatorLayout)findViewById(R.id.cordinator);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mHandler =new Handler();


        /**
         * Making Navigation drawaer using Recycler View**/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_navigation);

        NAVLIST = new ArrayList<>();
        Nav_adapter = new NavaRecyclerAdapter(getApplicationContext(), NAVLIST);

       /*  mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);*/

        GridLayoutManager lLayout = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Nav_adapter);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setHasFixedSize(true);

        prepareAlbums();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new HomeFragment.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Log.d("pos","fgf");
                Intent i;
                switch(position)
                {
                    case 0 :
                        i = new Intent(getApplicationContext(),InnerMainActivity.class);
                        i.putExtra("title","Aboutus");
                        startActivity(i);
                        break;

                    case 1 :
                        i = new Intent(getApplicationContext(),InnerMainActivity.class);
                        i.putExtra("title","Contactus");
                        startActivity(i);
                        break;

                    case 2 :
                        i = new Intent(getApplicationContext(),InnerMainActivity.class);
                        i.putExtra("title","share");
                        startActivity(i);
                        break;

                    case 3 :
                        i = new Intent(getApplicationContext(),InnerMainActivity.class);
                        i.putExtra("title","rate app");
                        startActivity(i);
                        break;

                }

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));

        /**
         * GETTING DETAILS FROM REST API
         * 1@ NEWS DETAILS
         * 2@ FEEDBACK DETAILS**/




        getFeedBackList();


        getNewsDetails();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


      /*  toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                cordinator.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.requestLayout();
            }
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        if (savedInstanceState == null) {
            HomeFragment f = new HomeFragment();
            loadHomeFragmentHome(f);
        }

    }

    private void prepareAlbums() {

        int[] covers = new int[]{
                R.drawable.ic_about_us_svg,
                R.drawable.ic_contact_svg,
                R.drawable.ic_rating,
                R.drawable.ic_share_svg};

        Icons a = new Icons("About",  covers[0]);
        NAVLIST.add(a);

        a = new Icons("ContactUs", covers[1]);
        NAVLIST.add(a);

        a = new Icons("Share", covers[2]);
        NAVLIST.add(a);

        a = new Icons("Rate App", covers[3]);
        NAVLIST.add(a);


    }


    public void loadHomeFragment(final Fragment fragment) {

        final String backStateName = fragment.getClass().getName();


        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack("none");
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.commitAllowingStateLoss();

           }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

    }


    @Override
    public void onBackPressed() {


        navigation.setSelectedItemId(R.id.navigation_home);

        Fragment fr = getSupportFragmentManager()
                .findFragmentById(R.id.content);


        if (fr instanceof HomeFragment) {


            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count; ++i) {
                fm.popBackStackImmediate();

            }

        }

        //super.onBackPressed();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }


    }


    /*@Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

    if (count == 1)
    {
        if (getSupportFragmentManager().findFragmentById(R.id.content).equals(HomeFragment.class))
        {
            super.onBackPressed();
        }
    loadHomeFragment(new HomeFragment());

    }

    if (count >1)
    {
        getSupportFragmentManager().popBackStackImmediate();
    }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;
        if (id == R.id.nav_about) {
             i = new Intent(this,InnerMainActivity.class);
            i.putExtra("title","Aboutus");
            navigationView.setCheckedItem(R.id.nav_about);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_contactdeveloper) {
            i = new Intent(this,InnerMainActivity.class);
            i.putExtra("title","Contact Developer");
            navigationView.setCheckedItem(R.id.nav_contactdeveloper);
            startActivity(i);


        } else if (id == R.id.nav_call) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_raeteus) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private int checkConnectivity() {
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


    private void getNewsDetails() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {



            Call<Model> listCall = mApiClient.getApiInterface()
                    .getNewsReports();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {

                        prograssHide();

                        int statusCode = response.code();

                        Log.w("hai","codenews:"+statusCode);

                        autoitems =response.body().getNews();

                        Log.w("neslist", String.valueOf(autoitems.size()));

                        Toast.makeText(getApplicationContext(),"doneNews",Toast.LENGTH_LONG).show();





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



    private void getFeedBackList() {

        cd = new ConnectionDetector(getApplicationContext());
        mApiClient = new ApiClient();
        isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent) {

            progressShow();

            Call<Model> listCall = mApiClient.getApiInterface()
                    .getFeedbackLists();
            listCall.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {

                    if (response.isSuccessful()) {


                        prograssHide();
                        progresscount=true;

                        int statusCode = response.code();

                        Log.w("hai","codenews:"+statusCode);

                        feedlist =response.body().getFeedbacklists();

                        Log.w("neslist", String.valueOf(feedlist.size()));

                        Toast.makeText(getApplicationContext(),"doFEED",Toast.LENGTH_LONG).show();


                        prograssHide();


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



    public void prograssHide(){

        if(progressDoalog != null && progressDoalog.isShowing()){
            progressDoalog.dismiss();
        }

    }

    public void progressShow(){

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("Please wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(true);
        progressDoalog.show();

    }


    private void loadHomeFragmentHome(final Fragment f1) {

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack("none");
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content, f1);
                fragmentTransaction.commitAllowingStateLoss();

            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private HomeFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final HomeFragment.ClickListener clicklistener){

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
