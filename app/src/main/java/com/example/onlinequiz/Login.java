package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends AppCompatActivity {

    EditText username, password;
    Button login, signup;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username =  findViewById(R.id.email);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.button);
        signup =findViewById(R.id.signup);
        DB= new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(Login.this,"Please Fill All Fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkuserpass(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this,"Sign In Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login.this, dashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}