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
 */

public class DataBaseHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentManager.db";
    private static int DATABASE_VERSION = 5;
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

    //To add an assignment to a specific classImage.
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

    //To add an exam to a specific classImage.
    //Returns Exam ID that has been created and -1 if the creation has failed.
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

    //Adding a Class with name and description ONLY.
    //Returns the classImage ID created or -1 if the creation failed.
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

    //Adding a Class with name and email ONLY.
    //Returns the student ID created or -1 if the creation failed.
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

    //In this method, we only need to remove a student from classImage,
    // Returns -1 if failed and nothing specific to be returned in case of success ( not -1 )
    public boolean removeStudentFromClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE5_NAME,TABLE5_COLUMN1_NAME+" = "+studentID+" and "+TABLE5_COLUMN2_NAME+" = "+classID,null)>0;
    }

    //In this method, we only need to add a student to classImage,
    // Returns -1 if failed and nothing specific to be returned in case of success ( not -1 )
    public boolean addStudentToClass(int studentID, int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE5_COLUMN1_NAME,studentID);
        contentValues.put(TABLE5_COLUMN2_NAME,classID);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.insert(TABLE5_NAME,null, contentValues)>0;
    }

    //This method returns a student list that is enrolled in a classImage given by the classID parameter.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name
    public Cursor getStudentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" as _id ,"+TABLE1_COLUMN2_NAME+
                " from "+TABLE1_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+classID
                +" and "+ TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);

    }

    //This method returns a assignment list that is assigned to a classImage given by the classID parameter.
    //It return a Cursor Object of 3 columns ---> First Column is ID and Second Column is Name and the third column is DueDate
    public Cursor getAssignmentsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id ,"+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+
                " from "+TABLE3_NAME+" join "+TABLE7_NAME+" on "+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME+"="+classID+
                " and "+ TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a exam list that is done by a classImage given by the classID parameter.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name and the third is the Date.
    public Cursor getExamsByClass(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id ,"+TABLE4_COLUMN2_NAME+","+TABLE4_COLUMN3_NAME+
                " from "+TABLE4_NAME+" join "+TABLE6_NAME+" on "+TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+"="+classID+
                " and "+ TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns 3 columns and only 1 row in Cursor --> First is RowID of course, Second is Name of Course , Third is the description.
    //Rowid column is not important for App use but there must be a column named _id in cursor returned in order for CursorAdpater to work correctly.
    public Cursor getClassInfo(int classID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString ="select rowid as _id ,"+TABLE2_COLUMN2_NAME+","+TABLE2_COLUMN3_NAME+
                " from "+TABLE2_NAME+" where "+TABLE2_COLUMN1_NAME+"="+classID;

        return sqLiteDatabase.rawQuery(queryString,null);

    }

    //This method returns a classImage list that is a student (given by the studentID parameter) is enrolled in.
    //It return a Cursor Object of 2 columns ---> First Column is ID and Second Column is Name
    public Cursor getClassesByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" as _id ,"+TABLE2_COLUMN2_NAME+
                " from "+TABLE2_NAME+" join "+TABLE5_NAME+" on "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a assignment list that is assigned to and done by a student given by the studentID parameter.
    //It return a Cursor Object of 4 columns ---> First Column is ID and Second Column is Name and the third column is DueDate and the fourth is the student rate.
    public Cursor getAssignmentsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id ,"+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE8_COLUMN3_NAME+
                " from "+TABLE3_NAME+" join "+TABLE8_NAME+" on "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+"="+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //This method returns a exam list that is assigned to and done by a student given by the studentID parameter.
    //It return a Cursor Object of 3 columns ---> First Column is ID and Second Column is Name and the third column is the student grade.
    public Cursor getExamsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString = "select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id ,"+TABLE4_COLUMN2_NAME+","+TABLE9_COLUMN3_NAME+
                " from "+TABLE4_NAME+" join "+TABLE9_NAME+" on "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+"="+studentID+
                " and "+ TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+"="+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //returns a Cursor of a specific student data's email and name, 3 columns : rowid , name , email
    //Rowid column is not important for App use but there must be a column named _id in cursor returned in order for CursorAdpater to work correctly.
    public Cursor getStudentInfo(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String queryString ="select rowid as _id,"+TABLE1_COLUMN2_NAME+","+TABLE1_COLUMN3_NAME+","+TABLE1_COLUMN4_NAME+","+TABLE1_COLUMN5_NAME+
                " from "+TABLE1_NAME+" where "+TABLE1_COLUMN1_NAME+"="+studentID;

        return sqLiteDatabase.rawQuery(queryString,null);
    }

    //returns a Cursor of all student records and 5 columns ---> ID, Name,Email,Number,Address
    public Cursor getAllStudents(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query_String = "select "+TABLE1_COLUMN1_NAME+" as _id,"+
                TABLE1_COLUMN2_NAME+","+TABLE1_COLUMN3_NAME+","+TABLE1_COLUMN4_NAME+","+
                TABLE1_COLUMN5_NAME+" from "+TABLE1_NAME;
        return sqLiteDatabase.rawQuery(query_String,null);

    }

    //This method is made to return a list of all assignments in the system.
    //The cursor returned has 4 columns ---> Assignment ID which is returned as _id column for CursorAdpater usage if any.
    //second column is AssignmentName , third column is the due date , fourth column is the class name this assignment is assigned to.
    public Cursor getAllAssignments(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query_String = "select "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" as _id,"+
                TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+
                " from "+TABLE3_NAME+" join "+TABLE7_NAME+" join "+TABLE2_NAME+" where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " = "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+
                TABLE7_NAME+"."+TABLE7_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(query_String,null);
    }

    //This method is made to return a list of all exams in the system.
    //The cursor returned has 4 columns ---> Exam ID which is returned as _id column for CursorAdpater usage if any.
    //second column is Exam Name , third column is the date , fourth column is the class name this assignment is assigned to.
    public Cursor getAllExams(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query_String = "select "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" as _id,"+
                TABLE4_NAME+"."+TABLE4_COLUMN2_NAME+","+TABLE4_COLUMN3_NAME+","+TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" as className"+
                " from "+TABLE4_NAME+" join "+TABLE6_NAME+" join "+TABLE2_NAME+" where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " = "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+
                TABLE6_NAME+"."+TABLE6_COLUMN1_NAME;

        return sqLiteDatabase.rawQuery(query_String,null);
    }

    public boolean checkAssignmentOff(int studentID,int assignmentID, int studentRate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE8_COLUMN1_NAME,studentID);
        contentValues.put(TABLE8_COLUMN2_NAME,assignmentID);
        contentValues.put(TABLE8_COLUMN3_NAME,studentRate);
        //if result is not -1 ( > 0 ) , returns true in case of successful insertion.
        return sqLiteDatabase.insert(TABLE8_NAME,null,contentValues) > 0 ;
    }
    public boolean putExamGrade(int studentID, int examID, int studentgrade){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE9_COLUMN1_NAME,studentID);
        contentValues.put(TABLE9_COLUMN2_NAME,examID);
        contentValues.put(TABLE9_COLUMN3_NAME,studentgrade);
        //if result is not -1 ( > 0 ) , returns true in case of successful insertion.
        return sqLiteDatabase.insert(TABLE9_NAME,null,contentValues) > 0 ;
    }

    public boolean editStudent(int studentID,String name,String email,String phoneNumber, String address) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN2_NAME, name);
        contentValues.put(TABLE1_COLUMN3_NAME, email);
        contentValues.put(TABLE1_COLUMN4_NAME, phoneNumber);
        contentValues.put(TABLE1_COLUMN5_NAME, address);
        return sqLiteDatabase.update(TABLE1_NAME, contentValues, TABLE1_COLUMN1_NAME + " = " + studentID, null) > 0;
    }

    public Cursor getUndoneAssignmentsByStudent(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+","+TABLE3_NAME+"."+TABLE3_COLUMN3_NAME+" from "+TABLE3_NAME+
                " join "+TABLE5_NAME+" join "+TABLE7_NAME+" join "+TABLE1_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" ="+studentID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME +
                " and "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+
                " except select Distinct "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+","+TABLE3_NAME+"."+TABLE3_COLUMN3_NAME+" from "+TABLE3_NAME+
                " join " +TABLE8_NAME+" join "+TABLE1_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" ="+studentID+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" ="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }

    public Cursor getStudentListByExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+
                " from "+TABLE1_NAME+" join "+TABLE4_NAME+ " join "+TABLE6_NAME+" join "+TABLE5_NAME+
                " where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+" = "+TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " except select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+
                " from "+TABLE1_NAME+" join "+TABLE4_NAME+ " join "+TABLE9_NAME+
                " where "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID+
                " and "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+" = "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME,null);

    }

    public Cursor getDoneStudentListByExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+","+TABLE9_COLUMN3_NAME+
                " from "+TABLE1_NAME+" join "+TABLE9_NAME+" join "+TABLE4_NAME+
                " where "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE9_NAME+"."+TABLE9_COLUMN1_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+TABLE9_NAME+"."+TABLE9_COLUMN2_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID,null);
    }

    public Cursor getUndoneAssignmentList(int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+
                " join "+TABLE5_NAME+" join "+TABLE7_NAME+" join "+TABLE3_NAME+
                " where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+
                " and "+TABLE5_NAME+"."+TABLE5_COLUMN2_NAME+"="+TABLE7_NAME+"."+TABLE7_COLUMN1_NAME +
                " and "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" = "+TABLE5_NAME+"."+TABLE5_COLUMN1_NAME+
                " except select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+" from "+TABLE1_NAME+
                " join " +TABLE8_NAME+" join "+TABLE3_NAME+
                " where "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" ="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+
                " and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }
    public Cursor getDoneAssignmentList(int AssignmentID ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery(" select Distinct "+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+","+TABLE1_NAME+"."+TABLE1_COLUMN2_NAME+","+TABLE8_COLUMN3_NAME+" from "+TABLE1_NAME+" join "+TABLE8_NAME+" join "+TABLE3_NAME+
                " where  "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" ="+AssignmentID+" and "+TABLE8_NAME+"."+TABLE8_COLUMN1_NAME+" ="+TABLE1_NAME+"."+TABLE1_COLUMN1_NAME+" and "+TABLE8_NAME+"."+TABLE8_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME,null);
    }


    //This returns a cursor with 3 Columns : ID , Name , Description.
    //The ID is returned as _id for Cursor Adapter Usage only and hav eno special meaning.
    public Cursor getClassList(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE2_COLUMN1_NAME+" as _id , "+TABLE2_COLUMN2_NAME+ " , "+ TABLE2_COLUMN3_NAME+" from "+TABLE2_NAME+"" , null);
    }

    public Cursor getStudentClassList(int StudentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select Distinct "+TABLE2_COLUMN1_NAME+", "+TABLE2_COLUMN2_NAME+" from "+TABLE2_NAME+" join "+TABLE1_NAME+" join "+TABLE5_NAME+"" +
                " where "+TABLE5_COLUMN1_NAME+"= "+TABLE1_COLUMN1_NAME+" and "+TABLE2_COLUMN1_NAME+" = "+TABLE5_COLUMN2_NAME+" and "+TABLE1_COLUMN1_NAME+"= '"+StudentID+"'", null);
    }
    //This function gets the assignment ID as input and returns a Cursor with 5 columns.
    //First Column --> Name , Second ---> DueDate , Third ---> Description , Fourth ---> Class Name
    public Cursor getAssignmentInfo(int AssignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select "+TABLE3_NAME+"."+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE3_NAME+"."+TABLE3_COLUMN4_NAME+","+
                TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" from "+TABLE3_NAME+" join "+TABLE7_NAME+" join "+TABLE2_NAME+
                " where "+TABLE7_NAME+"."+TABLE7_COLUMN2_NAME+" = "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+
                " and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+ TABLE7_NAME+"."+TABLE7_COLUMN1_NAME+
                " and "+TABLE3_NAME+"."+TABLE3_COLUMN1_NAME+" = "+AssignmentID,null);
    }
    //This function gets the exam ID as input and returns a Cursor with 3 columns.
    //First Column --> Name , Second ---> Date , Third ---> Class Name
    public Cursor getExamInfo(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select "+TABLE4_NAME+"."+TABLE4_COLUMN2_NAME+","+TABLE4_COLUMN3_NAME+","+
                TABLE2_NAME+"."+TABLE2_COLUMN2_NAME+" from "+TABLE4_NAME+" join "+TABLE6_NAME+" join "+TABLE2_NAME+
                " where "+TABLE6_NAME+"."+TABLE6_COLUMN2_NAME+" = "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+
                " and "+TABLE2_NAME+"."+TABLE2_COLUMN1_NAME+" = "+ TABLE6_NAME+"."+TABLE6_COLUMN1_NAME+
                " and "+TABLE4_NAME+"."+TABLE4_COLUMN1_NAME+" = "+examID,null);
    }
    //use this function when you want to return info about an assignment for a specific classImage
    //note that you should also use get DoneAssignment and UndoneAssignment function to get all required info on each assignment
    public Cursor getAssigmentInfo(int ClassID,int AssignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select "+TABLE3_COLUMN1_NAME+","+TABLE3_COLUMN2_NAME+","+TABLE3_COLUMN3_NAME+","+TABLE3_COLUMN4_NAME+"" +
                " from "+TABLE3_NAME+" join "+TABLE2_NAME+" join "+TABLE7_NAME+"" +
                " where "+TABLE3_COLUMN1_NAME+" = "+TABLE7_COLUMN2_NAME+"" +
                " and "+TABLE2_COLUMN1_NAME+" = "+TABLE7_COLUMN1_NAME+"" +
                " and "+TABLE2_COLUMN1_NAME+" = '"+ClassID+"' " +
                " and "+TABLE7_COLUMN2_NAME+" ='"+AssignmentID+"'  ", null);
    }
    // use this function to get all info on a specific exam with the students who took it and their grade
    public Cursor getExamInfo(int ClassID,int ExamID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery(" select "+TABLE4_COLUMN1_NAME+","+TABLE4_COLUMN2_NAME+","+TABLE1_COLUMN1_NAME+","+TABLE1_COLUMN2_NAME+","+TABLE9_COLUMN3_NAME+"" +
                " from "+TABLE1_NAME+" join "+TABLE4_NAME+" join "+TABLE2_NAME+" join "+TABLE6_NAME+" JOIN "+TABLE9_NAME+"" +
                " where "+TABLE4_COLUMN1_NAME+" = "+TABLE6_COLUMN2_NAME+" = "+TABLE9_COLUMN2_NAME+"" +
                " and "+TABLE6_COLUMN1_NAME+" ="+TABLE2_COLUMN1_NAME+"" +
                " and "+TABLE1_COLUMN1_NAME+"= "+TABLE9_COLUMN1_NAME+"" +
                " and "+TABLE2_COLUMN1_NAME+" = '"+ClassID+"'" +
                " and "+TABLE9_COLUMN2_NAME+"= '"+ExamID+"' ", null);
    }

    //Method to remove an assignment from the system.
    //Remove goes through 3 Tables --> AssignmentStudentRelation , AssignmentClassRelation , Assignments
    public boolean removeAssignment(int assignmentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE8_NAME, TABLE8_COLUMN2_NAME + " = " + assignmentID + " ", null);
        sqLiteDatabase.delete(TABLE7_NAME, TABLE8_COLUMN2_NAME + " = " + assignmentID + " ", null);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE3_NAME, TABLE3_COLUMN1_NAME + " = " + assignmentID + " ", null)>0;
    }

    //Method to remove an exam from the system.
    //Remove goes through 3 Tables --> ExamStudentRelation , ExamClassRelation , Exams
    public boolean removeExam(int examID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE6_NAME, TABLE6_COLUMN2_NAME + " = " + examID + " ", null);
        sqLiteDatabase.delete(TABLE9_NAME, TABLE9_COLUMN2_NAME + " = " + examID + " ", null);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.delete(TABLE4_NAME, TABLE4_COLUMN1_NAME + " = " + examID + " ", null)>0;
    }


    //DropStudentFromSystem function , student will no longer appear in anything
    public boolean DropStudentFromSystem(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        sqLiteDatabase.delete(TABLE8_NAME, TABLE8_COLUMN1_NAME + " = " + studentID + " ", null);
        sqLiteDatabase.delete(TABLE9_NAME, TABLE9_COLUMN1_NAME + " = " + studentID + " ", null);
        sqLiteDatabase.delete(TABLE5_NAME, TABLE5_COLUMN1_NAME + " = " + studentID + " ", null);
        return sqLiteDatabase.delete(TABLE1_NAME, TABLE1_COLUMN1_NAME + " = " + studentID + " ", null)>0;

    }

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

    public boolean setStudentNote(int studentID,String note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE1_COLUMN6_NAME,note);
        //If result is bigger than 0 ( i.e not -1 ) ----> return success.
        return sqLiteDatabase.update(TABLE1_NAME,contentValues,TABLE1_COLUMN1_NAME+" = "+studentID+" ",null) > 0;
    }

    public Cursor getStudentNote(int studentID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select "+TABLE1_COLUMN6_NAME+" from "+TABLE1_NAME+" where "+TABLE1_COLUMN1_NAME+" = "+studentID,null);
    }





}
