package com.vineesh.suthanbathery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vineesh on 04/07/2017.
 */

public class Model implements Serializable {

   /* @SerializedName("area")
    private List<Model> areaList;

    public List<Model> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Model> areaList) {
        this.areaList = areaList;
    }*/

    @SerializedName("auto")
    private List<Model> autolist;

    @SerializedName("bankContact")
    private List<Model> bankContact;


    @SerializedName("taxiContact")
    private List<Model> taxiContact;


    @SerializedName("news")
    private List<Model> news;


    @SerializedName("importantContact")
    private List<Model> emergency;

    @SerializedName("hospitalContact")
    private List<Model> hospital;

    @SerializedName("feedback")
    private List<Model> feedbacklists;

    @SerializedName("shopeCat")
    private  List<Model> shopeCatogory;

    @SerializedName("subShopeCat")
    private  List<Model> subShoCategory;
    @SerializedName("shop")
    private  List<Model> shop;

    @SerializedName("educationcat")
    private  List<Model> educationcat;

    @SerializedName("educationcontact")
    private  List<Model> educationcontact;

    @SerializedName("workCategory")
    private  List<Model> workCategory;

    @SerializedName("workerContact")
    private  List<Model> workerContact;

    @SerializedName("goodsContact")
    private  List<Model> goodsContact;

    @SerializedName("bloodType")
    private  List<Model> bloodType;

    @SerializedName("bloodcontact")
    private  List<Model> bloodcontact;



    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;


    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private String address;

    @SerializedName("model")
    private String model;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image_name;

    @SerializedName("message")
    private String message;

    @SerializedName("blood_type")
    private String blood_group;


    public Model(List<Model> autolist,
 List<Model> bankContact, List<Model> taxiContact,
 List<Model> news, List<Model> emergency, List<Model> hospital,
 List<Model> feedbacklists, List<Model> shopeCatogory,List<Model> subShoCategory,
 List<Model> educationcat,List<Model> workCategory,List<Model> goodsContact,List<Model> shop,
 List<Model> educationcontact,List<Model> workerContact,List<Model> bloodType,List<Model> bloodcontact,


 String id, String name, String phone, String address,
 String model, String description, String image_name,
 String message,String blood_group                                     ) {


        this.autolist = autolist;
        this.subShoCategory = subShoCategory;
        this.shop = shop;
        this.bankContact = bankContact;
        this.taxiContact = taxiContact;
        this.news = news;
        this.educationcontact = educationcontact;
        this.emergency = emergency;
        this.hospital = hospital;
        this.bloodType = bloodType;
        this.bloodcontact = bloodcontact;

        this.feedbacklists = feedbacklists;
        this.shopeCatogory = shopeCatogory;
        this.educationcat = educationcat;
        this.workCategory = workCategory;
        this.workerContact = workerContact;
        this.goodsContact = goodsContact;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.model = model;
        this.description = description;
        this.image_name = image_name;
        this.message = message;
        this.blood_group = blood_group;
    }

    public Model() {

    }

    public List<Model> getBloodcontact() {
        return bloodcontact;
    }

    public void setBloodcontact(List<Model> bloodcontact) {
        this.bloodcontact = bloodcontact;
    }

    public List<Model> getBloodType() {
        return bloodType;
    }

    public void setBloodType(List<Model> bloodType) {
        this.bloodType = bloodType;
    }

    public List<Model> getEducationcontact() {
        return educationcontact;
    }

    public List<Model> getWorkerContact() {
        return workerContact;
    }

    public void setWorkerContact(List<Model> workerContact) {
        this.workerContact = workerContact;
    }

    public void setEducationcontact(List<Model> educationcontact) {
        this.educationcontact = educationcontact;
    }

    public List<Model> getShop() {
        return shop;
    }

    public void setShop(List<Model> shop) {
        this.shop = shop;
    }

    public List<Model> getGoodsContact() {
        return goodsContact;
    }

    public void setGoodsContact(List<Model> goodsContact) {
        this.goodsContact = goodsContact;
    }

    public List<Model> getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(List<Model> workCategory) {
        this.workCategory = workCategory;
    }

    public List<Model> getEducationcat() {
        return educationcat;
    }

    public void setEducationcat(List<Model> educationcat) {
        this.educationcat = educationcat;
    }

    public List<Model> getSubShoCategory() {
        return subShoCategory;
    }

    public void setSubShoCategory(List<Model> subShoCategory) {
        this.subShoCategory = subShoCategory;
    }

    public List<Model> getAutolist() {
        return autolist;
    }

    public void setAutolist(List<Model> autolist) {
        this.autolist = autolist;
    }

    public List<Model> getBankContact() {
        return bankContact;
    }

    public void setBankContact(List<Model> bankContact) {
        this.bankContact = bankContact;
    }

    public List<Model> getTaxiContact() {
        return taxiContact;
    }

    public void setTaxiContact(List<Model> taxiContact) {
        this.taxiContact = taxiContact;
    }

    public List<Model> getNews() {
        return news;
    }

    public void setNews(List<Model> news) {
        this.news = news;
    }

    public List<Model> getEmergency() {
        return emergency;
    }

    public void setEmergency(List<Model> emergency) {
        this.emergency = emergency;
    }

    public List<Model> getHospital() {
        return hospital;
    }

    public void setHospital(List<Model> hospital) {
        this.hospital = hospital;
    }

    public List<Model> getFeedbacklists() {
        return feedbacklists;
    }

    public void setFeedbacklists(List<Model> feedbacklists) {
        this.feedbacklists = feedbacklists;
    }

    public List<Model> getShopeCatogory() {
        return shopeCatogory;
    }

    public void setShopeCatogory(List<Model> shopeCatogory) {
        this.shopeCatogory = shopeCatogory;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }


}
