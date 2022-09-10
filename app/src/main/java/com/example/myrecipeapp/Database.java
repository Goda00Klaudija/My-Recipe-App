package com.example.myrecipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Database {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recepies.db";
    private static final String DATABASE_TABLE = "recepies";
    public static final String DATABASE_ID = "rec_id";
    String[] columns = {"rec_id",
            "photo",
            "name",
            "description",
            "is_favorite",
            "calories",
            "proteins",
            "fat",
            "carbs",
            "category1",
            "category2",
            "category3",
    };
    private final Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mDB;

    public Database(Context context) {
        mContext = context;
    }

    public void open() {
        mDatabaseHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDatabaseHelper.getWritableDatabase();
    }


    public void close() {
        if (mDatabaseHelper != null) mDatabaseHelper.close();
    }

    public Cursor getDatabase() {
        return mDB.query(DATABASE_TABLE, columns, null, null, null, null, null);
    }

    public Cursor getDescriptionCursor(String name) {
        String query="SELECT * from recepies where name = '"+name+"';";
        return mDB.rawQuery(query,null);
    }

    public String getDescription(String name){
        String description = "";
        Cursor cursor = this.getDescriptionCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("description");
            description = cursor.getString(index);
        }
        return description;
    }

    public String getProteins(String name){
        String query="Select proteins from recepies where name = '"+name+"';";
        Cursor cursor = mDB.rawQuery(query,null);
        cursor.moveToFirst();
        float temp = cursor.getFloat(0);
        return String.valueOf(temp);
    }

    public String getCalories(String name){
        String query="Select calories from recepies where name = '"+name+"';";
        Cursor cursor = mDB.rawQuery(query,null);
        cursor.moveToFirst();
        float temp = cursor.getFloat(0);
        return String.valueOf(temp);
    }

    public String getFat(String name){
        String query="Select fat from recepies where name = '"+name+"';";
        Cursor cursor = mDB.rawQuery(query,null);
        cursor.moveToFirst();
        float temp = cursor.getFloat(0);
        return String.valueOf(temp);
    }

    public String getCarbs(String name){
        String description = "";
        String query="Select carbs from recepies where name = '"+name+"';";
        Cursor cursor = mDB.rawQuery(query,null);
        cursor.moveToFirst();
        float temp = cursor.getFloat(0);
        return String.valueOf(temp);
    }

    public Bitmap getPhoto(String name){
        String query = "select photo FROM recepies where name = '"+name+"'";
        Cursor cursor = mDB.rawQuery(query, null);
        cursor.moveToFirst();
        byte[] bytesImage = cursor.getBlob(0);
        cursor.close();
        return BitmapFactory.decodeByteArray(bytesImage,0,bytesImage.length);
    }

    public Cursor getIngredientsCursor(String name){
        String query="select ingridients.name from ing_to_rec INNER JOIN ingridients on ing_to_rec.ingridient_id = ingridients.ing_id INNER JOIN recepies on recepies.rec_id = ing_to_rec.recepie_id where recepies.name = '"+name+"';";
        return mDB.rawQuery(query,null);
    }

    public ArrayList getIngredients(String name){
        ArrayList<String> Ingredients = new ArrayList<String>();
        Cursor cursor = this.getIngredientsCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            Ingredients.add(cursor.getString(index));
        }
        return Ingredients;
    }

    public Cursor getAmountCursor(String name){
        String query="select ing_to_rec.amount from ing_to_rec INNER JOIN ingridients on ing_to_rec.ingridient_id = ingridients.ing_id INNER JOIN recepies on recepies.rec_id = ing_to_rec.recepie_id where recepies.name = '"+name+"';";
        return mDB.rawQuery(query,null);
    }

    public ArrayList getAmount(String name){
        ArrayList<String> Ingredients = new ArrayList<String>();
        Cursor cursor = this.getAmountCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("amount");
            Ingredients.add(cursor.getString(index));
        }
        return Ingredients;
    }

    public Cursor getMeasurementCursor(String name){
        String query="select ing_to_rec.mesurment from ing_to_rec INNER JOIN ingridients on ing_to_rec.ingridient_id = ingridients.ing_id INNER JOIN recepies on recepies.rec_id = ing_to_rec.recepie_id where recepies.name = '"+name+"';";
        return mDB.rawQuery(query,null);
    }

    public ArrayList getMeasurement(String name){
        ArrayList<String> Ingredients = new ArrayList<String>();
        Cursor cursor = this.getMeasurementCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("mesurment");
            Ingredients.add(cursor.getString(index));
        }
        return Ingredients;
    }

    public Cursor getAllRecipesCursor(){
        String query="select name from recepies";
        return mDB.rawQuery(query,null);
    }

    public ArrayList getAllRecipes(){
        ArrayList<String> recipes = new ArrayList<String>();
        Cursor cursor = this.getAllRecipesCursor();
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            recipes.add(cursor.getString(index));
        }
        return recipes;
    }

    public Cursor getSearchedRecipesCursor(String name){
        String query="select name from recepies where name like '%"+name+"%';";
        return mDB.rawQuery(query,null);
    }

    public ArrayList getSearchedRecipes(String name){
        ArrayList<String> Search = new ArrayList<>();
        Cursor cursor = this.getSearchedRecipesCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            Search.add(cursor.getString(index));
        }
        return Search;
    }

    public Cursor searchCategoryAllCursor(String name){
        String query="Select name from recepies WHERE category1 = '"+name+"' or category2 = '"+name+"' or category3 = '"+name+"';";
        return mDB.rawQuery(query,null);
    }

    public ArrayList searchCategoryAll(String name){
        ArrayList<String> Search = new ArrayList<>();
        Cursor cursor = this.searchCategoryAllCursor(name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            Search.add(cursor.getString(index));
        }
        return Search;
    }

    public Cursor searchCategoryCursor(String category, String name){
        String query="Select name from recepies where (category1= '"+category+"' or category2 ='"+category+"' or category3 = '"+category+"') and name like '%"+name+"%'";
        return mDB.rawQuery(query,null);
    }

    public ArrayList searchCategory(String category,String name){
        ArrayList<String> Search = new ArrayList<>();
        Cursor cursor = this.searchCategoryCursor(category, name);
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            Search.add(cursor.getString(index));
        }
        return Search;
    }

    public Cursor filterFavoritesCursor(){
        String query="SELECT name from recepies where is_favorite = 1;";
        return mDB.rawQuery(query,null);
    }

    public ArrayList filterFavorites(){
        ArrayList<String> Search = new ArrayList<>();
        Cursor cursor = this.filterFavoritesCursor();
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("name");
            Search.add(cursor.getString(index));
        }
        return Search;
    }

    public void addFavorites(String name){
        String query = "UPDATE recepies SET is_favorite = 1 WHERE name = '"+name+"';";
        mDB.execSQL(query);
    }

    public void deleteFavorites(String name){
        String query = "UPDATE recepies SET is_favorite = 0 WHERE name = '"+name+"';";
        mDB.execSQL(query);
    }

    public Cursor getID(long rowID) {
        return mDB.query(DATABASE_TABLE, null, "rec_id" + " = "
                + rowID, null, null, null, null);
    }

    public class DatabaseHelper extends SQLiteAssetHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
    }

    public String getDescription2 (String name){
        Cursor cursor = this.getDatabase();
        String desc="";
        while(cursor.moveToNext()) {
            int index;
            index = cursor.getColumnIndexOrThrow("description");
            desc = cursor.getString(index);
            cursor.close();
        }
        return desc;
    }

}
