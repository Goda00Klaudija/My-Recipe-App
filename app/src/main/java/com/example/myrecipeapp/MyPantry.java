package com.example.myrecipeapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


public class MyPantry extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);

        drawerLayout = findViewById(R.id.drawer_layout2);
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
        recreate();
    }

    public void ClickMySavedRecipes(View view){
        MainPage.redirectActivity(this,MySavedRecipes.class);
    }
}
