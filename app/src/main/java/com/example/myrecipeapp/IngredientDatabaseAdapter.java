package com.example.myrecipeapp;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IngredientDatabaseAdapter {

    DatabaseHelper helper;
    SQLiteDatabase db;

    public IngredientDatabaseAdapter(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();
    }

    //Function to get all recipes
    public List<IngredientCards> getAllIngredients(){
        String columns[] = {DatabaseHelper.KEY_INGID, DatabaseHelper.KEY_NAME};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        List<IngredientCards> ingredientList = new ArrayList<>();
        while(cursor.moveToNext()){
            //first column: rec_id, an INTEGER so int
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_INGID);
            int ingid = cursor.getInt(index1);
            //third column: name, a TEXT so string
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);

            IngredientCards ingredient = new IngredientCards(ingid, name);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    public List<IngredientCards> getSearchedIngredients(String name_serc){
        String columns[] = {DatabaseHelper.KEY_INGID, DatabaseHelper.KEY_NAME};
        String query = "SELECT ing_id, name FROM ingridients where name like '%"+name_serc+"%';";
        Cursor cursor = db.rawQuery(query,null);
        List<IngredientCards> ingredientList = new ArrayList<>();
        while(cursor.moveToNext()){
            //first column: rec_id, an INTEGER so int
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_INGID);
            int ingid = cursor.getInt(index1);
            //third column: name, a TEXT so string
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);

            IngredientCards ingredient = new IngredientCards(ingid, name);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    //Function to all recipe's name
    public List<String> getNames(){
        String columns[] = {DatabaseHelper.KEY_NAME};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,null, null,null, null, null, null);
        List<String> ingredientList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);
            String result = new String(name);
            ingredientList.add(result);
        }
        return ingredientList;
    }

    //Function get all recipes by name
    public List<IngredientCards> getIngredientByName(String names){
        String columns[] = {DatabaseHelper.KEY_INGID, DatabaseHelper.KEY_NAME};

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns,"Name LIKE ?", new String[]{"%"+names+"%"},null, null, null, null);
        List<IngredientCards> ingredientList = new ArrayList<>();
        while(cursor.moveToNext()){
            //first column: rec_id, an INTEGER so int
            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_INGID);
            int ingid = cursor.getInt(index1);
            //third column: name, a TEXT so string
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_NAME);
            String name = cursor.getString(index3);

            IngredientCards ingredient = new IngredientCards(ingid, name);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "recepies.db";
        private static final String TABLE_NAME = "ingridients";
        // When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = 1;
        private static final String KEY_INGID = "ing_id";
        private static final String KEY_NAME = "name";

        private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( "+
                KEY_INGID +"integer," +
                KEY_NAME +"text," +
                KEY_INGID + "AUTOINCREMENT)";

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
