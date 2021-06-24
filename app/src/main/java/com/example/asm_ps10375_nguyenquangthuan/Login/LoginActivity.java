package com.example.asm_ps10375_nguyenquangthuan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.Activity.MainActivity;
import com.example.asm_ps10375_nguyenquangthuan.DataBase.DBHelper;
import com.example.asm_ps10375_nguyenquangthuan.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tv_register;
    DBHelper myDB;
    CheckBox checkBox;
    SharedPreferences luutru;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //check login neu da dang nhap thi cho qua man hinh main
        //CheckLoginStatus();

        // neu chua thi dang nhap


        //anh sa
        edtUsername = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPass);
        btnLogin = findViewById(R.id.btnLogin);
        tv_register = findViewById(R.id.gotoRegister);
        checkBox = findViewById(R.id.checkBox);
        dialog = new Dialog(LoginActivity.this);

        luutru = getSharedPreferences("filematkhau", Context.MODE_PRIVATE);
        // Nạp sẵn thông tin lên Edittext cho lần thứ 2
        // defValue là khoảng trắng nếu như lần đầu tiên vào ứng dụng, user và pass chưa từng được lưu
        if (luutru.getBoolean("save_information", false)) {
            edtUsername.setText(luutru.getString("username_information", ""));
            edtPassword.setText(luutru.getString("password_information", ""));
            checkBox.setChecked(true);
        }


        myDB = new DBHelper(this);

        //register
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                SharedPreferences.Editor editor = luutru.edit();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter Credentials.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean mresult = myDB.checkUserPassword(username, password);
                    if (mresult == true) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        SuccessDialog();

                        //luu tai khoan
                        if (checkBox.isChecked()) {
                            editor.putString("username_information", username);
                            editor.putString("password_information", password);
                        }
                        editor.putBoolean("save_information", checkBox.isChecked());
                        editor.commit();
                    } else {
                        FailedDialog();
                    }
                }

            }

        });
    }

    private void SuccessDialog() {
        dialog.setContentView(R.layout.dialog_custom_success);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn_success = dialog.findViewById(R.id.btn_ok);
        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void FailedDialog() {
        dialog.setContentView(R.layout.dialog_custom_failed);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn_success = dialog.findViewById(R.id.btn_ok_1);
        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
//    private void  CheckLoginStatus(){
//
//        SharedPreferences sharedPreferences = getSharedPreferences("Login_status",MODE_PRIVATE);
//        boolean isLogin = sharedPreferences.getBoolean("filematkhau",false);
//        if (isLogin== true){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//            return;
//        }
//    }

}