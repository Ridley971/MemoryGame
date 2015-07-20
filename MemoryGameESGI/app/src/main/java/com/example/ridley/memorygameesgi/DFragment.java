package com.example.ridley.memorygameesgi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Ridley on 16/07/15.
 */
public class DFragment extends DialogFragment {

    public static  DFragment newInstance(int hours, int min,int sec,int nbbeats)
    {
        DFragment df=new DFragment();

        Bundle args=new Bundle();
        args.putInt("hours",hours);
        args.putInt("min",min);
        args.putInt("sec",sec);
        args.putInt("nbBeats",nbbeats);

        df.setArguments(args);

        return df;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Bundle args=getArguments();
        int hours=args.getInt("hours");
        int min=args.getInt("min");
        int sec=args.getInt("sec");
        int nbBeats=args.getInt("nbBeats");

        String time=hours+":"+min+":"+sec;

        String gameEndFormat= getResources().getString(R.string.GameWin);

        String gameEnd=String.format(gameEndFormat,nbBeats,time);

        return new AlertDialog.Builder(getActivity())

                .setTitle(getResources().getString(R.string.Congratulations))
                .setMessage(gameEnd)
                .setPositiveButton("OK",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        Intent intent =new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                         getActivity().finish();

                    }
                }).create();
    }

}
