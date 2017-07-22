package com.vineesh.suthanbathery.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vineesh on 04/07/2017.
 */

public class ConnectionDetector {

    private Context mContext;


    public ConnectionDetector(Context context) {
        this.mContext = context;
    }



    /*
    *
    *
    *  method isConnectingToInternet
    *  checking internet connection
    *  return true if connection is present
    *  return false if connection is absent
    *
    *
    *
    */

    public boolean isConnectingToInternet() {

        ConnectivityManager cn = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo nf = cn.getActiveNetworkInfo();
        if (nf != null && nf.isConnected() == true) {
            return true;
        } else {
            return false;
        }

    }
}
