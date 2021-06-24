package com.example.asm_ps10375_nguyenquangthuan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm_ps10375_nguyenquangthuan.Model.ClassModel;
import com.example.asm_ps10375_nguyenquangthuan.R;

import java.util.List;

public class ClassAdapter extends BaseAdapter {
    private List<ClassModel> data;
    private Context mcontext;

    public ClassAdapter(Context context , List<ClassModel> _data){
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
            convertView = View.inflate(viewGroup.getContext(), R.layout.item_list, null);

        }
        ClassModel classModel = (ClassModel) getItem(i);

        TextView tenLop = ((TextView) convertView.findViewById(R.id.tv_tenlop_item_list));
        TextView maLop = ((TextView) convertView.findViewById(R.id.tv_malop_item_list));

        tenLop.setText(classModel.getClassName());
        maLop.setText(classModel.getClassId());

        return convertView;
    }
}
