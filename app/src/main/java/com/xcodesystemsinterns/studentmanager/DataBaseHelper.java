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
3-A Cursor Class provides random read-write accesses to Result sets.
 -To check if the Cursor returned values ---->if(Cursor.getcount() == 0) no results
 -else create a StringBuffer and append the results found to it by looping on the Cursor ( moveToNext() , getString() )

4-In ContentValues.put method ---> First argument is Column name , Second argument is the value we are passing.
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
    //Should not be used method.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop Old version Tables.
        String drop_query1="DROP TABLE "+TABLE1_NAME;
        String drop_query2="DROP TABLE "+TABLE2_NAME;
        String drop_query3="DROP TABLE "+TABLE3_NAME;
        String drop_query4="DROP TABLE "+TABLE4_NAME;
        String drop_query5="DROP TABLE "+TABLE5_NAME;
        String drop_query6="DROP TABLE "+TABLE6_NAME;
        String drop_query7="DROP TABLE "+TABLE7_NAME;
        String drop_query8="DROP TABLE "+TABLE8_NAME;
        String drop_query9="DROP TABLE "+TABLE9_NAME;

        db.execSQL(drop_query1);
        db.execSQL(drop_query2);
        db.execSQL(drop_query3);
        db.execSQL(drop_query4);
        db.execSQL(drop_query5);
        db.execSQL(drop_query6);
        db.execSQL(drop_query7);
        db.execSQL(drop_query8);
        db.execSQL(drop_query9);

        //Re-create the Tables of the database for the new version.
        onCreate(db);

    }

    //To add an assignment to a specific class.
    //Returns Assignment ID that has been created and -1 if the creation has failed.
    public int addAssignment(String name,String duedate, String description, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //First Insert into the table of assignment.
        contentValues.put(TABLE3_COLUMN2_NAME,name);
        contentValues.put(TABLE3_COLUMN3_NAME,duedate);
        contentValues.put(TABLE3_COLUMN4_NAME,description);
        long result = sqLiteDatabase.insert(TABLE3_NAME, null, contentValues);
        if(result== -1) return -1;
        else {
            //If the insertion correctly took place ---> Insert into assignmentClassRelation
            //Get AssignmentID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE3_COLUMN1_NAME + " from " + TABLE3_NAME + " order by " + TABLE3_COLUMN1_NAME + " DESC LIMIT 1", null);
            contentValues.clear();
            contentValues.put(TABLE7_COLUMN1_NAME,classID);
            contentValues.put(TABLE7_COLUMN2_NAME,cursor.getInt(0));
            result = sqLiteDatabase.insert(TABLE7_NAME, null, contentValues);
            //if new result value doesn't equal -1  ----> Successful Insertion in both Tables ----> return the AssignmentID.
            if(result != -1) return cursor.getInt(0);
            //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
            else return -1;
        }
    }

    //To add an exam to a specific class.
    //Returns Exam ID that has been created and -1 if the creation has failed.
    public int addExam(String name,int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //First Insert into the table of exams.
        contentValues.put(TABLE4_COLUMN2_NAME,name);
        long result = sqLiteDatabase.insert(TABLE4_NAME, null, contentValues);
        if(result== -1) return -1;
        else {
            //If the insertion correctly took place ---> Insert into ExamClassRelation
            //Get ExamID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE4_COLUMN1_NAME + " from " + TABLE4_NAME + " order by " + TABLE4_COLUMN1_NAME + " DESC LIMIT 1", null);
            //Re-use of instance.
            contentValues.clear();
            contentValues.put(TABLE6_COLUMN1_NAME,classID);
            contentValues.put(TABLE6_COLUMN2_NAME,cursor.getInt(0));
            result = sqLiteDatabase.insert(TABLE6_NAME, null, contentValues);
            //if new result value doesn't equal -1  ----> Successful Insertion in both Tables ----> return the ExamID.
            if(result != -1) return cursor.getInt(0);
            //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
            else return -1;

        }
    }

    //Adding a Class with name and description ONLY.
    //Returns the class ID created or -1 if the creation failed.
    public int addClass(String name,String description){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE2_COLUMN2_NAME,name);
        contentValues.put(TABLE2_COLUMN3_NAME,description);
        long result = sqLiteDatabase.insert(TABLE2_NAME, null, contentValues);
        //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
        if(result == -1) return -1;
        //if result value doesn't equal -1  ----> Successful Insertion in Table ----> return the ClassID.
        else {
            //Get ClassID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE2_COLUMN1_NAME + " from " + TABLE2_NAME + " order by " + TABLE2_COLUMN1_NAME + " DESC LIMIT 1", null);
            return cursor.getInt(0);
        }

    }

    //Adding a Class with name and email ONLY.
    //Returns the student ID created or -1 if the creation failed.
    public int addStudent(String name, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN2_NAME,name);
        contentValues.put(TABLE1_COLUMN3_NAME,email);
        long result = sqLiteDatabase.insert(TABLE1_NAME, null, contentValues);
        //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
        if(result == -1) return -1;
        //if result value doesn't equal -1  ----> Successful Insertion in Table ----> return the StudentID.
        else {
            //Get StudentID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE1_COLUMN1_NAME + " from " + TABLE1_NAME + " order by " + TABLE1_COLUMN1_NAME + " DESC LIMIT 1", null);
            return cursor.getInt(0);
        }
    }

    //In this method, we only need to remove a student from class,
    // Returns -1 if failed and nothing specific to be returned in case of success ( not -1 )
    public boolean removeStudentFromClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE5_NAME,TABLE5_COLUMN1_NAME+" = "+studentID+" and "+TABLE5_COLUMN2_NAME+" = "+classID,null)>0;
    }

    //In this method, we only need to add a student to class,
    // Returns -1 if failed and nothing specific to be returned in case of success ( not -1 )
    public boolean addStudentToClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE5_COLUMN1_NAME,studentID);
        contentValues.put(TABLE5_COLUMN2_NAME,classID);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.insert(TABLE5_NAME,null, contentValues)>0;
    }

    //This method returns a student list that is enrolled in a class given by the classID parameter.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name
    public Cursor getStudentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_COLUMN2_NAME+
                " from "+TABLE1_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+classID
                +" and "+ TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);

    }

    //This method returns a assignment list that is assigned to a class given by the classID parameter.
    //It return a Cursor Object of 3 columns ---> First Column is ID and Second Column is Name and the third column is DueDate
    public Cursor getAssignmentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+","+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+
                " from "+TABLE3_NAME+" join "+TABLE7_NAME+" on "+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME+"="+classID+
                " and "+ TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a exam list that is done by a class given by the classID parameter.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name
    public Cursor getExamsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+","+TABLE4_COLUMN2_NAME+
                " from "+TABLE4_NAME+" join "+TABLE6_NAME+" on "+TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+"="+classID+
                " and "+ TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns 2 columns and only 1 row in Cursor --> First is Name of Course , Second is the description.
    public Cursor getClassInfo(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString ="select "+TABLE2_COLUMN2_NAME+","+TABLE2_COLUMN3_NAME+
                " from "+TABLE2_NAME+" where "+TABLE2_COLUMN1_NAME+"="+classID;

        return sqLiteDatabase.rawQuery(queryString,null);

    }

    //This method returns a class list that is a student (given by the studentID parameter) is enrolled in.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name
    public Cursor getClassesByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+","+TABLE2_COLUMN2_NAME+
                " from "+TABLE2_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a assignment list that is assigned to and done by a student given by the studentID parameter.
    //It return a Cursor Object of 4 columns ---> First Column is ID and Second Column is Name and the third column is DueDate and the fourth is the student rate.
    public Cursor getAssignmentsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+","+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE8_COLUMN3_NAME+
                " from "+TABLE3_NAME+" join "+TABLE8_NAME+" on "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a exam list that is assigned to and done by a student given by the studentID parameter.
    //It return a Cursor Object of 3 columns ---> First Column is ID and Second Column is Name and the third column is the student grade.
    public Cursor getExamsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+","+TABLE4_COLUMN2_NAME+","+TABLE9_COLUMN3_NAME+
                " from "+TABLE4_NAME+" join "+TABLE9_NAME+" on "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //returns a Cursor of a specific student data's email and name.
    public Cursor getStudentInfo(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString ="select "+TABLE1_COLUMN2_NAME+","+TABLE1_COLUMN3_NAME+
                " from "+TABLE1_NAME+" where "+TABLE1_COLUMN1_NAME+"="+studentID;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //returns a Cursor of all student records and 3 columns ---> ID, Name,Email
    public Cursor getAllStudents(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query_String = "select * from "+TABLE1_NAME;

        return sqLiteDatabase.rawQuery(query_String,null);

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

    public Cursor getStudentClassList(int StudentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE2_COLUMN1_NAME+", "+TABLE2_COLUMN2_NAME+" from "+TABLE2_NAME+" join "+TABLE1_NAME+" join "+TABLE5_NAME+"" +
                " where "+TABLE5_COLUMN1_NAME+"= "+TABLE1_COLUMN1_NAME+" and "+TABLE2_COLUMN1_NAME+" = "+TABLE5_COLUMN2_NAME+" and "+TABLE1_COLUMN1_NAME+"= '"+StudentID+"'", null);
    }
    //use this function when you want to retrive info about an addigment for a specific class
    // note that you should also use get DoneAssigment and UndoneAssigment function to get all required info on each assigment
    public Cursor getAssigmentInfo(int ClassID,int AssignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select "+TABLE3_COLUMN1_NAME+","+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE3_COLUMN4_NAME+"" +
                " from "+TABLE3_NAME+" join "+TABLE2_NAME+" join "+TABLE7_NAME+"" +
                " where "+TABLE3_COLUMN1_NAME+" = "+TABLE7_COLUMN2_NAME+"" +
                " and "+TABLE2_COLUMN1_NAME+" = "+TABLE7_COLUMN1_NAME+"" +
                " and "+TABLE2_COLUMN1_NAME+" = '"+ClassID+"' " +
                " and "+TABLE7_COLUMN2_NAME+" ='"+AssignmentID+"'  ", null);
    }





}
