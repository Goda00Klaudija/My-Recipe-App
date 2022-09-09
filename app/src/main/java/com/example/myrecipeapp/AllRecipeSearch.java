package com.example.myrecipeapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//hi
public class AllRecipeSearch extends AppCompatActivity {

    DrawerLayout drawerLayout;

    //New attempt
    DatabaseAdapter databaseAdapter;
    RecyclerView recycler_list;
    RecipeAdapter recipeAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<RecipesCards> recipesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe_search);

        //Needed for Navigation
        drawerLayout = findViewById(R.id.drawer_layout4);

        //New attempt
        DBHelperRecipes.copyDB(this);
        databaseAdapter = new DatabaseAdapter(this);
        recipesList = databaseAdapter.getAllRecipes();
        recycler_list = findViewById(R.id.recycler_list);
        recycler_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_list.setLayoutManager(layoutManager);
        recipeAdapter = new RecipeAdapter(this, recipesList, recycler_list);
        recycler_list.setAdapter(recipeAdapter);
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
