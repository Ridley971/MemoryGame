package com.example.ridley.memorygameesgi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ridley on 13/07/15.
 */
public abstract class DAOBase
{
    protected  final static int DbVersion=1;
    protected final static  String FileDbName="databaseMemoryGame.db";

    protected SQLiteDatabase mdb=null;
    protected ScoreDbHandler mHandler=null;

    public DAOBase(Context context)
    {
        mHandler=new ScoreDbHandler(context);
    }

    public SQLiteDatabase open()
    {
        mdb=mHandler.getWritableDatabase();
        return mdb;
    }
    public void closeDB()
    {
        mdb.close();
    }

    public SQLiteDatabase getDB()
    {
        return mdb;
    }
}
