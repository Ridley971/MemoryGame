package com.example.ridley.memorygameesgi;

/**
 * Created by Ridley on 13/07/15.
 */
public class ScoreGames
{
    private long _id=0;
    private String _username;
    private Integer _nbbeats;
    private String _time;

    public ScoreGames( String user, Integer beats, String time)
    {
        _id++;
        _username=user;
        _nbbeats=beats;
        _time=time;
    }

    public long get_id(){return _id;}
    public String get_username(){return _username;}
    public Integer get_nbbeats(){return  _nbbeats;}
    public String get_time(){return  _time;}

    public void set_id(Integer id){ _id=id;}
    public void set_username(String user){ _username=user;}
    public void set_nbbeats(Integer beats){  _nbbeats=beats;}
    public void set_time(String time){  _time=time;}
}