package com.example.asm_ps10375_nguyenquangthuan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.DAO.ClassDAO;
import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.R;

public class MainActivity extends AppCompatActivity {
    ImageView img_add_main, img_list_main, img_manager_main, img_contact_main;
    ClassDAO classDAO;
    ClassModel classModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhsa();
        classDAO = new ClassDAO(this);
        classModel = new ClassModel();
        //add class
        img_add_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show dialog
                Dialog dialog;
                dialog = new Dialog(MainActivity.this);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                //setmau dialog bo vien
             //   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.item_add_class);

                //anhsa

                EditText edt_Nameclass = dialog.findViewById(R.id.edt_item_tenlop);
                EditText edt_Idclass = dialog.findViewById(R.id.edt_item_malop);
                Button btn_Add = dialog.findViewById(R.id.btn_add_item_class);
                Button btn_delete = dialog.findViewById(R.id.btn_delete_item_class);

                //xu ly su kien
                btn_Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String idClass = edt_Idclass.getText().toString().trim();
                        String nameCLass = edt_Nameclass.getText().toString().trim();
                        Boolean checkMaLop = classDAO.checkClassID(idClass);
                        if (edt_Idclass.getText().length() == 0 || edt_Idclass.getText().length() == 0) {
                            Toast.makeText(MainActivity.this, "Chưa Nhập Thông Tin", Toast.LENGTH_SHORT).show();
                        } else if (checkMaLop == false) {
                            Toast.makeText(MainActivity.this, "Mã Lớp Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (edt_Nameclass.getText().length() == 0) {
                            Toast.makeText(MainActivity.this, "Vui Lòng Nhập Tên Lớp", Toast.LENGTH_SHORT).show();
                        } else {
                            ClassModel classModel = new ClassModel(idClass, nameCLass);
                            ClassDAO daoClass = new ClassDAO(MainActivity.this);
                            daoClass.insert(classModel);

                            //thong BAO
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                            alertDialog.setTitle("Thông Báo");
                            alertDialog.setIcon(R.drawable.presentation);
                            alertDialog.setMessage("Bạn Đã Thêm Thành Công Lớp " + edt_Nameclass.getText().toString() + ".");
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog dialog1 = alertDialog.create();
                            dialog1.show();
                            //    Toast.makeText(MainActivity.this, "Thêm Lớp "+edt_Nameclass.getText().toString()+ " Thành Công", Toast.LENGTH_SHORT).show();


                            //lam moi edt
                            edt_Idclass.setText("");
                            edt_Nameclass.setText("");
                        }
                    }
                });
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        //classlist
        img_list_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityList.class);
                startActivity(intent);
            }
        });
        //student manager
        img_manager_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityManager.class);
                startActivity(intent);
            }
        });
        //contact
        img_contact_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityContact.class);
                startActivity(intent);
            }
        });

    }


    private void anhsa() {

        img_add_main = (ImageView) findViewById(R.id.add_img_main);
        img_list_main = (ImageView) findViewById(R.id.list_img_main);
        img_manager_main = (ImageView) findViewById(R.id.manager_img_main);
        img_contact_main = (ImageView) findViewById(R.id.contact_img_main);

    }
}