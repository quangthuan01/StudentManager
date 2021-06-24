package com.example.asm_ps10375_nguyenquangthuan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.DataBase.DBHelper;
import com.example.asm_ps10375_nguyenquangthuan.R;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password,gmail, confimpassword;
    Button mRegister;
    TextView tvgotoLogin;
    DBHelper myDB;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvgotoLogin = findViewById(R.id.tv_haveAccout);

        username = findViewById(R.id.edtNameRegister);
        password = findViewById(R.id.edtPasswordRegister);
        confimpassword = findViewById(R.id.edtConFimPasswordRegister);
        gmail =findViewById(R.id.edtGmailRegister);

        mRegister = findViewById(R.id.btnRegister);

        myDB = new DBHelper(this);

        tvgotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confimpass = confimpassword.getText().toString();

                if (user.equals("") || pass.equals("") || confimpass.equals("")) {

                    Toast.makeText(RegisterActivity.this, " Fill all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(confimpass)) {
                        Boolean usercheckResult = myDB.checkUser(user);
                        if (usercheckResult == false) {
                            Boolean mResult = myDB.insertData(user, pass);
                            if (mResult == true) {
                                Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already Exists.\n Please Login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not Matching.", Toast.LENGTH_SHORT).show();
                    }
                }
                username.setText("");
                password.setText("");
                gmail.setText("");
                confimpassword.setText("");

            }
        });
    }

}