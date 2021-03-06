package com.xcodesystemsinterns.studentmanager.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xcodesystemsinterns.studentmanager.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context mcontext;
    Cursor doneCursor;
    Cursor undoneCursor;
    String viewType;

    public ExpandAdapter(Context mcontext, Cursor doneCursor, Cursor undoneCursor,String viewType) {
        this.mcontext = mcontext;
        this.doneCursor = doneCursor;
        this.undoneCursor = undoneCursor;
        this.viewType=viewType;
    }
    //List<StudentDescription> done;
    // List<StudentDescription> undone;


    /*
        public ExpandAdapter(Context mcontext, List<StudentDescription> done, List<StudentDescription> undone) {
            this.mcontext = mcontext;
            this.done = done;
            this.undone = undone;
        }
    */
    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int i) {
        return i==0 ? doneCursor.getCount(): undoneCursor.getCount();
    }

    @Override
    public Object getGroup(int i) {
        return i==0? "\t\tDone Students:" : "\t\tUndone Students:";
    }

    @Override
    public Object getChild(int i, int i1) {
        if(i==0){
            doneCursor.moveToPosition(i1);
            return doneCursor;
        }
        undoneCursor.moveToPosition(i1);
        return  undoneCursor;
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
        Cursor c=(Cursor) getChild(i,i1);
        // v.setTransitionName();
        if(v==null){
            LayoutInflater li=(LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=li.inflate(R.layout.single_student_details,null);
        }
        TextView name = (TextView) v.findViewById(R.id.StudentName);
        TextView grade = (TextView) v.findViewById(R.id.StudentGrade);

        name.setText(c.getString(1));
        //If it is a done assignment show grade , other wise don't show but only the name.
        if(c.getColumnCount()==3) {
            if(viewType.equals("assignment"))
            grade.setText("" + c.getInt(2)+"/5");
            else
                grade.setText("" + c.getString(2));
        }
        else
            grade.setText("");
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
