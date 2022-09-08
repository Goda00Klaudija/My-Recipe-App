package com.example.myrecipeapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ListRecipe extends AppCompatActivity{

    private CardView list_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        list_container = findViewById(R.id.list_container);

        goToDetails();
    }

    private void goToDetails() {
        list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListRecipe.this, RecipeDetailsActivity.class);
                startActivity(i);
            }
        });
    }

}