package com.example.myrecipeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeVH>{

    List<String> items;

    @NonNull
    @Override
    public RecipeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new RecipeVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeVH holder, int position) {
        holder.textView_calories.setText(items.get(position));
        holder.textView_title.setText(items.get(position));
        holder.textView_title.setSelected(true);
        holder.textView_protein.setText(items.get(position));
        holder.textView_carbs.setText(items.get(position));
        holder.textView_fat.setText(items.get(position));
        holder.textView_comment.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class  RecipeVH extends RecyclerView.ViewHolder{
    CardView list_container;
    TextView textView_title, textView_calories, textView_protein, textView_carbs, textView_fat, textView_comment;
    ImageView imageView_food;

    private RecipeAdapter adapter;

    public RecipeVH(@NonNull View itemView) {
        super(itemView);
        list_container = itemView.findViewById(R.id.list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_calories = itemView.findViewById(R.id.textView_calories);
        textView_protein = itemView.findViewById(R.id.textView_protein);
        textView_carbs = itemView.findViewById(R.id.textView_carbs);
        textView_fat = itemView.findViewById(R.id.textView_fat);
        textView_comment = itemView.findViewById(R.id.textView_comment);
        imageView_food = itemView.findViewById(R.id.imageView_food);
    }

    public RecipeVH linkAdapter(RecipeAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}