package com.example.myrecipeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class  MyAccount extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button BSelectImage;
    DBHelper dbHelper;
    TextView nameSurname;
    TextView email;
    TextView changePassword;

    ImageView IVPreviewImage;

    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        dbHelper = new DBHelper(this);

        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        drawerLayout=findViewById(R.id.drawer_layout);
        nameSurname = findViewById(R.id.txtNameSurname);
        email = findViewById(R.id.txtEMail);
        //changePassword=findViewById(R.id.txtChangePassword);

        String user = dbHelper.getUser();
        try {
            Bitmap bitmap = dbHelper.getPhoto(user);
            IVPreviewImage.setImageBitmap(bitmap);
        } catch (Exception e){}

        email.setText(dbHelper.getEmail(user));
        nameSurname.setText(user);

//        changePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // Intent i = new Intent(MyAccount.this, change_password.class);
//               // startActivity(i);
//            }
//        });

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(MyAccount.this, R.raw.tap);
                mediaPlayer.start();
                imageChooser();
            }
        });


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
        recreate();
    }

    public void ClickMyPantry(View view){
        MainPage.redirectActivity(this,MyPantry.class);
    }

//    public void ClickMySavedRecipes(View view){
//        MainPage.redirectActivity(this,MySavedRecipes.class);
//    }

//     this function is triggered when
//     the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                    try {
                        String user = dbHelper.getUser();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        byte[] bytesImage = getBytes(bitmap);
                        if(dbHelper.ifExists(user) == 1){
                            dbHelper.updateEntry(user, bytesImage);
                        } else{
                            dbHelper.addEntry(user, bytesImage);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onPause(){
        super.onPause();
        MainPage.closeDrawer(drawerLayout);
    }
}

