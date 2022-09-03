package com.example.myrecipeapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MealCategories extends AppCompatActivity {

    public Button btnMeat, btnSeaFood, btnGlutenFree, btnSalads, btnVegetarian, btnIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_categories);

        btnGlutenFree = findViewById(R.id.buttonGlutenFree);
        btnIngredient = findViewById(R.id.buttonIngredient);
        btnMeat = findViewById(R.id.buttonMeat);
        btnSalads = findViewById(R.id.buttonSalads);
        btnVegetarian = findViewById(R.id.buttonVeg);
        btnSeaFood = findViewById(R.id.buttonSeaFood);

        goToGlutenFree();
        goToMeat();
        goToSeaFood();
        goToSalads();
        goToVegetarian();
        goToIngredient();
    }

    private void goToVegetarian() {
        btnVegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, VegetarianList.class);
                startActivity(i);
            }
        });
    }

    private void goToIngredient() {
        btnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, IngredientList.class);
                startActivity(i);
            }
        });
    }

    private void goToSeaFood() {
        btnSeaFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, SeaFoodList.class);
                startActivity(i);
            }
        });
    }

    private void goToGlutenFree() {
        btnGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, GlutenFreeList.class);
                startActivity(i);
            }
        });
    }

    private void goToMeat() {
        btnMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, MeatList.class);
                startActivity(i);
            }
        });
    }

    private void goToSalads() {
        btnSalads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MealCategories.this, SaladList.class);
                startActivity(i);
            }
        });
    }
}
