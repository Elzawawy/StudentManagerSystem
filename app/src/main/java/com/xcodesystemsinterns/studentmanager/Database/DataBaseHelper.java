package com.xcodesystemsinterns.studentmanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* Notes
1-The ".db" extension in the database name tells android that there is a database.
2-The "execSQL" method executes Whatever SQL Query you pass as argument.
3-A Cursor Class provides random read-write accesses to Result sets.
 -To check if the Cursor returned values ---->if(Cursor.getcount() == 0) no results
 -else create a StringBuffer and append the results found to it by looping on the Cursor ( moveToNext() , getString() )

4-In ContentValues.put method ---> First argument is Column name , Second argument is the value we are passing.
5- Row id column is not important for UI use but there must be a column named _id in cursor returned in order for CursorAdapter to work correctly.
 */

public class DataBaseHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManager.db";
    private static int DATABASE_VERSION = 6;
    //================== Table 1 ======================
    private static final String TABLE1_NAME = "Students";
    private static final String TABLE1_COLUMN1_NAME = "StudentID";
    private static final String TABLE1_COLUMN2_NAME = "Name";
    private static final String TABLE1_COLUMN3_NAME = "Email";
    private static final String TABLE1_COLUMN4_NAME = "PhoneNumber";
    private static final String TABLE1_COLUMN5_NAME = "Address";
    private static final String TABLE1_COLUMN6_NAME = "Note";
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
    private static final String TABLE4_COLUMN3_NAME = "Date";
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
    //================= Public Constants ==============
    public static final String ID_COLUMN = "_id";
    public static final String NAME_COLUMN = "Name";
    public static final String DESCRIPTION_COLUMN = "Description";
    public static final String DATE_COLUMN = "Date";
    public static final String DUEDATE_COLUMN ="DueDate";
    public static final String EMAIL_COLUMN = "Email";
    public static final String GRADE_COLUMN = "Grade";
    public static final String NOTE_COLUMN = "Note";
    public static final String ADDRESS_COLUMN = "Address";
    public static final String PHONE_COLUMN = "PhoneNumber";
    public static final String CLASS_NAME_COLUMN = "Class";



    //Creating the database takes place here.
    //For sake of Simplicity of calling the Constructor, parameters are reduced by local variables usage.
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //================= Tables Creation on start and on each time upgraded ==============
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_Table1 = "CREATE TABLE "+ TABLE1_NAME + " ( "+
                TABLE1_COLUMN1_NAME+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
                TABLE1_COLUMN2_NAME+" TEXT NOT NULL, "+
                TABLE1_COLUMN3_NAME+" TEXT NOT NULL, "+
                TABLE1_COLUMN4_NAME+" TEXT NOT NULL, " +
                TABLE1_COLUMN5_NAME+" TEXT NOT NULL," +
                TABLE1_COLUMN6_NAME+" TEXT );";
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
                TABLE4_COLUMN2_NAME+" TEXT NOT NULL, "+
                TABLE4_COLUMN3_NAME+" TEXT NOT NULL );";
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
                TABLE9_COLUMN3_NAME+" TEXT NOT NULL, "+
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

    //================= Tables Deletion and Re-creating in case of new version ==============
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

    //================= Insertion Methods ==============

    //Method 1 : To add an assignment to a specific class.
    //Inputs : Assignment Details and ID of the specific class.
    //Outputs : Assignment ID that has been created and -1 if the creation has failed.
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
            cursor.moveToFirst();
            int assignmentID = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE3_COLUMN1_NAME));
            //Insert into AssignmentClassRelation with assignmentID just found and classID input parameter.
            contentValues.clear();
            contentValues.put(TABLE7_COLUMN1_NAME,classID);
            contentValues.put(TABLE7_COLUMN2_NAME,assignmentID);
            result = sqLiteDatabase.insert(TABLE7_NAME, null, contentValues);
            //if new result value doesn't equal -1  ----> Successful Insertion in both Tables ----> return the AssignmentID.
            if(result != -1) return assignmentID;
                //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
            else return -1;
        }
    }

    //Method 2 : To add an exam to a specific class.
    //Inputs : Exam Details and ID of the specific class.
    //Outputs :  Exam ID that has been created and -1 if the creation has failed.
    public int addExam(String name,String date,int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //First Insert into the table of exams.
        contentValues.put(TABLE4_COLUMN2_NAME,name);
        contentValues.put(TABLE4_COLUMN3_NAME,date);
        long result = sqLiteDatabase.insert(TABLE4_NAME, null, contentValues);
        if(result== -1) return -1;
        else {
            //If the insertion correctly took place ---> Insert into ExamClassRelation
            //Get ExamID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE4_COLUMN1_NAME + " from " + TABLE4_NAME + " order by " + TABLE4_COLUMN1_NAME + " DESC LIMIT 1", null);
            cursor.moveToFirst();
            int examID = cursor.getInt(cursor.getColumnIndexOrThrow(TABLE4_COLUMN1_NAME));
            //Re-use of instance.
            contentValues.clear();
            //Insert into ExamClassRelation with examID just found and classID input parameter.
            contentValues.put(TABLE6_COLUMN1_NAME,classID);
            contentValues.put(TABLE6_COLUMN2_NAME,examID);
            result = sqLiteDatabase.insert(TABLE6_NAME, null, contentValues);
            //if new result value doesn't equal -1  ----> Successful Insertion in both Tables ----> return the ExamID.
            if(result != -1) return examID;
                //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
            else return -1;

        }
    }

    //Method 3 : To add a class to system.
    //Inputs : Class Details.
    //Outputs : Class ID that has been created or -1 if the creation failed.
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
            //adjust cursor to first position.
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndexOrThrow(TABLE2_COLUMN1_NAME));
        }

    }

    //Method 4 : To add a student to system.
    //Inputs : Students Details.
    //Outputs : Student ID that has been created or -1 if the creation failed.
    public int addStudent(String name, String email,String phoneNumber , String address){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN2_NAME,name);
        contentValues.put(TABLE1_COLUMN3_NAME,email);
        contentValues.put(TABLE1_COLUMN4_NAME,phoneNumber);
        contentValues.put(TABLE1_COLUMN5_NAME,address);
        long result = sqLiteDatabase.insert(TABLE1_NAME, null, contentValues);
        //if result value equals -1  ----> Failed Insertion in Table ----> return -1 to indicated failure
        if(result == -1) return -1;
            //if result value doesn't equal -1  ----> Successful Insertion in Table ----> return the StudentID.
        else {
            //Get StudentID of the record you just entered.
            Cursor cursor = sqLiteDatabase.rawQuery("select " + TABLE1_COLUMN1_NAME + " from " + TABLE1_NAME + " order by " + TABLE1_COLUMN1_NAME + " DESC LIMIT 1", null);
            //adjust cursor to first position.
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndexOrThrow(TABLE1_COLUMN1_NAME));
        }
    }

    //Method 5 : To add a specific student to a specific class.
    //Inputs : IDs of the of the specific class and student.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean addStudentToClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE5_COLUMN1_NAME,studentID);
        contentValues.put(TABLE5_COLUMN2_NAME,classID);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.insert(TABLE5_NAME,null, contentValues)>0;
    }

    //Method 6 : To add a note to a specific student
    //Inputs : ID of the of the specific student and the note to be added.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean addStudentNote(int studentID, String note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN6_NAME,note);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.update(TABLE1_NAME,contentValues,TABLE1_COLUMN1_NAME+" = "+studentID+" ",null) > 0;
    }

    //Method 7 : To add a specific done assignment to a specific student with the grade.
    //Inputs : IDs of the specific student and assignment and the grade to be added.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean checkAssignmentOff(int studentID,int assignmentID, int studentRate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE8_COLUMN1_NAME,studentID);
        contentValues.put(TABLE8_COLUMN2_NAME,assignmentID);
        contentValues.put(TABLE8_COLUMN3_NAME,studentRate);
        //if result is not -1 ( > 0 ) , returns true in case of successful insertion.
        return sqLiteDatabase.insert(TABLE8_NAME,null,contentValues) > 0 ;
    }

    //Method 8 : To add a specific done exam to a specific student with the grade.
    //Inputs : IDs of the specific student and exam and the grade to be added.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean putExamGrade(int studentID, int examID, String studentGrade){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE9_COLUMN1_NAME,studentID);
        contentValues.put(TABLE9_COLUMN2_NAME,examID);
        contentValues.put(TABLE9_COLUMN3_NAME,studentGrade);
        //if result is not -1 ( > 0 ) , returns true in case of successful insertion.
        return sqLiteDatabase.insert(TABLE9_NAME,null,contentValues) > 0 ;
    }

    //================= Deletion Methods ==============

    //Method 1 : To remove a specific assignment from the system.
    //Inputs : ID of the specific assignment to be removed.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean removeAssignment(int assignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE8_NAME, TABLE8_COLUMN2_NAME + " = " + assignmentID + " ", null);
        sqLiteDatabase.delete(TABLE7_NAME, TABLE8_COLUMN2_NAME + " = " + assignmentID + " ", null);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE3_NAME, TABLE3_COLUMN1_NAME + " = " + assignmentID + " ", null)>0;
    }

    //Method 2 : To remove a specific exam from the system.
    //Inputs : ID of the specific exam to be removed.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean removeExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE6_NAME, TABLE6_COLUMN2_NAME + " = " + examID + " ", null);
        sqLiteDatabase.delete(TABLE9_NAME, TABLE9_COLUMN2_NAME + " = " + examID + " ", null);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE4_NAME, TABLE4_COLUMN1_NAME + " = " + examID + " ", null)>0;
    }

    //Method 3 : To remove a specific student from the system.
    //Inputs : ID of the specific student to be removed.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean removeStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        sqLiteDatabase.delete(TABLE8_NAME, TABLE8_COLUMN1_NAME + " = " + studentID + " ", null);
        sqLiteDatabase.delete(TABLE9_NAME, TABLE9_COLUMN1_NAME + " = " + studentID + " ", null);
        sqLiteDatabase.delete(TABLE5_NAME, TABLE5_COLUMN1_NAME + " = " + studentID + " ", null);
        return sqLiteDatabase.delete(TABLE1_NAME, TABLE1_COLUMN1_NAME + " = " + studentID + " ", null)>0;

    }

    //Method 4 : To remove a specific class from the system.
    //Inputs : ID of the specific class to be removed.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean removeClass(int ClassID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        sqLiteDatabase.rawQuery(" delete from "+TABLE4_NAME+" where "+TABLE4_COLUMN1_NAME+" = 'select "+TABLE6_COLUMN2_NAME+" FROM "+TABLE6_NAME+" where "+TABLE6_COLUMN1_NAME+" ="+ClassID+"'", null);
        sqLiteDatabase.rawQuery(" delete from "+TABLE3_NAME+" where "+TABLE3_COLUMN1_NAME+" = 'select "+TABLE7_COLUMN2_NAME+" FROM "+TABLE7_NAME+" where "+TABLE7_COLUMN1_NAME+" ="+ClassID+"'", null);
        sqLiteDatabase.delete(TABLE6_NAME, TABLE6_COLUMN1_NAME + " = " + ClassID + " ", null);
        sqLiteDatabase.delete(TABLE7_NAME, TABLE7_COLUMN1_NAME + " = " + ClassID + " ", null);
        sqLiteDatabase.delete(TABLE5_NAME, TABLE5_COLUMN2_NAME + " = " + ClassID + " ", null);
        return sqLiteDatabase.delete(TABLE2_NAME, TABLE5_COLUMN2_NAME + " = " + ClassID + " ", null)>0;

    }

    //Method 5 : To remove a specific student from a specific class.
    //Inputs : IDs of the specific class and student to be removed.
    //Outputs : False if failed ( value = -1 ) and True in case of success ( value > -1 ).
    public boolean removeStudentFromClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE5_NAME,TABLE5_COLUMN1_NAME+" = "+studentID+" and "+TABLE5_COLUMN2_NAME+" = "+classID,null)>0;
    }

    //================= Class Retrieval Methods ==============

    //Method 1 : Gets Student List that are enrolled in a specific class.
    //Inputs : ID of the specific class.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getStudentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_COLUMN2_NAME+
                " from "+TABLE1_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+classID
                +" and "+ TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);

    }

    //Method 2 : Gets Student List that are un-enrolled in a specific class.
    //Inputs : ID of the specific class.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getUnregisteredStudentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery( "select "+TABLE1_COLUMN1_NAME+" as _id,"+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+
                " except select "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+
                " join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+classID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME,null);
    }

    //Method 3 : Gets Assignment List added in a specific class.
    //Inputs : ID of the specific class.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id" , Second Column is "Name" and Third Column is "DueDate".
    public Cursor getAssignmentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id ,"+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+
                " from "+TABLE3_NAME+" join "+TABLE7_NAME+" on "+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME+"="+classID+
                " and "+ TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    //Method 4 : Gets Exam List added in a specific class.
    //Inputs : ID of the specific class.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id" , Second Column is "Name" and Third Column is "Date".
    public Cursor getExamsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id ,"+TABLE4_COLUMN2_NAME+","+TABLE4_COLUMN3_NAME+
                " from "+TABLE4_NAME+" join "+TABLE6_NAME+" on "+TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+"="+classID+
                " and "+ TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME,null);
    }

    //Method 5 : Gets details of a specific class.
    //Inputs : ID of the specific class.
    //Outputs : Cursor object with 1 row and 3 columns ---> First Column is "_id" (Row id --look at Note 5) , Second Column is "Name" and Third Column is "Description".
    public Cursor getClassInfo(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select rowid as _id ,"+TABLE2_COLUMN2_NAME+","+TABLE2_COLUMN3_NAME+
                " from "+TABLE2_NAME+" where "+TABLE2_COLUMN1_NAME+"="+classID,null);
    }

    //Method 6 : Gets all classes details in the system.
    //Inputs : None.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id", Second Column is "Name" and Third Column is "Description".
    public Cursor getAllClasses(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select Distinct "+TABLE2_COLUMN1_NAME+" as _id , "+TABLE2_COLUMN2_NAME+
                ","+ TABLE2_COLUMN3_NAME+ " from "+TABLE2_NAME+"" , null);
    }

    //================= Student Retrieval Methods ==============

    //Method 1 : Gets Class List that a specific student is enrolled into.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getClassesByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" as _id ,"+TABLE2_COLUMN2_NAME+
                " from "+TABLE2_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME,null);
    }

    //Method 2 : Gets a Done Assignment List by a specific student.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 4 columns ---> First Column is "_id" , Second Column is "Name" , Third Column is "DueDate" and Fourth column is "Grade"
    public Cursor getAssignmentsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery( "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id ,"+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE8_COLUMN3_NAME+
                " as Grade from "+TABLE3_NAME+" join "+TABLE8_NAME+" on "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    //Method 3 : Gets Exam List added done by a specific student
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id" , Second Column is "Name" and Third Column is "Grade".
    public Cursor getExamsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id ,"+TABLE4_COLUMN2_NAME+","+TABLE9_COLUMN3_NAME+
                " as Grade from "+TABLE4_NAME+" join "+TABLE9_NAME+" on "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME,null);
    }

    //Method 4 : Gets Class List that a specific student is un-enrolled into.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getUnregisteredClassesByStudent(int studentID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select  "+TABLE2_COLUMN1_NAME+" as _id , "+TABLE2_COLUMN2_NAME+ " from "+TABLE2_NAME+
                " except select "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" as _id ,"+TABLE2_COLUMN2_NAME+" from "+TABLE2_NAME+
                " join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME, null);
    }

    //Method 5 : Gets a Undone Assignment List by a specific student.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 2 columns ---> First Column is "Name" and Second Column is "DueDate".
    public Cursor getUndoneAssignmentsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select Distinct "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+" as Name ,"+TABLE3_NAME+"."+TABLE3_COLUMN3_NAME+" as DueDate from "+TABLE3_NAME+
                " join "+TABLE5_NAME+" join "+TABLE7_NAME+" join "+TABLE1_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" ="+studentID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+" = "+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME +
                " and "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+
                " except select Distinct "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+" as Name ,"+TABLE3_NAME+"."+TABLE3_COLUMN3_NAME+
                " as DueDate from "+TABLE3_NAME+ " join " +TABLE8_NAME+" join "+TABLE1_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+studentID+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    //Method 6 : Gets details for a specific student.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 1 row and 5 columns ---> First Column is "_id" (row id --look at Note 5 ), Second Column is "Name" , Third Column is "Email" , Fourth Column is "PhoneNumber" and Fifth Column is "Address".
    public Cursor getStudentInfo(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select rowid as _id , "+TABLE1_COLUMN2_NAME+","+TABLE1_COLUMN3_NAME+","+
                TABLE1_COLUMN4_NAME+","+TABLE1_COLUMN5_NAME+ " from "+TABLE1_NAME+
                " where "+TABLE1_COLUMN1_NAME+"="+studentID,null);
    }

    //Method 7 : Gets the Note written to a specific student.
    //Inputs : ID of the specific student.
    //Outputs : Cursor object with 1 row and 1 column ---> First Column is "Note".
    public Cursor getStudentNote(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE1_COLUMN6_NAME+" from "+TABLE1_NAME+
                " where "+TABLE1_COLUMN1_NAME+" = "+studentID,null);
    }

    //Method 8 : Gets all students details in the system.
    //Inputs : None.
    //Outputs : Cursor object with 5 columns ---> First Column is "_id" , Second Column is "Name" , Third Column is "Email" , Fourth Column is "PhoneNumber" and Fifth Column is "Address".
    public Cursor getAllStudents(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE1_COLUMN1_NAME+" as _id , "+
                TABLE1_COLUMN2_NAME+","+TABLE1_COLUMN3_NAME+","+TABLE1_COLUMN4_NAME+","+
                TABLE1_COLUMN5_NAME+" from "+TABLE1_NAME,null);

    }

    //================= Assignment Retrieval Methods ==============

    //Method 1 : Gets a list of students that has undone a specific assignment.
    //Inputs : ID of the specific assignment.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getUndoneStudentsByAssignment(int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id , "+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" as Name from "+TABLE1_NAME+
                " join "+TABLE5_NAME+" join "+TABLE7_NAME+" join "+TABLE3_NAME+
                " where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME +
                " and "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+
                " except select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" as Name from "+TABLE1_NAME+
                " join " +TABLE8_NAME+" join "+TABLE3_NAME+
                " where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" ="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    //Method 2 : Gets a list of students that has done a specific assignment.
    //Inputs : ID of the specific assignment.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id" , Second Column is "Name" and Third Column is "Grade"
    public Cursor getDoneStudentsByAssignment(int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery(" select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+
                TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" as Name , "+TABLE8_COLUMN3_NAME+" as Grade from "+TABLE1_NAME+
                " join "+TABLE8_NAME+" join "+TABLE3_NAME+ " where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" ="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    //Method 3 : Gets assignment details of a specific assignment.
    //Inputs : ID of the specific assignment.
    //Outputs : Cursor object with 4 columns ---> First Column is "Name" , Second Column is "DueDate" , Third Column is "Description" and Fourth Column is "Class".
    public Cursor getAssignmentInfo(int AssignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+" as Name ,"+TABLE3_COLUMN3_NAME+
                ","+TABLE3_NAME+"."+TABLE3_COLUMN4_NAME+" as Description ,"+ TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" as Class from "+
                TABLE3_NAME+" join "+TABLE7_NAME+" join "+TABLE2_NAME+
                " where "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+ TABLE7_NAME+"."+TABLE7_COLUMN1_NAME+
                " and "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" = "+AssignmentID,null);
    }

    //Method 4 : Gets all assignment details in the system.
    //Inputs : None.
    //Outputs : Cursor object with 4 columns ---> First Column is "_id" , Second Column is "Name" , Third Column is "DueDate" and Fourth Column is "Class".
    public Cursor getAllAssignments(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query_String = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id,"+
                TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+" as Name , "+TABLE3_COLUMN3_NAME+","+TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+
                " as Class from "+TABLE3_NAME+" join "+TABLE7_NAME+" join "+TABLE2_NAME+" where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " = "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+
                TABLE7_NAME+"."+TABLE7_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(query_String,null);
    }

    //================= Exam Retrieval Methods ==============

    //Method 1 : Gets a list of students that has undone a specific exam.
    //Inputs : ID of the specific exam.
    //Outputs : Cursor object with 2 columns ---> First Column is "_id" and Second Column is "Name".
    public Cursor getUndoneStudentsByExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+
                " as Name from "+TABLE1_NAME+" join "+TABLE4_NAME+ " join "+TABLE6_NAME+" join "+TABLE5_NAME+
                " where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+" = "+TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " except select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+
                " as Name from "+TABLE1_NAME+" join "+TABLE4_NAME+ " join "+TABLE9_NAME+
                " where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID+
                " and "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME,null);

    }

    //Method 2 : Gets a list of students that has done a specific exam.
    //Inputs : ID of the specific exam.
    //Outputs : Cursor object with 3 columns ---> First Column is "_id" , Second Column is "Name" and Third Column is "Grade"
    public Cursor getDoneStudentsByExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+
                TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" as Name ,"+TABLE9_COLUMN3_NAME+
                " as Grade from "+TABLE1_NAME+" join "+TABLE9_NAME+" join "+TABLE4_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID,null);
    }

    //Method 3 : Gets exam details of a specific exam.
    //Inputs : ID of the specific exam.
    //Outputs : Cursor object with 3 columns ---> First Column is "Name" , Second Column is "Date" and Third Column is "Class".
    public Cursor getExamInfo(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE4_NAME+"."+TABLE4_COLUMN2_NAME+" as Name ,"+TABLE4_COLUMN3_NAME+","+
                TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" as Class from "+TABLE4_NAME+" join "+TABLE6_NAME+" join "+TABLE2_NAME+
                " where "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+ TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID,null);
    }

    //Method 4 : Gets all exam details in the system.
    //Inputs : None.
    //Outputs : Cursor object with 4 columns ---> First Column is "_id" , Second Column is "Name" , Third Column is "Date" and Fourth Column is "Class".
    public Cursor getAllExams(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.rawQuery("select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id,"+
                TABLE4_NAME+"."+TABLE4_COLUMN2_NAME+" as Name ,"+TABLE4_COLUMN3_NAME+","+TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" as Class "+
                " from "+TABLE4_NAME+" join "+TABLE6_NAME+" join "+TABLE2_NAME+" where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " = "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+
                TABLE6_NAME+"."+TABLE6_COLUMN1_NAME,null);
    }

    //================= Modification Methods ==============

    public boolean editStudent(int studentID,String name,String email,String phoneNumber, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN2_NAME, name);
        contentValues.put(TABLE1_COLUMN3_NAME, email);
        contentValues.put(TABLE1_COLUMN4_NAME, phoneNumber);
        contentValues.put(TABLE1_COLUMN5_NAME, address);
        return sqLiteDatabase.update(TABLE1_NAME, contentValues, TABLE1_COLUMN1_NAME + " = " + studentID, null) > 0;
    }

}
