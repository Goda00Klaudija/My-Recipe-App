package com.example.myrecipeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    //RecipeAdapter Class
    //New attempt
    Context context;
    List<IngredientCards> ingredientList;
    RecyclerView recycler_ingredientList;


    //SearchView
    List<IngredientCards> ingredientByName;

    final View.OnClickListener onClickListener = new MyOnClickListener();


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView theName, theIng_Id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theIng_Id = itemView.findViewById(R.id.ing_id);
            theName = itemView.findViewById(R.id.ingredient);
        }
    }

    public IngredientAdapter(Context context, List<IngredientCards> ingredientList, RecyclerView recycler_ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.recycler_ingredientList = recycler_ingredientList;
    }

    //SearchView
    public IngredientAdapter(IngredientList context, List<IngredientCards> ingredientByName) {
        this.context = context;
        this.ingredientByName = ingredientByName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ingredient, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        IngredientCards recipe = ingredientList.get(i);
        viewHolder.theIng_Id.setText(""+recipe.getIng_Id());
        viewHolder.theName.setText(recipe.getName());
//
//        Database db = new Database(context);
//        db.open();
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            int itemPosition = recycler_ingredientList.getChildLayoutPosition(v);
            String item = ingredientList.get(itemPosition).getName();
            Intent i = new Intent(context, RecipeDetailsActivity.class);
            i.putExtra("key",item);
            context.startActivity(i);
        }
    }
}
