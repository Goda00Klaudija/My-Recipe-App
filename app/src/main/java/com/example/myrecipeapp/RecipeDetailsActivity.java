package com.example.myrecipeapp;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;

    public TextView textView_meal_name, textView_meal_method;
    public ImageView imageView_meal_image;
    public ListView list_meal_ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("key");

        getWindow().setLayout((int)(width*.92),(int)(height*.97));

        findViews();

        //for testing purposes all code below
        Database db = new Database(this);
        db.open();

        textView_meal_method.setText(db.getDescription(name));
        textView_meal_name.setText(name);
        imageView_meal_image.setImageBitmap(db.getPhoto(name));
        ArrayList<String> ingredients= db.getIngredients(name);
        ArrayList<String> amount = db.getAmount(name);
        ArrayList<String> measurement = db.getMeasurement(name);
        String[] ing = new String[ingredients.size()];
        for (int i = 0; i<ingredients.size();i++){
            ing[i] = ingredients.get(i) +" "+ amount.get(i) +" "+ measurement.get(i);
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, ing);
        list_meal_ingredients.setAdapter(adapter);

    }

    private void findViews() {
        this.textView_meal_name = (TextView)this.findViewById(R.id.textView_meal_name);
        this.textView_meal_method = (TextView)this.findViewById(R.id.textView_method);
        this.imageView_meal_image = (ImageView)this.findViewById(R.id.imageView_meal_image);
        this.list_meal_ingredients = (ListView)this.findViewById(R.id.listView_details);
    }
}