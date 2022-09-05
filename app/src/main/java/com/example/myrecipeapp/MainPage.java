package com.example.myrecipeapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myrecipeapp.ListRecipe;

public class MainPage extends AppCompatActivity {

    private LinearLayout DayBox;
    public Button btnAllRecipes, btnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main_page);

        DayBox = findViewById(R.id.mainBox);
        btnAllRecipes = findViewById(R.id.buttonAllRecipes);
        btnCategory = findViewById(R.id.buttonCategory);

        goToAllRecipes();
        goToCategories();

        DayBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, RecipeDetailsActivity.class));
            }
        });
    }

    private void goToAllRecipes() {
        btnAllRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainPage.this, AllRecipeSearch.class);
                startActivity(j);
            }
        });
    }

    private void goToCategories() {
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this, MealCategories.class);
                startActivity(i);
            }
        });
    }

}