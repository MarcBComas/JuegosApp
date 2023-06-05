package com.example.juegosapp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.juegosapp.R;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ScoreViewHolder>{
    class ScoreViewHolder extends RecyclerView.ViewHolder {
        public final TextView name;
        public final TextView score;
        public final TextView time;
        public ScoreViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            score = (TextView) itemView.findViewById(R.id.score);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    private final LayoutInflater mInflater;
    GameDataHelper db;
    Context mContext;
    String game;
    String order;

    public RankingAdapter(Context context, GameDataHelper db, String game, String order) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.game = game;
        this.order = order;
        this.db = db;
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.playerscore, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        final ScoreViewHolder h = holder;
        Scores current = db.gameQuery(position, game, order);
        holder.name.setText(current.getName());
        holder.time.setText("Tiempo: "+current.getTime());
        if (game.equals("DMCO")) {
            holder.score.setText("Puntuacion: "+current.getOther());
        } else if (game.equals("LO")) {
            holder.score.setText("Pulsaciones: "+current.getOther());
        }

    }

    @Override
    public int getItemCount() {
        return (int) db.count(game);
    }
}
