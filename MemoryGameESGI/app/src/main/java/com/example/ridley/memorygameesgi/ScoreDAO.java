package com.example.ridley.memorygameesgi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by Ridley on 13/07/15.
 */
public class ScoreDAO extends DAOBase {
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
    public  static final String SCORE_TABLE_DELETE= "DROP TABLE IF EXISTS "+SCORE_TABLE_NAME+" ;";

    public ScoreDAO(Context context) {
        super(context);
    }

    public  void AddScore(ScoreGames newScore)
    {
        /*String reqInsert=" INSERT INTO "+SCORE_TABLE_NAME+
                "("+SCORE_USERNAME+","+SCORE_NBBEATS+","+SCORE_TIME+")VALUES "+
                "("+newScore.get_username()+","+newScore.get_nbbeats()+","+newScore.get_time()+");";*/

        ContentValues values=new ContentValues();
        values.put(SCORE_USERNAME,newScore.get_username());
        values.put(SCORE_NBBEATS,newScore.get_nbbeats());
        values.put(SCORE_TIME,newScore.get_time());

        mdb.insert(SCORE_TABLE_NAME,null,values);
    }

    public  Cursor SelectScore(String user)
    {
        Cursor selectQuery=mdb.rawQuery("Select "
                +SCORE_USERNAME+","+SCORE_NBBEATS+","+SCORE_TIME
                + " from "+SCORE_TABLE_NAME+" where "+SCORE_USERNAME+"=?"
                ,new String[]{user});

        return selectQuery;
    }

    public  void DeleteScore(long idScore)
    {
        mdb.delete(SCORE_TABLE_NAME,SCORE_KEY+"=?",new String[]{String.valueOf(idScore)});

    }
}
