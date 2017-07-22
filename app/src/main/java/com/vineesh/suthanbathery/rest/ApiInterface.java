package com.vineesh.suthanbathery.rest;

import com.vineesh.suthanbathery.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vineesh on 04/07/2017.
 */

public interface ApiInterface {

    /**
     * GETTING THE AREA DETAILS
     * *
     * */

    @GET("area")
    Call<Model> getAreaAPI();

    @GET("auto-contacts")
    Call<Model> getAuotoDetails(@Query("areaid") int areaid);

    @GET("bank-contacts")
    Call<Model> getBankContacts(@Query("areaid") int areaid);

    @GET("taxi-contacts")
    Call<Model> getTaxiContacts(@Query("areaid") int areaid);


    @GET("news-reports")
    Call<Model> getNewsReports();

    @GET("important-contacts")
    Call<Model> getEmergencyContacts(@Query("areaid") int areaid);


    @GET("hospital-contacts")
    Call<Model> getHospitalContacts(@Query("areaid") int areaid);


    @GET("feedback-list")
    Call<Model> getFeedbackLists();

    @GET("shope-category")
    Call<Model> getShopCategory();


    @GET("shope-Sub-category")
    Call<Model> getShopSubCategory(@Query("catid") int catid);

    @GET("shope")
    Call<Model> getShopContacts(@Query("subcatid") int subcatid,@Query("areaid") int areaid);



    @GET("education-category")
    Call<Model> getEducationList();

    @GET("education-contact")
    Call<Model> getEducationContacts(@Query("catid") int catid,@Query("areaid") int areaid);


    @GET("work-categories")
    Call<Model> getWorkList();

    @GET("worker-contacts")
    Call<Model> getWorkersContacts(@Query("areaid") int areaid,@Query("catid") int catid);


    @GET("goods-contacts")
    Call<Model> getGoods(@Query("areaid") int areaid);



    @GET("blood-type")
    Call<Model> getBloodList();


    @GET("blood-contacts")
    Call<Model> getBloodContacts(@Query("areaid") int areaid,@Query("typeid") int typeid);

}
