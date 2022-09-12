package com.example.myrecipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


public class MyPantry extends AppCompatActivity {

    public TextView btnAdd;
    DrawerLayout drawerLayout;
    public TextView username_tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);

        btnAdd = findViewById(R.id.add);

        username_tab = findViewById(R.id.username_tab);
        DBHelper dbHelper = new DBHelper(this);
        username_tab.setText(dbHelper.getUsername());

        //Needed for Navigation
        drawerLayout = findViewById(R.id.drawer_layout2);

        goToAdd();
    }

    private void goToAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPantry.this, IngredientList.class);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onPause(){
        super.onPause();
        MainPage.closeDrawer(drawerLayout);
    }
}
