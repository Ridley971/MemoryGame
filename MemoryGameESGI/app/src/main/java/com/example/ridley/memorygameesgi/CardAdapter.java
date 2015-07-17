package com.example.ridley.memorygameesgi;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Ridley on 03/07/15.
 */
public class CardAdapter extends BaseAdapter {

    private Context mcontext;

    private Integer[] tabCards={R.drawable.abricot,R.drawable.ananas,R.drawable.banane,
                                R.drawable.carambole,R.drawable.cerise,R.drawable.fraise,
                                R.drawable.goyave,R.drawable.kiwi,R.drawable.litchi,
                                R.drawable.mangue,R.drawable.melon,R.drawable.orange,
                                R.drawable.papaye,R.drawable.pasteque,R.drawable.poire,
                                R.drawable.pomme,R.drawable.raisin,R.drawable.tomate,
                                R.drawable.abricot,R.drawable.ananas,R.drawable.banane,
                                R.drawable.carambole,R.drawable.cerise,R.drawable.fraise,
                                R.drawable.goyave,R.drawable.kiwi,R.drawable.litchi,
                                R.drawable.mangue,R.drawable.melon,R.drawable.orange,
                                R.drawable.papaye,R.drawable.pasteque,R.drawable.poire,
                                R.drawable.pomme,R.drawable.raisin,R.drawable.tomate
                                };

    public CardAdapter(Context c)
    {
        mcontext=c;

        for ( int i=0;i<tabCards.length;i++)
        {
            Log.i("InfoTab","Indice:"+i+" val:"+tabCards[i]);
        }

        shuffleArray(tabCards);

        for ( int i=0;i<tabCards.length;i++)
        {
            Log.i("InfoTab","Indice:"+i+" val:"+tabCards[i]);
        }
    }

    private void shuffleArray(Integer[] tabCards)
    {
        Random rnd=new Random();

        for (int i = tabCards.length-1; i >0 ; i--) {

            int index=rnd.nextInt(i+1);
            int a=tabCards[index];

            tabCards[index]=tabCards[i];
            tabCards[i]=a;
        }
    }

    public  int getCount()
    {
        return tabCards.length;
    }

    public Object getItem(int position)
    {
        return  tabCards[position];
    }

    public long getItemId(int position)
    {

        return   tabCards[position];
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView aCard;

        if (  convertView ==null)
        {
            aCard =new ImageView(mcontext);
            aCard.setLayoutParams(new GridView.LayoutParams(100,100));
            aCard.setScaleType(ImageView.ScaleType.CENTER_CROP);
            aCard.setPadding(10,10,10,10);
            aCard.setColorFilter(Color.GREEN);
        }else
        {
            aCard=(ImageView)convertView;
        }

        aCard.setImageResource(tabCards[position]);

        return  aCard;
    }
}
