package com.example.myrecipeapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;

    public TextView textView_meal_name, textView_meal_method;
    public ImageView imageView_meal_image;
    public RecyclerView recycler_meal_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        findViews();
    }

    private void findViews() {
        this.textView_meal_name = (TextView)this.findViewById(R.id.textView_meal_name);
        this.textView_meal_method = (TextView)this.findViewById(R.id.textView_meal_method);
        this.imageView_meal_image = (ImageView)this.findViewById(R.id.imageView_meal_image);
        this.recycler_meal_ingredients = (RecyclerView)this.findViewById(R.id.recycler_meal_ingredients);
    }
}