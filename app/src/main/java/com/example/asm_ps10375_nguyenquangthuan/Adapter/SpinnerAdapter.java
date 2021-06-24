package com.example.asm_ps10375_nguyenquangthuan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.R;

import java.util.List;


public class SpinnerAdapter  extends BaseAdapter {

    private  Context context;
    private List<ClassModel> data;

    public  SpinnerAdapter (Context mcontext, List<ClassModel> _data){

        context = mcontext;
        data= _data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }


    public  int getPosition(String classID) {
        int index  = -1;
        for (int i  = 0; i<data.size(); i++){
            ClassModel classModel = data.get(i);
            if (classModel.getClassId().equals(classID)){
                index = i;
                break;
            }
        }
                return index;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = view;
        if (view == null) {
            convertView = View.inflate(viewGroup.getContext(), R.layout.spinner_1row, null);

        }

        TextView spn_id = ((TextView) convertView.findViewById(R.id.txt_idCLass));
        ClassModel classModel = (ClassModel) getItem(i);
        spn_id.setText(classModel.getClassId());
        return convertView;
    }
}
