package com.example.myrecipeapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MySavedRecipes extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_saved_recipes);

        drawerLayout = findViewById(R.id.drawer_layout3);
    }

    public void ClickTab(View view){
        MainPage.openDrawer(drawerLayout);
    }

    public void ClickTitle(View view){
        MainPage.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        MainPage.redirectActivity(this,MainPage.class);
    }

    public void ClickMyAccount(View view){
        MainPage.redirectActivity(this,MyAccount.class);
    }

    public void ClickMyPantry(View view){
        MainPage.redirectActivity(this,MyPantry.class);
    }

//    public void ClickMySavedRecipes(View view){
//        recreate();
//    }
}

