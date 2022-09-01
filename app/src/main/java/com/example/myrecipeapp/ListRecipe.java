package com.example.recipeapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.recipeapp.ListRecipe.1;

public class ListRecipe extends AppCompatActivity {
    private TextView text;

    public ListRecipe() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void goToRegister() {
        this.text.setOnClickListener(new 1(this));
    }
}