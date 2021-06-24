package com.example.asm_ps10375_nguyenquangthuan.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final String DBNAME = "QLSV";

    public DBHelper(Context context) {
        super(context, "QLSV", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //tao bang Class
        String sql = "CREATE TABLE CLASS(ClassId Text primary key not null,ClassName Text);";
        sqLiteDatabase.execSQL(sql);

        //tao bang user
        sql = "CREATE TABLE USERS(User Text primary key not null,Password Text);";
        sqLiteDatabase.execSQL(sql);
        //tao bang Student
        sql = "CREATE TABLE STUDENT(StudentCode Text primary key not null," +
                "StudentName Text," +
                "BirthDay Text," +
                "ClassId Text not null," +
                "foreign key (ClassId) references CLASS(ClassId) )";
        sqLiteDatabase.execSQL(sql);


        sqLiteDatabase.execSQL("INSERt INTO CLASS VALUES('LT14301','CNTT')");
        sqLiteDatabase.execSQL("INSERt INTO CLASS VALUES('DL13452','Du Lịch')");
        sqLiteDatabase.execSQL("INSERt INTO CLASS VALUES('KT01826','Kinh Tế')");
        sqLiteDatabase.execSQL("INSERt INTO CLASS VALUES('NN99372','Ngoại Ngữ')");
        sqLiteDatabase.execSQL("INSERt INTO CLASS VALUES('NT09916','Ngoại Thương')");


        sqLiteDatabase.execSQL("INSERt INTO STUDENT VALUES('Ps01222','Tô Thị Lan Anh', '7/8/2000','LT14301')");
        sqLiteDatabase.execSQL("INSERt INTO STUDENT VALUES('Ps18822','Nguyễn Thị Kim Dung', '7/7/2000','DL13452')");
        sqLiteDatabase.execSQL("INSERt INTO STUDENT VALUES('Ps01772','Cầm Thị Huỳnh Như', '5/12/2000','KT01826')");
        sqLiteDatabase.execSQL("INSERt INTO STUDENT VALUES('Ps01229','Lê Thị Kim Quyên', '13/9/2000','NN99372')");
        sqLiteDatabase.execSQL("INSERt INTO STUDENT VALUES('Ps10229','Lê Ngọc Thiện', '8/7/2000','NT09916')");


    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLASS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String User, String Password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User", User);
        contentValues.put("Password", Password);
        long mresult = myDB.insert("USERS", null, contentValues);
        if (mresult == -1) {
            return false;

        } else {
            return true;
        }
    }

    public Boolean checkUser(String User) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select* from USERS where User =?", new String[]{User});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUserPassword(String User, String Password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from USERS where User =? and Password=?", new String[]{User, Password});
        if (cursor.getCount() > 0) {
            return true;

        } else {
            return false;
        }
    }
}
