package com.example.ridley.memorygameesgi;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ridley on 19/07/15.
 */
public class AdapterListScore extends ArrayAdapter<ScoreGames> {
    Activity _activity;
    ArrayList<ScoreGames>listScores;
    private static LayoutInflater inflater=null;

    public AdapterListScore(Activity activity,int textViewResourceId ,ArrayList<ScoreGames> lscores) {
        super(activity, textViewResourceId, lscores);
        try{
            this._activity=activity;
            this.listScores=lscores;

            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }catch (Exception e){

        }

    }

    public int getCount(){
        return listScores.size();
    }
    public ScoreGames getItem(ScoreGames scorePosition){
        return scorePosition;
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder{
        public TextView display_name;

        public TextView display_nbBeats;

        public TextView display_time;
    }

    public View getView(int position, View convertView,ViewGroup parent){
        View vi=convertView;
        final ViewHolder holder;
        try{
            if (convertView==null){
                vi=inflater.inflate(R.layout.listscores_layout,null);
                holder=new ViewHolder();

                holder.display_name=(TextView)vi.findViewById(R.id.display_name);
                holder.display_nbBeats=(TextView)vi.findViewById(R.id.display_nbbeats);
                holder.display_time=(TextView)vi.findViewById(R.id.display_time);

                vi.setTag(holder);
            }else{
                holder=(ViewHolder) vi.getTag();
            }

            holder.display_name.setText(listScores.get(position).get_username());
            holder.display_nbBeats.setText(listScores.get(position).get_nbbeats().toString());
            holder.display_time.setText(listScores.get(position).get_time());

        }catch (Exception e){
            Log.i("ExceptionAf",e.getMessage());
        }

        return vi;

    }
}
