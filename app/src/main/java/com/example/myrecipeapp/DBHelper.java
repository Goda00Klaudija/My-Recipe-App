package com.example.myrecipeapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login.db";
    public static final String TABLE_NAME = "accounts_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "EMAIL";
    public static final String COL3 = "USERNAME";
    public static final String COL4 = "PASSWORD";
    public static final String COL5 = "PICTURE";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable =  "CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " BLOB)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE photo_table ( photoID TEXT, image BLOB);";
        db.execSQL(createTable);

        createTable = "CREATE TABLE user (userID TEXT);";
        db.execSQL(createTable);

        createTable = "CREATE TABLE rememberMe (user TEXT, pass TEXT);";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String dbEmail, String dbUsername, String dbPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, dbEmail);
        contentValues.put(COL3, dbUsername);
        contentValues.put(COL4, dbPassword);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public void saveCredentials(String user, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "DELETE FROM rememberMe;";
        db.execSQL(str);
        str = "INSERT INTO rememberMe (user,pass) VALUES ('"+user+"','"+pass+"');";
        db.execSQL(str);
    }

    public void dropCredentials(){
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "DELETE FROM rememberMe;";
        db.execSQL(str);
    }

    public void putUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "DELETE FROM user;";
        db.execSQL(str);
        str = "INSERT INTO user (userID) VALUES ('"+user+"');";
        db.execSQL(str);
    }

    public String getEmail(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT EMAIL FROM accounts_table WHERE USERNAME = '"+user+"';";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return  cursor.getString(0);
    }

    public String getUsername(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT user FROM rememberMe";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getPassword(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT pass FROM rememberMe";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public String getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM user";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public Boolean checkData(String dbEmail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM accounts_table WHERE EMAIL = ?",new String[] {dbEmail});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean checkUsernamePassword(String dbUsername, String dbPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM accounts_table WHERE USERNAME = ? AND PASSWORD = ?", new String[] {dbUsername, dbPassword});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public void addEntry( String name, byte[] image) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put("photoID",    name);
        cv.put("image",   image);
        db.insert( "photo_table", null, cv );
    }

    public void updateEntry( String name, byte[] image) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "DELETE FROM photo_table WHERE photoID = '"+name+"';";
        db.execSQL(strSQL);
        addEntry(name,image);
    }

    public int ifSaved() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT EXISTS(SELECT 1 FROM rememberMe);";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int ifExists(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT EXISTS(SELECT 1 FROM photo_table WHERE photoID='"+name+"');";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getInt(0);

    }
    public Bitmap getPhoto(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select image FROM photo_table where photoID = '"+name+"'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        byte[] bytesImage = cursor.getBlob(0);
        cursor.close();
        return BitmapFactory.decodeByteArray(bytesImage,0,bytesImage.length);
    }

}
