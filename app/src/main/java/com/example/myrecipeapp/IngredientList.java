package com.example.myrecipeapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class IngredientList extends AppCompatActivity {

    public Button btnAdd;
    ImageView btnBack;

    //New attempt
    IngredientDatabaseAdapter ingredientDatabaseAdapter;
    RecyclerView recycler_ingredientList;
    IngredientAdapter ingredientAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<IngredientCards> ingredientList = new ArrayList<>();

    //------------------------------------
    //For SearchView
//    SearchView searchView;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    //------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);

        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);

        goToBack();

        //------------------------------------
        //Attempt 400 For SearchView
        //init view
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchView_ingredients);
        //init DB
        ingredientDatabaseAdapter = new IngredientDatabaseAdapter(this);
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
                    recycler_ingredientList.setAdapter(ingredientAdapter);
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
        ingredientDatabaseAdapter = new IngredientDatabaseAdapter(this);
        ingredientList = ingredientDatabaseAdapter.getAllIngredients();
        recycler_ingredientList = findViewById(R.id.recycler_ingredientList);
        recycler_ingredientList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_ingredientList.setLayoutManager(layoutManager);
        ingredientAdapter = new IngredientAdapter(this, ingredientList, recycler_ingredientList);
        recycler_ingredientList.setAdapter(ingredientAdapter);

    }

    private void goToBack() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(IngredientList.this, R.raw.tap);
                mediaPlayer.start();

                Intent j = new Intent(IngredientList.this, MyPantry.class);
                startActivity(j);
            }
        });
    }

    //------------------------------------
    //Attempt 400 For SearchView
    private void startSearch(String text) {
        ingredientAdapter = new IngredientAdapter(this,ingredientDatabaseAdapter.getIngredientByName(text));
        recycler_ingredientList.setAdapter(ingredientAdapter);
    }

    private void loadSuggestedList() {
        suggestList = ingredientDatabaseAdapter.getNames(); //suggestList    (Database) database.getNames()
        materialSearchBar.setLastSuggestions(suggestList);

    }
    //Attempt 400 For SearchView finished
    // ------------------------------------

    public void onItemClickAddToCart(){

    }

}