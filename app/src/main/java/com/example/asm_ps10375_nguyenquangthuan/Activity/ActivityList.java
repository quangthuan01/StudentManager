package com.example.asm_ps10375_nguyenquangthuan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.Adapter.ClassAdapter;
import com.example.asm_ps10375_nguyenquangthuan.DAO.ClassDAO;
import com.example.asm_ps10375_nguyenquangthuan.DAO.StudentDAO;
import com.example.asm_ps10375_nguyenquangthuan.DataBase.DBHelper;
import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;
import com.example.asm_ps10375_nguyenquangthuan.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {
    ListView listView_danhsach;
    List<ClassModel> list;
    ClassAdapter classAdapter;
    ClassModel classModel;
    DBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //anhsa
        listView_danhsach = findViewById(R.id.lv_activity_list);

        //tra ve danh sach
        list = new ArrayList<>();
        list = (new ClassDAO(this)).getall();
        //set adapter
        classAdapter = new ClassAdapter(this, list);
        listView_danhsach.setAdapter(classAdapter);



        listView_danhsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //dialog
                Dialog dialog = new Dialog(ActivityList.this);
                dialog.setContentView(R.layout.layout_detell_list);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animations;
                classModel = (ClassModel) classAdapter.getItem(i);

                EditText edt_classname = (EditText) dialog.findViewById(R.id.editTextClassName);
                EditText edt_classId = (EditText) dialog.findViewById(R.id.editTextClassID);
                Button btn_delete = (Button) dialog.findViewById(R.id.btn_delete_listdetell);
                Button btn_update = (Button) dialog.findViewById(R.id.btn_update_listdetell);

                edt_classname.setText(classModel.getClassName());
                edt_classId.setText(classModel.getClassId());

              //  Toast.makeText(ActivityList.this, "Bạn Đã Chọn Lớp "+edt_classname.getText().toString()+".", Toast.LENGTH_SHORT).show();

                //delete
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //thong BAO
                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(ActivityList.this);
                        alertDialog1.setTitle("Thông Báo");
                        alertDialog1.setIcon(R.drawable.presentation);
                        alertDialog1.setMessage("Bạn Có Chắc Chắn Muốn Xóa Lớp :  " + edt_classname.getText().toString() + " Hay Không .");
                        alertDialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ClassDAO classDAO = new ClassDAO(ActivityList.this);
                                classDAO.delete(classModel.getClassId());
                                finish();
                                startActivity(getIntent());

                            }   
                        });
                        alertDialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog1 = alertDialog1.create();
                        dialog1.show();
                      //  Toast.makeText(ActivityList.this, "Delete Successfull", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                //update
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String _id = edt_classId.getText().toString();
                        String _name = edt_classname.getText().toString();
                        ClassDAO classDAO = new ClassDAO(ActivityList.this);
                        classModel.setClassId(_id);
                        classModel.setClassName(_name);
                        classDAO.update(classModel);
                        classAdapter.notifyDataSetChanged();

                        //thong BAO
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ActivityList.this);
                        alertDialog.setTitle("Thông Báo");
                        alertDialog.setIcon(R.drawable.presentation);
                        alertDialog.setMessage("Bạn Đã Sửa Thành Công Lớp " + edt_classname.getText().toString() + ".");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog1 = alertDialog.create();
                        dialog1.show();
                   //     Toast.makeText(ActivityList.this, "Update Successful", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


}