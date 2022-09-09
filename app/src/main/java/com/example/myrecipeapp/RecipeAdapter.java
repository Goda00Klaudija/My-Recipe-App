package com.example.myrecipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    //RecipeAdapter Class
    //New attempt
    Context context;
    List<RecipesCards> recipeList;
    RecyclerView recycler_list;

    final View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView theName, theCalories, theProtein, theCarbs, theFat, theRec_Id;
        //theComment;
//        ImageView theMeal_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theRec_Id = itemView.findViewById(R.id.rec_id);
            theName = itemView.findViewById(R.id.textView_name);
            theCalories = itemView.findViewById(R.id.textView_calories);
            theProtein = itemView.findViewById(R.id.textView_protein);
            theCarbs = itemView.findViewById(R.id.textView_carbs);
            theFat = itemView.findViewById(R.id.textView_fat);
//            theComment = itemView.findViewById(R.id.textView_comment);
//            theMeal_image = itemView.findViewById(R.id.imageView_meal_image);
        }
    }

    public RecipeAdapter(Context context, List<RecipesCards> recipeList, RecyclerView recycler_list) {
        this.context = context;
        this.recipeList = recipeList;
        this.recycler_list = recycler_list;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        RecipesCards recipe = recipeList.get(i);
        viewHolder.theRec_Id.setText(""+recipe.getRec_Id());
        viewHolder.theName.setText(recipe.getName());
        viewHolder.theCalories.setText(""+recipe.getCalories());
        viewHolder.theProtein.setText(""+recipe.getProteins());
        viewHolder.theCarbs.setText(""+recipe.getCarbs());
        viewHolder.theFat.setText(""+recipe.getFat());
//        viewHolder.theMeal_image.setImageBitmap(recipe.getPhoto());
//        viewHolder.theComment.setText(recipe.getComment());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            int itemPosition = recycler_list.getChildLayoutPosition(v);
            String item = recipeList.get(itemPosition).getName();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }
}
