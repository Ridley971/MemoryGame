package com.example.ridley.memorygameesgi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDbHandler extends SQLiteOpenHelper 
{
    public  static final String SCORE_KEY="id";
    public  static final String SCORE_USERNAME="username";
    public  static final String SCORE_NBBEATS="nbbeats";
    public  static final String SCORE_TIME="time";

    public  static final String SCORE_TABLE_NAME="Score";
    public  static final String SCORE_TABLE_CREATE=
            "CREATE TABLE "+SCORE_TABLE_NAME+" ("+
            SCORE_KEY+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            SCORE_USERNAME+"TEXT,"+
            SCORE_NBBEATS+"INTEGER,"+
            SCORE_TIME+"TEXT);";
    
    public ScoreDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
