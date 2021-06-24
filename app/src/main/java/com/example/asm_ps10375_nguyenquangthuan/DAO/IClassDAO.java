package com.example.asm_ps10375_nguyenquangthuan.DAO;

import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;

import java.util.List;

public interface IClassDAO {
    //1 ham
    List<ClassModel> getall();
    void  insert(ClassModel classModel);
    void   update(ClassModel classModel);
    void  delete(String  ClassId);
    Boolean checkClassID(String ClassID);

}
