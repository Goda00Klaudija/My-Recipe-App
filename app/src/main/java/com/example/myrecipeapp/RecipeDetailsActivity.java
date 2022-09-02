package com.example.myrecipeapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;
    TextView textView_meal_name;
    TextView textView_meal_method;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients;

    public RecipeDetailsActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(2131427357);
        this.findViews();
        this.id = Integer.parseInt(this.getIntent().getStringExtra("id"));
    }

    private void findViews() {
        this.textView_meal_name = (TextView)this.findViewById(2131231171);
        this.textView_meal_method = (TextView)this.findViewById(2131231170);
        this.imageView_meal_image = (ImageView)this.findViewById(2131230946);
        this.recycler_meal_ingredients = (RecyclerView)this.findViewById(2131231067);
    }
}