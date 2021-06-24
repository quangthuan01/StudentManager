package com.example.asm_ps10375_nguyenquangthuan.Model;

public class ClassModel {
    private String ClassId;
    private String ClassName;
    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public ClassModel(String classId, String className) {
        ClassId = classId;
        ClassName = className;
    }

    public ClassModel() {
    }
}
