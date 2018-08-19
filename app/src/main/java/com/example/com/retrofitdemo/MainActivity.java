package com.example.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<Contact> contacts;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        contacts = new ArrayList<Contact>();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<Contact>> call = apiInterface.getContacts();

        call.enqueue(new Callback<ArrayList<Contact>>() {
            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                contacts = response.body();
                 recyclerAdapter = new RecyclerAdapter(contacts);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {

            }
        });

    }
}
