package com.xcodesystemsinterns.studentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/* Notes
1-The ".db" extension in the database name tells android that there is a database.
2-The "execSQL" method executes Whatever SQL Query you pass as argument.
 */

public class DataBaseHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManager.db";
    private static int DATABASE_VERSION = 1;
    //================== Table 1 ======================
    private static final String TABLE1_NAME = "Students";
    private static final String TABLE1_COLUMN1_NAME = "StudentID";
    private static final String TABLE1_COLUMN2_NAME = "Name";
    private static final String TABLE1_COLUMN3_NAME = "Email";
    //================== Table 2 ======================
    private static final String TABLE2_NAME = "Classes";
    private static final String TABLE2_COLUMN1_NAME = "ClassID";
    private static final String TABLE2_COLUMN2_NAME = "Name";
    private static final String TABLE2_COLUMN3_NAME = "Description";
    //================== Table 3 ======================
    private static final String TABLE3_NAME = "Assignments";
    private static final String TABLE3_COLUMN1_NAME = "AssignmentID";
    private static final String TABLE3_COLUMN2_NAME = "Name";
    private static final String TABLE3_COLUMN3_NAME = "DueDate";
    private static final String TABLE3_COLUMN4_NAME = "Description";
    //================== Table 4 ======================
    private static final String TABLE4_NAME = "Exams";
    private static final String TABLE4_COLUMN1_NAME = "ExamID";
    private static final String TABLE4_COLUMN2_NAME = "Name";
    //================== Table 5 ======================
    private static final String TABLE5_NAME = "StudentClassRelation";
    private static final String TABLE5_COLUMN1_NAME = "StudentID";
    private static final String TABLE5_COLUMN2_NAME = "ClassID";
    //================== Table 6 ======================
    private static final String TABLE6_NAME = "ExamClassRelation";
    private static final String TABLE6_COLUMN1_NAME = "ClassID";
    private static final String TABLE6_COLUMN2_NAME = "ExamID";
    //================== Table 7 ======================
    private static final String TABLE7_NAME = "AssignmentClassRelation";
    private static final String TABLE7_COLUMN1_NAME = "ClassID";
    private static final String TABLE7_COLUMN2_NAME = "AssignmentID";
    //================= Table 8 =======================
    private static final String TABLE8_NAME = "AssignmentStudentRelation";
    private static final String TABLE8_COLUMN1_NAME = "StudentID";
    private static final String TABLE8_COLUMN2_NAME = "AssignmentID";
    private static final String TABLE8_COLUMN3_NAME = "StudnetRate";
    //================= Table 9 =======================
    private static final String TABLE9_NAME = "ExamStudentRelation";
    private static final String TABLE9_COLUMN1_NAME = "StudentID";
    private static final String TABLE9_COLUMN2_NAME = "ExamID";
    private static final String TABLE9_COLUMN3_NAME = "StudentGrade";


    //Creating the database takes place here.
    //For sake of Simplicity of calling the Constructor, parameters are reduced by local variables usage.
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating the table takes place here.
    //Whenever the onCreate method is called,
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_Table1 = "CREATE TABLE "+ TABLE1_NAME + " ( "+
                TABLE1_COLUMN1_NAME+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                TABLE1_COLUMN2_NAME+" TEXT NOT NULL, "+
                TABLE1_COLUMN3_NAME+" TEXT NOT NULL);";
        String query_Table2 = "CREATE TABLE "+ TABLE2_NAME + " ( "+
                TABLE2_COLUMN1_NAME+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                TABLE2_COLUMN2_NAME+" TEXT NOT NULL, "+
                TABLE2_COLUMN3_NAME+" TEXT NOT NULL);";
        String query_Table3 = "CREATE TABLE "+ TABLE3_NAME + " ( "+
                TABLE3_COLUMN1_NAME+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                TABLE3_COLUMN2_NAME+" TEXT NOT NULL, "+
                TABLE3_COLUMN3_NAME+" TEXT NOT NULL, "+
                TABLE3_COLUMN4_NAME+" TEXT NOT NULL );";
        String query_Table4 = "CREATE TABLE "+ TABLE4_NAME + " ( "+
                TABLE4_COLUMN1_NAME+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                TABLE4_COLUMN2_NAME+" TEXT NOT NULL );";
        String query_Table5 = "CREATE TABLE "+ TABLE5_NAME + " ( "+
                TABLE5_COLUMN1_NAME+" INTEGER NOT NULL, "+
                TABLE5_COLUMN2_NAME+" INTEGER NOT NULL, "+
                "FOREIGN KEY("+TABLE5_COLUMN1_NAME+") REFERENCES "+TABLE1_NAME+"("+TABLE1_COLUMN1_NAME+"),"+
                "FOREIGN KEY("+TABLE5_COLUMN2_NAME+") REFERENCES "+TABLE2_NAME+"("+TABLE2_COLUMN1_NAME+") );";
        String query_Table6 = "CREATE TABLE "+ TABLE6_NAME + " ( "+
                TABLE6_COLUMN1_NAME+" INTEGER NOT NULL, "+
                TABLE6_COLUMN2_NAME+" INTEGER NOT NULL, "+
                "FOREIGN KEY("+TABLE6_COLUMN2_NAME+") REFERENCES "+TABLE4_NAME+"("+TABLE4_COLUMN1_NAME+"),"+
                "FOREIGN KEY("+TABLE6_COLUMN1_NAME+") REFERENCES "+TABLE2_NAME+"("+TABLE2_COLUMN1_NAME+") );";
        String query_Table7 = "CREATE TABLE "+ TABLE7_NAME + " ( "+
                TABLE7_COLUMN1_NAME+" INTEGER NOT NULL, "+
                TABLE7_COLUMN2_NAME+" INTEGER NOT NULL, "+
                "FOREIGN KEY("+TABLE7_COLUMN1_NAME+") REFERENCES "+TABLE2_NAME+"("+TABLE2_COLUMN1_NAME+"),"+
                "FOREIGN KEY("+TABLE7_COLUMN2_NAME+") REFERENCES "+TABLE3_NAME+"("+TABLE3_COLUMN1_NAME+") );";
        String query_Table8 = "CREATE TABLE "+ TABLE8_NAME + " ( "+
                TABLE8_COLUMN1_NAME+" INTEGER NOT NULL, "+
                TABLE8_COLUMN2_NAME+" INTEGER NOT NULL, "+
                TABLE8_COLUMN3_NAME+" INTEGER NOT NULL, "+
                "FOREIGN KEY("+TABLE8_COLUMN2_NAME+") REFERENCES "+TABLE3_NAME+"("+TABLE3_COLUMN1_NAME+"),"+
                "FOREIGN KEY("+TABLE8_COLUMN1_NAME+") REFERENCES "+TABLE1_NAME+"("+TABLE1_COLUMN1_NAME+") );";
        String query_Table9 = "CREATE TABLE "+ TABLE9_NAME + " ( "+
                TABLE9_COLUMN1_NAME+" INTEGER NOT NULL, "+
                TABLE9_COLUMN2_NAME+" INTEGER NOT NULL, "+
                TABLE9_COLUMN3_NAME+" INTEGER NOT NULL, "+
                "FOREIGN KEY("+TABLE9_COLUMN1_NAME+") REFERENCES "+TABLE1_NAME+"("+TABLE1_COLUMN1_NAME+"),"+
                "FOREIGN KEY("+TABLE9_COLUMN2_NAME+") REFERENCES "+TABLE4_NAME+"("+TABLE4_COLUMN1_NAME+") );";

        db.execSQL(query_Table1);
        db.execSQL(query_Table2);
        db.execSQL(query_Table3);
        db.execSQL(query_Table4);
        db.execSQL(query_Table5);
        db.execSQL(query_Table6);
        db.execSQL(query_Table7);
        db.execSQL(query_Table8);
        db.execSQL(query_Table9);
    }

    //Upgrading the version of the database to a new version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_Table1="DROP TABLE "+TABLE1_NAME;
        String drop_Table2="DROP TABLE "+TABLE1_NAME;
        String drop_Table3="DROP TABLE "+TABLE1_NAME;
        String drop_Table4="DROP TABLE "+TABLE1_NAME;
        String drop_Table5="DROP TABLE "+TABLE1_NAME;
        String drop_Table6="DROP TABLE "+TABLE1_NAME;
        String drop_Table7="DROP TABLE "+TABLE1_NAME;
        String drop_Table8="DROP TABLE "+TABLE1_NAME;
        String drop_Table9="DROP TABLE "+TABLE1_NAME;

        db.execSQL(drop_Table1);
        db.execSQL(drop_Table2);
        db.execSQL(drop_Table3);
        db.execSQL(drop_Table4);
        db.execSQL(drop_Table5);
        db.execSQL(drop_Table6);
        db.execSQL(drop_Table7);
        db.execSQL(drop_Table8);
        db.execSQL(drop_Table9);

        //Re-create the Tables of the database for the new version.
        onCreate(db);

    }
    public Cursor getUndoneAssignmentList(int ClassID,int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE1_COLUMN1_NAME+","+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+" join "+TABLE5_NAME+" join "+TABLE7_NAME+" join "+TABLE3_NAME+"" +
                " where "+TABLE5_COLUMN2_NAME+"= '"+ClassID+"' and "+TABLE3_COLUMN1_NAME+" ='"+AssignmentID+"' and "+TABLE5_COLUMN2_NAME+"="+TABLE7_COLUMN1_NAME +" and "+TABLE7_COLUMN2_NAME+" = "+TABLE3_COLUMN1_NAME+" and "+TABLE1_COLUMN1_NAME+" = "+TABLE5_COLUMN1_NAME+"" +
                " except select Distinct "+TABLE1_COLUMN1_NAME+","+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+" join "+TABLE5_NAME+" join "+TABLE8_NAME+" join "+TABLE3_NAME+"" +
                " where "+TABLE5_COLUMN2_NAME+"= '"+ClassID+"'and "+TABLE3_COLUMN1_NAME+" ='"+AssignmentID+"' and "+TABLE8_COLUMN1_NAME+" ="+TABLE5_COLUMN1_NAME+" and "+TABLE8_COLUMN2_NAME+" = "+TABLE3_COLUMN1_NAME+" and "+TABLE1_COLUMN1_NAME+" = "+TABLE5_COLUMN1_NAME+"",null);
    }
    public Cursor getDoneAssignmentList(int ClassID,int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery(" select Distinct "+TABLE1_COLUMN1_NAME+","+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+" join "+TABLE5_NAME+" join "+TABLE8_NAME+" join "+TABLE3_NAME+"" +
                " where "+TABLE5_COLUMN2_NAME+"= '"+ClassID+"'and "+TABLE3_COLUMN1_NAME+" ='"+AssignmentID+"' and "+TABLE8_COLUMN1_NAME+" ="+TABLE5_COLUMN1_NAME+" and "+TABLE8_COLUMN2_NAME+" = "+TABLE3_COLUMN1_NAME+" and "+TABLE1_COLUMN1_NAME+" = "+TABLE5_COLUMN1_NAME+"",null);
    }
    public Cursor getClassList(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE2_COLUMN1_NAME+", "+TABLE2_COLUMN2_NAME+" from "+TABLE2_NAME+"" , null);
    }
/*
    public Cursor getAllStudentsByName(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select distinct Name from "+TABLE_NAME,null);
    }

    public Cursor getClassList(String studentName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Class from "+TABLE_NAME+" where Name= '"+studentName+"'",null);
    }

    //Inserting Data into the table takes place here.
    //According to the Columns values, the parameters changes.
    public boolean onInsert(String Name,String Email,String Class){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //First argument is Column name , Second argument is the value we are passing.

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result== -1) return false;
        else return true;
    }

    //A Cursor Class provides random read-write accesses to Result sets.
    //To check if the Cursor returned values ---->
    // if(Cursor.getcount() == 0) no results
    // else create a StringBuffer and append the results found to it by looping on the Cursor ( moveToNext() , getString() )
    //A method to return all data from a certain table.
    //A similar method can be applied to return certain data from certain table by changing the query statement.
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);

    }

    public ArrayList<String> toArrayList(Cursor cursor){
        ArrayList<String> arrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(0));
        }

        return arrayList;
    }*/

}
