package com.example.ridley.memorygameesgi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ScoreDbHandler extends SQLiteOpenHelper 
{
    public  static  final String DBSCORE_NAME="dbScore.db";
    public static final int DBSCORE_VERSION=1;

    public  static final String SCORE_TABLE_NAME="Score";
    public  static final String SCORE_KEY="id";
    public  static final String SCORE_USERNAME="username";
    public  static final String SCORE_NBBEATS="nbbeats";
    public  static final String SCORE_TIME="time";


    public  static final String SCORE_TABLE_CREATE=
            "CREATE TABLE "+SCORE_TABLE_NAME+" ("+
            SCORE_KEY+" INTEGER PRIMARY KEY, "+
            SCORE_USERNAME+" TEXT, "+
            SCORE_NBBEATS+" INTEGER, "+
            SCORE_TIME+" TEXT )";


    public ScoreDbHandler(Context context) {
        super(context, DBSCORE_NAME, null, DBSCORE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SCORE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //CRUD
    public void addScore(ScoreGames newScore){
        SQLiteDatabase mdb=this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(SCORE_USERNAME,newScore.get_username());
        values.put(SCORE_NBBEATS,newScore.get_nbbeats());
        values.put(SCORE_TIME,newScore.get_time());

        long rowInsert=mdb.insert(SCORE_TABLE_NAME,null,values);

        mdb.close();
    }

    public ScoreGames getScore(int id){
        SQLiteDatabase mdb=this.getReadableDatabase();

        Cursor cursor=mdb.query(SCORE_TABLE_NAME,new String[]{SCORE_KEY,
                                SCORE_USERNAME,SCORE_NBBEATS,SCORE_TIME},SCORE_KEY+"=?",
                                new String[]{String.valueOf(id)},null,null,null);
        if (   cursor!=null)
            cursor.moveToFirst();

        ScoreGames foundScore=new ScoreGames(Integer.parseInt(cursor.getString(0)),
                                             cursor.getString(1),
                                              Integer.parseInt(cursor.getString(2)),
                                            cursor.getString(3));
        return foundScore;

    }

    public ArrayList<ScoreGames> getAllScores(){
        ArrayList<ScoreGames> listScores=new ArrayList<ScoreGames>();

        String selectQuery="SELECT * FROM "+SCORE_TABLE_NAME;

        SQLiteDatabase mdb=this.getReadableDatabase();

        Cursor cursor=mdb.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                ScoreGames aScore= new ScoreGames();

                aScore.set_id(Integer.parseInt(cursor.getString(0)));
                aScore.set_username(cursor.getString(1));
                aScore.set_nbbeats(Integer.parseInt(cursor.getString(2)));
                aScore.set_time(cursor.getString(3));

                listScores.add(aScore);
            }while (cursor.moveToNext());
        }

        return  listScores;


    }

    public void deleteScore(ScoreGames scoreToDelete){
        SQLiteDatabase mdb=this.getWritableDatabase();

        mdb.delete(SCORE_TABLE_NAME,SCORE_KEY+" =?",
                new String[]{String.valueOf(scoreToDelete.get_id())});
        mdb.close();
    }

}
