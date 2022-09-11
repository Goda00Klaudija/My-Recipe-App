package com.example.myrecipeapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;

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

    //------------------------------------
    //For SearchView
//    SearchView searchView;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    //------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipe_search);

        //Needed for Navigation
        drawerLayout = findViewById(R.id.drawer_layout4);

//        //For SearchView
//        searchView = findViewById(R.id.searchView_home);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

        //------------------------------------
        //Attempt 400 For SearchView
        //init view
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchView_home);
        //init DB
        databaseAdapter = new DatabaseAdapter(this);
        //Setup search bar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestedList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for (String search: suggestList)
                {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);
            }
            @Override
            public void afterTextChanged (Editable editable){
                }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recycler_list.setAdapter(recipeAdapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
            }
        });
        //Attempt 400 finished
        //------------------------------------

        //Connecting to DB
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
    //------------------------------------
    //Attempt 400 For SearchView
    private void startSearch(String text) {
        recipeAdapter = new RecipeAdapter(this,databaseAdapter.getRecipeByName(text));
        recycler_list.setAdapter(recipeAdapter);
    }

    private void loadSuggestedList() {
        suggestList = databaseAdapter.getNames(); //suggestList    (Database) database.getNames()
        materialSearchBar.setLastSuggestions(suggestList);

    }
    //Attempt 400 For SearchView finished
    // ------------------------------------

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
//        MainPage.redirectActivity(this,MySavedRecipes.class);
//    }

}
