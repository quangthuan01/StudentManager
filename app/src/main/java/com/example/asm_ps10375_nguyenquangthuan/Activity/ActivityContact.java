package com.example.asm_ps10375_nguyenquangthuan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.R;

public class ActivityContact extends AppCompatActivity {
    Button btn_tosend;
    EditText edt_name, edt_gmail, edt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        edt_name = findViewById(R.id.name_contact);
        edt_gmail = findViewById(R.id.gmail_contact);
        edt_phone = findViewById(R.id.phone_contact);
        btn_tosend = findViewById(R.id.btn_tosend_contact);

        btn_tosend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation() < 1) {
                    Toast.makeText(ActivityContact.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ActivityContact.this, "Đã Gửi Thành Công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public int validation() {
        if
        (edt_name.getText().toString().isEmpty() || edt_gmail.getText().toString().isEmpty() || edt_phone.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }
}