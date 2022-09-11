package com.example.myrecipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;

    //SearchView
//    SearchView searchView;

    public DatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    //Function to get all recipes
    public List<RecipesCards> getAllRecipes(){
        String columns[] = {DatabaseHelper.KEY_RECID, DatabaseHelper.KEY_NAME,
                DatabaseHelper.KEY_CAL, DatabaseHelper.KEY_PROTEINS, DatabaseHelper.KEY_FAT, DatabaseHelper.KEY_CARBS};

        // DatabaseHelper.KEY_PHOTO, DatabaseHelper.KEY_DES, ; DatabaseHelper.KEY_FAVE, ; DatabaseHelper.KEY_CAT1, DatabaseHelper.KEY_CAT2, DatabaseHelper.KEY_CAT3
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        List<RecipesCards> recipesList = new ArrayList<>();
        while(cursor.moveToNext()){
            //first column: rec_id, an INTEGER so int
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_RECID);
            int recid = cursor.getInt(index1);
            //second column: type: BLOB so idk...
//            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_PHOTO);
//            Bitmap photo = cursor.getPhoto(index2); //when i make this into getPhoto, throws out error.
            //third column: name, a TEXT so string
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);
//            //fourth column: description, a TEXT so string
//            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_DES);
//            String description = cursor.getString(index4);
//            //firth column: an is_favourite, an INTEGER so int
//            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_FAVE);
//            int favourite = cursor.getInt(index5);
            //sixth column: calories, a REAL so idk, try string
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_CAL);
            String calories = cursor.getString(index6);
            //seventh column: proteins a REAL so idk, try string
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_PROTEINS);
            String proteins = cursor.getString(index7);
            //eigth column: fat a REAL so idk, try string
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_FAT);
            String fat = cursor.getString(index8);
            //ninth column: carbs a REAL so idk, try string
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_CARBS);
            String carbs = cursor.getString(index9);
//            //tenth column: category1, a TEXT so string
//            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT1);
//            String category1 = cursor.getString(index10);
//            //eleventh column: category2, a TEXT so string
//            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT2);
//            String category2 = cursor.getString(index11);
//            //twelfth column: category3, a TEXT so string
//            int index12 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT3);
//            String category3 = cursor.getString(index12);

            RecipesCards recipe = new RecipesCards(recid, name, calories, proteins, fat, carbs);
            recipesList.add(recipe);
        }
        return recipesList;
    }

    //Function to all recipe's name
    public List<String> getNames(){
        String columns[] = {DatabaseHelper.KEY_NAME};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        List<String> recipesList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);
            String result = new String(name);
            recipesList.add(result);
        }
        return recipesList;
    }

    //Function get all recipes by name
    public List<RecipesCards> getRecipeByName(String names){
        String columns[] = {DatabaseHelper.KEY_RECID, DatabaseHelper.KEY_NAME,
                DatabaseHelper.KEY_CAL, DatabaseHelper.KEY_PROTEINS, DatabaseHelper.KEY_FAT, DatabaseHelper.KEY_CARBS};

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,"Name LIKE ?", new String[]{"%"+names+"%"},null, null, null, null);
        List<RecipesCards> recipesList = new ArrayList<>();
        while(cursor.moveToNext()){
            //first column: rec_id, an INTEGER so int
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_RECID);
            int recid = cursor.getInt(index1);
            //second column: type: BLOB so idk...
//            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_PHOTO);
//            Bitmap photo = cursor.getPhoto(index2); //when i make this into getPhoto, throws out error.
            //third column: name, a TEXT so string
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);
//            //fourth column: description, a TEXT so string
//            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_DES);
//            String description = cursor.getString(index4);
//            //firth column: an is_favourite, an INTEGER so int
//            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_FAVE);
//            int favourite = cursor.getInt(index5);
            //sixth column: calories, a REAL so idk, try string
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_CAL);
            String calories = cursor.getString(index6);
            //seventh column: proteins a REAL so idk, try string
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_PROTEINS);
            String proteins = cursor.getString(index7);
            //eigth column: fat a REAL so idk, try string
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_FAT);
            String fat = cursor.getString(index8);
            //ninth column: carbs a REAL so idk, try string
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_CARBS);
            String carbs = cursor.getString(index9);
//            //tenth column: category1, a TEXT so string
//            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT1);
//            String category1 = cursor.getString(index10);
//            //eleventh column: category2, a TEXT so string
//            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT2);
//            String category2 = cursor.getString(index11);
//            //twelfth column: category3, a TEXT so string
//            int index12 = cursor.getColumnIndex(DatabaseHelper.KEY_CAT3);
//            String category3 = cursor.getString(index12);

            RecipesCards recipe = new RecipesCards(recid, name, calories, proteins, fat, carbs);
            recipesList.add(recipe);
        }
        return recipesList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "recepies.db";
        private static final String TABLE_NAME = "recepies";
        // When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = 1;
        private static final String KEY_RECID = "rec_id";
        //        private static final String KEY_PHOTO = "photo";
        private static final String KEY_NAME = "name";
        //        private static final String KEY_DES = "description";
//        private static final String KEY_FAVE = "is_favourite";
        private static final String KEY_CAL = "calories";
        private static final String KEY_PROTEINS = "proteins";
        private static final String KEY_FAT = "fat";
        private static final String KEY_CARBS = "carbs";
        //        private static final String KEY_CAT1 = "category1";
//        private static final String KEY_CAT2 = "category2";
//        private static final String KEY_CAT3 = "category3";
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( "+
                KEY_RECID +"integer," +
                KEY_NAME +"text," +
                KEY_CAL +"real," +
                KEY_PROTEINS +"real," +
                KEY_FAT +"real," +
                KEY_CARBS +"real," +
                KEY_RECID + "AUTOINCREMENT)";

        //KEY_PHOTO +"blob," + ; +KEY_DES+"text," ; +KEY_FAVE+"integer default 0," ; +KEY_CAT1+"text," +KEY_CAT2+"text," ; +KEY_CAT3+"text,primary key("
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                //db.execSQL(CREATE_TABLE);
                Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (SQLException e){
                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
