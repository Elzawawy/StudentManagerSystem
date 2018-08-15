package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context mcontext;
    List<Assignment> myList;

    public MyAdapter(Context mcontext, List<Assignment> myList) {
        this.mcontext = mcontext;
        this.myList = myList;
    }
    public int getSelectedAssignmentID(int position){
        return myList.get(position).getID();
    }
    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int i) {
        return myList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= View.inflate(mcontext,R.layout.activity_list,null);
        TextView AssignmentName = (TextView) v.findViewById(R.id.AssignmentName);
        TextView ClassName = (TextView) v.findViewById(R.id.ClassName);
        TextView DueDate = (TextView) v.findViewById(R.id.DueDate);
        AssignmentName.setText(myList.get(i).getName());
        ClassName.setText(myList.get(i).getClassName());
        DueDate.setText(myList.get(i).getDate());
      //  v.setTag(myList.get(i).get());
        return v;
    }
}
