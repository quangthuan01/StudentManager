package com.example.asm_ps10375_nguyenquangthuan.Model;

public class StudentModel {
    private String StudentCode;
    private String StudentName;
    private String BirthDay;
    private String ClassId;

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public StudentModel(String studentCode, String studentName, String birthDay, String classId) {
        StudentCode = studentCode;
        StudentName = studentName;
        BirthDay = birthDay;
        ClassId = classId;
    }

    public StudentModel() {
    }
}
