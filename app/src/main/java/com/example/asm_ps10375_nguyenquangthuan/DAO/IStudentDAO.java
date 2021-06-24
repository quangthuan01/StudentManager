package com.example.asm_ps10375_nguyenquangthuan.DAO;

import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;

import java.util.List;

public interface IStudentDAO {
    //4 ham
    List<StudentModel> getall();
    StudentModel getall(String ClassId);
    void  insert(StudentModel studentModel);
    void   update(StudentModel studentModel);
    void  delete(String  StudentCode);
    Boolean checkStudentCode(String StudentCODE);
}
