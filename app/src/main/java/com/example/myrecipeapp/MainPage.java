package com.example.myrecipeapp;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainPage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private LinearLayout DayBox;
    public Button btnAllRecipes, btnCategory;
    public ImageView img;
    public TextView dishName, mealDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DayBox = findViewById(R.id.mainBox);
        btnAllRecipes = findViewById(R.id.buttonAllRecipes);
        btnCategory = findViewById(R.id.buttonCategory);
        drawerLayout=findViewById(R.id.drawer);
        img=findViewById(R.id.imageView_meal_image);
        dishName=findViewById(R.id.textView_meal_name);
        mealDesc=findViewById(R.id.textView_meal_method);

        Database db = new Database(this);
        db.open();

        ArrayList<String> recipes = db.getAllRecipes();
        int randomNum = ThreadLocalRandom.current().nextInt(0, recipes.size());
        String name = recipes.get(randomNum);
        dishName.setText(name);
        img.setImageBitmap(db.getPhoto(name));
        String s = db.getDescription(name);
        s = s.substring(0, Math.min(s.length(), 150));
        mealDesc.setText(s+"...");

        db.close();

        goToAllRecipes();
        goToCategories();

        DayBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPage.this, RecipeDetailsActivity.class);
                i.putExtra("key",name);
                startActivity(i);
            }
        });

    }

    public void ClickTab(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickTitle(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickMyAccount(View view){
        redirectActivity(this,MyAccount.class);
    }


    public void ClickMyPantry(View view){
        redirectActivity(this,MyPantry.class);
    }

    public void ClickMySavedRecipes(View view){
        redirectActivity(this,MySavedRecipes.class);
    }

//    public void ClickSettings(View view){
//        redirectActivity(this,);
//    }

    public static void redirectActivity(Activity activity,Class Class) {
        Intent intent = new Intent(activity,Class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private void goToAllRecipes() {
        btnAllRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(MainPage.this, R.raw.tap);
                mediaPlayer.start();

                Intent j = new Intent(MainPage.this, AllRecipeSearch.class);
                startActivity(j);
            }
        });
    }

    private void goToCategories() {
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(MainPage.this, R.raw.tap);
                mediaPlayer.start();
                Intent i = new Intent(MainPage.this, MealCategories.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
