package com.example.asm_ps10375_nguyenquangthuan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ps10375_nguyenquangthuan.DataBase.DBHelper;
import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    DBHelper myDbHelper;

    public StudentDAO(Context context) {
        myDbHelper = new DBHelper(context);
    }

    @Override
    public List<StudentModel> getall() {
        List<StudentModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM STUDENT", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String StudentCode = cursor.getString(0);
            String StudentName = cursor.getString(1);
            String BirthDay = cursor.getString(2);
            String ClassId = cursor.getString(3);
            StudentModel studentModel = new StudentModel(StudentCode, StudentName, BirthDay, ClassId);
            list.add(studentModel);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    @Override
    public StudentModel getall(String ClassId) {

        StudentModel studentModel = null;
        SQLiteDatabase database = myDbHelper.getReadableDatabase();
        Cursor cursor = database
                .rawQuery("SELECT * FROM STUDENT WHERE ClassId = ?", new String[]{ClassId});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String StudentCode = cursor.getString(0);
            String StudentName = cursor.getString(1);
            String BirthDay = cursor.getString(2);
            String ClassIds = cursor.getString(3);
            studentModel = new StudentModel(StudentCode, StudentName, BirthDay, ClassIds);
            cursor.moveToNext();
        }
        cursor.close();
        return studentModel;
    }

    @Override
    public void insert(StudentModel studentModel) {

        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("StudentCode", studentModel.getStudentCode());
        values.put("StudentName", studentModel.getStudentName());
        values.put("BirthDay", studentModel.getBirthDay());
        values.put("ClassId", studentModel.getClassId());
        //Neu de null thi khi value bang null thi loi
        db.insert("STUDENT",null,values);
     //   db.close();

    }

    @Override
    public void update(StudentModel studentModel) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String[]  params = new String[]{studentModel.getStudentCode()};
        values.put("StudentName", studentModel.getStudentName());
        values.put("BirthDay", studentModel.getBirthDay());
        values.put("ClassId", studentModel.getClassId());
        //Neu de null thi khi value bang null thi loi
        db.update("STUDENT",values,"StudentCode = ?",params );

    }

    @Override
    public void delete(String StudentCode) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        String[]  params = new String[]{StudentCode};
        db.delete("STUDENT","StudentCode =?",params);
    }


    //check trung ma sinh vien
    @Override
    public Boolean checkStudentCode(String StudentCODE) {
        SQLiteDatabase db;
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from STUDENT where StudentCode=?", new String[]{StudentCODE});
        if (cursor.getCount() > 0) return false;
        else return true;
    }
}
