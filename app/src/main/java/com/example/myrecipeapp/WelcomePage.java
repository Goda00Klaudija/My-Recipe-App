package com.example.myrecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {

    private EditText username, password;
    private Button loginBtn, registerBtn;
    private DBHelper DBHelper;
    private TextView forgotPassword;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        DBHelper = new DBHelper(getApplicationContext());
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.btnLogin);
        registerBtn = findViewById(R.id.btnMoveRegister);
        forgotPassword = findViewById(R.id.txtForgotPassword);
        checkBox = findViewById(R.id.checkBox);

        if (DBHelper.ifSaved() == 1){
            username.setText(DBHelper.getUsername());
            password.setText(DBHelper.getPassword());
            checkBox.setChecked(true);
        }

        goToRegister();
        login();
        returnForgotPassword();
    }


    private void goToRegister() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomePage.this, RegisterPage.class);
                startActivity(i);
            }
        });
    }
        private void login() {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String user = username.getText().toString();
                    String pass = password.getText().toString();

                    if(user.equals("")|| pass.equals("")){
                        Toast.makeText(WelcomePage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean checkUserPass = DBHelper.checkUsernamePassword(user, pass);
                        if(checkUserPass == true){
                            Toast.makeText(WelcomePage.this, "Sign in successfull!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WelcomePage.this, MainPage.class);
                            DBHelper.putUser(user);
                            if (checkBox.isChecked()){
                                DBHelper.saveCredentials(user,pass);
                            } else {
                                DBHelper.dropCredentials();
                            }
                            startActivity(intent);
                        }else{
                            Toast.makeText(WelcomePage.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    }

    private void returnForgotPassword(){
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this, ForgotPaassword.class);
                startActivity(intent);
            }
        });
    }
}