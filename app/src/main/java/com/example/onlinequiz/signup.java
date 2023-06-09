package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText name, username, password, repassword;
    Button login, signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        username =  findViewById(R.id.email);
        password = findViewById(R.id.pass);
        repassword = findViewById(R.id.repass);
        login = findViewById(R.id.button);
        signup = findViewById(R.id.signup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String uname = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (fname.equals("") || uname.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(signup.this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
                } else if (pass.equals(repass)) {
                    Boolean checkusername = DB.checkusername(uname);
                    if (!checkusername) {
                        boolean insert = DB.insertData(fname, uname, pass);
                        if (insert) {
                            Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signup.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signup.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(signup.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
