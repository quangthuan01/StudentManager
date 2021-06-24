package com.example.asm_ps10375_nguyenquangthuan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_ps10375_nguyenquangthuan.DataBase.DBHelper;
import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements IClassDAO {

    DBHelper myDbHelper;

    public ClassDAO(Context context) {
        myDbHelper = new DBHelper(context);
    }

    @Override
    public List<ClassModel> getall() {
        List<ClassModel> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CLASS", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String ClassId = cursor.getString(0);
            String ClassName = cursor.getString(1);
            ClassModel classModel = new ClassModel(ClassId, ClassName);
            list.add(classModel);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

//    @Override
//    public ClassModel getall(String ClassId) {
//        ClassModel classModel = null;
//        SQLiteDatabase database = myDbHelper.getReadableDatabase();
//        Cursor cursor = database
//                .rawQuery("SELECT * FROM CLASS WHERE ClassId = ?", new String[]{ClassId});
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            String id = cursor.getString(0);
//            String name = cursor.getString(1);
//
//            classModel = new ClassModel(id, name);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return classModel;
//    }

    @Override
    public void insert(ClassModel classModel) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("ClassId", classModel.getClassId());
        values.put("ClassName", classModel.getClassName());
        //Neu de null thi khi value bang null thi loi
        db.insert("CLASS", null, values);
        //  db.close();

    }

    @Override
    public void update(ClassModel classModel) {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        String[]  params = new String[]{classModel.getClassId()};
        values.put("ClassId", classModel.getClassId());
        values.put("ClassName", classModel.getClassName());
        //Neu de null thi khi value bang null thi loi
        db.update("CLASS",values,"ClassID = ?",params );

    }

    @Override
    public void delete(String ClassId) {
        SQLiteDatabase database = myDbHelper.getReadableDatabase();
        String[] params = new String[]{ClassId};
        database.delete("CLASS", "ClassId = ?", params);
    }


    //check Trung ma lop
    @Override
    public Boolean checkClassID(String ClassID) {
        SQLiteDatabase db;
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from CLASS where ClassId=?", new String[]{ClassID});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

}
