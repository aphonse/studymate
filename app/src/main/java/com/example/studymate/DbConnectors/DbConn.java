package com.example.studymate.DbConnectors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.studymate.GUI.FindQuestions;


public class DbConn extends SQLiteOpenHelper {
    public static final String DBNAME = "Studymate.db", TABLE = "Questions",
            COLID = "id", COLTOPIC = "Topic", COLQTEXT = "QuestionText", COLQPIC = "QuestionPic", COLQANSWERS = "Answers", COMMENTS = "comments";
    public String topic;
    static DbConn INSTANCE;
    SQLiteDatabase db;
    //    ArrayList<String> ans=new ArrayList<>();
    String ans;
    String question;
    Context context;

    public DbConn(Context context) {
        super(context, DBNAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        INSTANCE=this;
        db.execSQL("CREATE TABLE Questions(id INTEGER PRIMARY KEY AUTOINCREMENT,Topic TEXT,QuestionText TEXT,QuestionPic BLOB,Answers TEXT,comments TEXT)");
        db.execSQL("CREATE TABLE Answers(id INTEGER PRIMARY KEY AUTOINCREMENT,Questions TEXT,Answers TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Questions");
        db.execSQL("DROP TABLE IF EXISTS Answers");
//create tables again
        onCreate(db);
    }

    public boolean AskingQuestions(String Question, String Topic, byte[] pic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(COLTOPIC, Topic);
        vals.put(COLQTEXT, Question);
        vals.put(COLQPIC, pic);
        long res = db.insert(TABLE, null, vals);
        return res != -1;
    }

    public Cursor getData() {
        SQLiteDatabase dab = this.getWritableDatabase();
        String topic1 = FindQuestions.getINSTANCE().getTopic1();
//        String topic="bio";
        Cursor res = dab.rawQuery("SELECT * FROM Questions WHERE Topic='" + topic1 + "'", null);
        return res;
    }

    public boolean InsertAns(String answer,String question){
        SQLiteDatabase dab = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put("Questions",question);
        vals.put("Answers",answer);
        long res = dab.insert("Answers", null, vals);
        Log.d("hello world", "insert: "+res);
        return res!=-1;


    }
    public static DbConn getINSTANCE(){
        return INSTANCE;
    }


    public Cursor getAnswer(String question) {
        SQLiteDatabase dab = this.getWritableDatabase();
//        String text=null;
//        Cursor res = dab.rawQuery("SELECT * FROM Answers WHERE Questions='"+question+"'", null);
        Cursor res1 = dab.rawQuery("SELECT * FROM Answers WHERE Questions='" + question + "'", null);
//        if (res1.moveToNext()){
//          String  text=res1.getString(1);
//            Log.d("hello world", "getAnswer: "+text);
//
//        }else {
//            Log.d("hello world", "failed: ");
//        }
//        Log.d("hello world", "quiz: "+ question);

        return res1;
    }

}
