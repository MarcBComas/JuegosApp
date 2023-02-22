package com.example.juegosapp.highscores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegosapp.R;

public class RankingActivity extends AppCompatActivity {
    private GameDataHelper mDB;
    private RecyclerView mRecyclerView;
    private RankingAdapter mAdapter;
    private String game;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_layout);

        mDB = new GameDataHelper(this);

        this.game = this.getIntent().getStringExtra("GAME");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new RankingAdapter(this, mDB, game);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
