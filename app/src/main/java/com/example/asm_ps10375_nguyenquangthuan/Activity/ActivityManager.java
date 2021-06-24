package com.example.asm_ps10375_nguyenquangthuan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_ps10375_nguyenquangthuan.Adapter.SpinnerAdapter;
import com.example.asm_ps10375_nguyenquangthuan.Adapter.StudentAdapter;
import com.example.asm_ps10375_nguyenquangthuan.DAO.ClassDAO;
import com.example.asm_ps10375_nguyenquangthuan.DAO.StudentDAO;
import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;
import com.example.asm_ps10375_nguyenquangthuan.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class ActivityManager extends AppCompatActivity {

    TextInputEditText edt_tenSv, edt_masinhvien;
    Spinner spn_class;
    TextView txt_ngaysinh;
    Button btn_add, btn_ngaysinh;
    StudentAdapter studentAdapter;
    StudentDAO daoStudent;
    StudentModel studentModel;
    List<StudentModel> list;
    ListView listView_sv;
    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);


        anhsa();
        studentModel = new StudentModel();
        daoStudent = new StudentDAO(this);
        //tra ve danh sach
        list = (new StudentDAO(this)).getall();
        //set adapter
        studentAdapter = new StudentAdapter(this, list);
        studentAdapter.notifyDataSetChanged();
        listView_sv.setAdapter(studentAdapter);
        listView_sv.invalidateViews();



        listView_sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dialog dialog = new Dialog(ActivityManager.this);
                dialog.setContentView(R.layout.activity_student_destell);
                dialog.getWindow().setWindowAnimations(R.style.DialogforAnimation);
                studentModel = (StudentModel) studentAdapter.getItem(i);

                /*anh sa*/
                TextView txt_codestudent = (TextView) dialog.findViewById(R.id.textViewStudentId_detell);
                Spinner Spnclassid = (Spinner) dialog.findViewById(R.id.txt_classid_detell);
                TextView edt_date = (TextView) dialog.findViewById(R.id.edtdate);
                EditText edt_namestudent = (EditText) dialog.findViewById(R.id.edtName_detell);
                Button btn_date = (Button) dialog.findViewById(R.id.btn_chonngaysinh_detell);
                Button btn_delete = (Button) dialog.findViewById(R.id.btn_delete_detell);
                Button btn_update = (Button) dialog.findViewById(R.id.btn_update);

                txt_codestudent.setText(studentModel.getStudentCode());
                edt_namestudent.setText(studentModel.getStudentName());
                edt_date.setText(studentModel.getBirthDay());


                //spinner
                List<ClassModel> classModelslist = (new ClassDAO(ActivityManager.this)).getall();
                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(ActivityManager.this, classModelslist);
                Spnclassid.setAdapter(spinnerAdapter);
                Spnclassid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        select = ((ClassModel) spinnerAdapter.getItem(i)).getClassId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                Spnclassid.setSelection(spinnerAdapter.getPosition(studentModel.getClassId()));

                //chon ngay sinh
                btn_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
                        int thang = calendar.get(Calendar.MONTH);
                        int nam = calendar.get(Calendar.YEAR);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityManager.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        edt_date.setText(dayOfMonth + "-" + month + "-" + year);
                                    }
                                }, nam, thang, ngay);
                        datePickerDialog.show();
                    }
                });
                //delete
                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        //thong BAO
                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(ActivityManager.this);
                        alertDialog1.setTitle("Thông Báo");
                        alertDialog1.setIcon(R.drawable.presentation);
                        alertDialog1.setMessage("Bạn Có Chắc Chắn Muốn Xóa Sinh Viên " + edt_namestudent.getText().toString() + " Hay Không.");
                        alertDialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                StudentDAO studentDAO = new StudentDAO(ActivityManager.this);
                                studentDAO.delete(studentModel.getStudentCode());
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
                        dialog.dismiss();
                    }
                });

                //update
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String _classid = select;
                        String _name = edt_namestudent.getText().toString();
                        String _date = edt_date.getText().toString();
                        StudentDAO studentDAO = new StudentDAO(ActivityManager.this);
                        studentModel.setStudentName(_name);
                        studentModel.setBirthDay(_date);
                        studentModel.setClassId(_classid);
                        studentDAO.update(studentModel);
                        studentAdapter.notifyDataSetChanged();

                        //thong BAO
                        AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(ActivityManager.this);
                        alertDialog1.setTitle("Thông Báo");
                        alertDialog1.setIcon(R.drawable.presentation);
                        alertDialog1.setMessage("Bạn Đã Sửa Thành Công Sinh Viên " + edt_namestudent.getText().toString() + ".");
                        alertDialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog dialog1 = alertDialog1.create();
                        dialog1.show();

                        dialog.dismiss();
                    }
                });
                dialog.show();

            }

        });

        //spinner
        List<ClassModel> classModelslist = (new ClassDAO(this)).getall();
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, classModelslist);
        spn_class.setAdapter(spinnerAdapter);
        spn_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = ((ClassModel) spinnerAdapter.getItem(i)).getClassId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //add
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentname = edt_tenSv.getText().toString().trim();
                String studentcode = edt_masinhvien.getText().toString().trim();
                String birthday = txt_ngaysinh.getText().toString().trim();
                String classid = select;
                Boolean checkStudenCODE = daoStudent.checkStudentCode(studentcode);

                if (edt_tenSv.getText().length() == 0 ||
                        edt_masinhvien.getText().length() == 0) {
                    thongbaochuanhap();
                    //   Toast.makeText(ActivityManager.this, "Chưa Nhập Thông Tin", Toast.LENGTH_SHORT).show();
                    return;

                } else if (checkStudenCODE == false) {
                    thongbaomasinhvien();
                    //  Toast.makeText(ActivityManager.this, "Mã Sinh Viên Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                    return;

                } else if (txt_ngaysinh.getText().length() == 0) {
                    thongbaothemngaysinh();
                    return;
                } else {
                    StudentModel studentModel = new StudentModel(studentcode, studentname, birthday, classid);
                    daoStudent = new StudentDAO(ActivityManager.this);
                    daoStudent.insert(studentModel);
                    studentAdapter = new StudentAdapter(ActivityManager.this, list);
                    studentAdapter.notifyDataSetChanged();
                    listView_sv.setAdapter(studentAdapter);
                    listView_sv.invalidateViews();
                    // listView_sv.invalidateViews();
                    thongbaothanhcong();
                    //Toast.makeText(ActivityManager.this, "Insert Successfull", Toast.LENGTH_SHORT).show();

                    //lam moi edittext
                    edt_tenSv.setText("");
                    edt_masinhvien.setText("");
                    txt_ngaysinh.setText("");
                }
            }
        });


        //chonngaysinh
        btn_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonngaysinh();
            }
        });


    }


    private void anhsa() {
        btn_ngaysinh = findViewById(R.id.btn_chonngaysinh);
        edt_tenSv = findViewById(R.id.textInput_namestudent_qlsv);
        txt_ngaysinh = findViewById(R.id.edt_ngaysinh_activity_qlsv);
        edt_masinhvien = findViewById(R.id.textInput_codestudent_qlsv);
        spn_class = findViewById(R.id.spn_activity_qlsv);
        listView_sv = findViewById(R.id.lv_activity_manager);
        btn_add = findViewById(R.id.btn_add_manager);


    }


    private void chonngaysinh() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt_ngaysinh.setText(dayOfMonth + "-" + month + "-" + year);
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();

    }

    private void thongbaomasinhvien() {
        Dialog dialogmsv = new Dialog(this);
        dialogmsv.setContentView(R.layout.dialog_masinhvientontai);
        Window window = dialogmsv.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.dialogAnimation;
        Button btn_ok = (Button) dialogmsv.findViewById(R.id.btn_ok_masv);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogmsv.dismiss();
            }
        });
        dialogmsv.show();
    }

    private void thongbaochuanhap() {
        Dialog dialogmsv1 = new Dialog(this);
        dialogmsv1.setContentView(R.layout.dialog_chuanhap);
        Window window = dialogmsv1.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.dialogAnimation;
        Button btn_ok = (Button) dialogmsv1.findViewById(R.id.btn_ok_chuanhap);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogmsv1.dismiss();
            }
        });
        dialogmsv1.show();
    }

    private void thongbaothanhcong() {
        Dialog dialogmsv2 = new Dialog(this);
        dialogmsv2.setContentView(R.layout.dialog_thanhcong);
        Window window = dialogmsv2.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.dialogAnimation;
        Button btn_ok = (Button) dialogmsv2.findViewById(R.id.btn_ok_thanhcong);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogmsv2.dismiss();
            }
        });
        dialogmsv2.show();
    }

    private void thongbaothemngaysinh() {
        Dialog dialogmsv3 = new Dialog(this);
        dialogmsv3.setContentView(R.layout.dialog_themngaysinh);
        Window window = dialogmsv3.getWindow();
        window.setGravity(Gravity.CENTER);
        window.getAttributes().windowAnimations = R.style.dialogAnimation;
        Button btn_ok = (Button) dialogmsv3.findViewById(R.id.btn_ok_themmalop);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogmsv3.dismiss();
            }
        });
        dialogmsv3.show();
    }
}