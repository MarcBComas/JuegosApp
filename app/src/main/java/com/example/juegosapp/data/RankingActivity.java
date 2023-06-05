package com.example.juegosapp.data;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegosapp.R;

public class RankingActivity extends AppCompatActivity {
    private GameDataHelper mDB;
    private RecyclerView mRecyclerView;
    private RankingAdapter mAdapter;
    private String game = "";
    private String order = "Tiempo";
    private TextView gameName;
    private Button loBttn;
    private Button dmcoBttn;
    private Spinner spn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_layout);
        mDB = new GameDataHelper(this);
        loBttn = (Button) findViewById(R.id.loBttn);
        dmcoBttn = (Button) findViewById(R.id.dmcoBttn);
        spn = (Spinner) findViewById(R.id.ordenspinner);
        gameName = (TextView) findViewById(R.id.game);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        startRanking();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dmcoBttn:
                this.game = "DMCO";
                startRanking();
                break;
            case R.id.loBttn:
                this.game = "LO";
                startRanking();
                break;
            case R.id.reorder:
                order = spn.getSelectedItem().toString();
                orderRanking();
                break;
        }
    }

    public void orderRanking() {
        mAdapter = new RankingAdapter(this, mDB, game, order);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void startRanking() {
        if (this.getIntent().getStringExtra("GAME") != null) {
            this.game = this.getIntent().getStringExtra("GAME");
        }
        if (!game.equals("")) {
            spn.setVisibility(View.VISIBLE);
            gameName.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
            ArrayAdapter<CharSequence> adapter;
            switch (game) {
                case "LO":
                    loBttn.setVisibility(View.INVISIBLE);
                    dmcoBttn.setVisibility(View.VISIBLE);
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.lo_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn.setAdapter(adapter);
                    gameName.setText("Lights Out");
                    break;
                case "DMCO":
                    dmcoBttn.setVisibility(View.INVISIBLE);
                    loBttn.setVisibility(View.VISIBLE);
                    adapter = ArrayAdapter.createFromResource(this,
                            R.array.dmco_array, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spn.setAdapter(adapter);
                    gameName.setText("2048");
                    break;
            }
            mAdapter = new RankingAdapter(this, mDB, game, order);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
