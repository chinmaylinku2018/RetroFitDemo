package com.example.com.retrofitdemo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("readcontacts.php")
    Call<ArrayList<Contact>> getContacts();
}
