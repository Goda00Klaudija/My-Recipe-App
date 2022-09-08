package com.example.myrecipeapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

//hi
public class AllRecipeSearch extends AppCompatActivity {

    DrawerLayout drawerLayout;

//    String []data = {"Hello", "Hi", "Welcome"};
//    int counter = 0;
//    ProgressDialog dialog;
//    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe_search);

        drawerLayout = findViewById(R.id.drawer_layout4);

//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Loading...");

//        List<String> items = new LinkedList<>();
//        items.add("Code it");

//        RecyclerView recyclerList = findViewById(R.id.recycler_list);
//        recyclerList.setLayoutManager(new LinearLayoutManager(this));
//        RecipeAdapter adapter = new RecipeAdapter();
//        recyclerList.setAdapter(adapter);

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

    public void ClickMySavedRecipes(View view){
        MainPage.redirectActivity(this,MySavedRecipes.class);
    }

}
