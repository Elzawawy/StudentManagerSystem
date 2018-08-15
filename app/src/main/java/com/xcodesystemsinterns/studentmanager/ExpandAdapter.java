package com.xcodesystemsinterns.studentmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context mcontext;
    List<StudentDescription> done;
    List<StudentDescription> undone;

    public ExpandAdapter(Context mcontext, List<StudentDescription> done, List<StudentDescription> undone) {
        this.mcontext = mcontext;
        this.done = done;
        this.undone = undone;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int i) {
        return i==0 ? done.size(): undone.size();
    }

    @Override
    public Object getGroup(int i) {
        return i==0? "\t\tDone Students:" : "\t\tUndone Students:";
    }

    @Override
    public Object getChild(int i, int i1) {
        return i==0?  done.get(i1): undone.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
       String title=(String) getGroup(i);
        if(view==null){
            LayoutInflater li=(LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=li.inflate(R.layout.list_group,null);

        }
        TextView ListGroupItem=(TextView) view.findViewById(R.id.ListGroupItem);
        ListGroupItem.setText(title);
        return  view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View v, ViewGroup viewGroup) {
        StudentDescription text=(StudentDescription) getChild(i,i1);
       // v.setTransitionName();
        if(v==null){
            LayoutInflater li=(LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.single_student_details,null);
        }
        TextView name = (TextView) v.findViewById(R.id.StudentName);
        TextView id = (TextView) v.findViewById(R.id.StudentID);

        TextView grade = (TextView) v.findViewById(R.id.StudentGrade);
        name.setText(text.getName());
        id.setText(""+text.getId());
        if(text.getGrade()!=0)
        grade.setText(text.getGrade()+"/5");
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
