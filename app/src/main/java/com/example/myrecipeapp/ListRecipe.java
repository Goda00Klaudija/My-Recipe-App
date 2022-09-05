package com.example.myrecipeapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ListRecipe extends AppCompatActivity {

    private LinearLayout mealBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        mealBox = findViewById(R.id.mealBox);

        goToDetails();
    }
    private void goToDetails() {
        mealBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListRecipe.this, RecipeDetailsActivity.class);
                startActivity(i);
            }
        });
    }
}