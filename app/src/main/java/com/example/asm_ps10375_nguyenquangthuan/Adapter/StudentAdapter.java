package com.example.asm_ps10375_nguyenquangthuan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_ps10375_nguyenquangthuan.Model.StudentModel;
import com.example.asm_ps10375_nguyenquangthuan.R;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<StudentModel> data;
    private Context mcontext;

    public StudentAdapter(Context context, List<StudentModel> _data) {
        mcontext = context;
        data = _data;

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = view;
        if (view == null) {
            convertView = View.inflate(viewGroup.getContext(), R.layout.item_student_list, null);

        }
        StudentModel studentModel = (StudentModel) getItem(i);

        TextView tensinhvien = ((TextView) convertView.findViewById(R.id.txt_tensinhvien_item_student_list));
        TextView masinhvien = ((TextView) convertView.findViewById(R.id.txt_masinhvien_item_student_list));
        TextView ngaysinh = ((TextView) convertView.findViewById(R.id.txt_ngaysinh_item_student_list));
        TextView malop = ((TextView) convertView.findViewById(R.id.txt_malop_item_student_list));

        tensinhvien.setText(studentModel.getStudentName());
        masinhvien.setText(studentModel.getStudentCode());
        ngaysinh.setText(studentModel.getBirthDay());
        malop.setText(studentModel.getClassId());

        return convertView;
    }
}
