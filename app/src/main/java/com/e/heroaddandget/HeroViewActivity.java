package com.e.heroaddandget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import HeroAPI.HeroAPI;
import URL.BaseUrl;
import adapter.HeroesAdapter;
import model.Heroesmodel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_view);

        recyclerView=findViewById(R.id.recyclerview);
//        getAllHeroes();

    }

//    private void getAllHeroes() {
//        HeroAPI heroAPI= BaseUrl.getInstance().create(HeroAPI.class);
//
//        Call<List<Heroesmodel>> listCall= heroAPI.getAllHeroes();
//
//        listCall.enqueue(new Callback<List<Heroesmodel>>() {
//            @Override
//            public void onResponse(Call<List<Heroesmodel>> call, Response<List<Heroesmodel>> response) {
//                if (!response.isSuccessful()){
//                    Toast.makeText(HeroViewActivity.this, "Code"+response.code() , Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Heroesmodel> heroesList=response.body();
//
//                HeroesAdapter contactsAdapter=new HeroesAdapter(heroesList,HeroViewActivity.this);
//                recyclerView.setAdapter(contactsAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(HeroViewActivity.this));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Heroesmodel>> call, Throwable t) {
//                Toast.makeText(HeroViewActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
