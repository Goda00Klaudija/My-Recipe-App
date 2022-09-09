package com.example.myrecipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RecipesCards {

    //Model Class
    private long rec_id;
    private String name;
    //    private String comment;
//    private String photo;
    private String  calories;
    private String proteins;
    private String carbs;
    private String fat;

    //Constructor
    public RecipesCards(long rec_id, String name, String calories, String protein, String carbs, String fat) {
        //, String textView_comment , String imageView_meal_image,
        this.rec_id = rec_id;
        this.name = name;
//        this.comment = textView_comment;
//        this.photo = imageView_meal_image;
        this.calories = calories;
        this.proteins = protein;
        this.carbs = carbs;
        this.fat = fat;
    }


    public long getRec_Id() {
        return rec_id;
    }

    public String getName() {
        return name;
    }

//    public String getPhoto() {
//        return photo;
//    }

    public String getCalories() {
        return calories;
    }

    public String getProteins() {
        return proteins;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getFat() {
        return fat;
    }
}
