package com.example.javalearnprogram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javalearnprogram.R;

import java.util.ArrayList;

public class ExesizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<String> Titles;
    private TextView textTitle; // Текст на карте, ИМЕННО ЭТО ID
    private CardView cardView; // Сама карточка, ИМЕННО ЭТО ID

    public ExesizeAdapter(Context context, ArrayList<String> titles) {
        this.layoutInflater = LayoutInflater.from(context);
        Titles = titles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = layoutInflater.inflate(R.layout._exesize_card_, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String title = Titles.get(position);
        textTitle = holder.itemView.findViewById(R.id.newsText);
        textTitle.setText(title);
        cardView.setId(position);
    }

    @Override
    public int getItemCount() {
        return Titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.newsText);
            cardView = itemView.findViewById(R.id.set);
        }
    }

}
