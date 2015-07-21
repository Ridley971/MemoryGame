package com.example.ridley.memorygameesgi;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends FragmentActivity {

    GridView gridGame;
   public ImageView lastCardSelected;
    public ImageView cardSelected;
    Chronometer chrono;
    TextView txtNbBeats;

    FragmentManager fm=getSupportFragmentManager();
    DFragment dfragment= new DFragment();

     final Handler myHandler = new Handler();

    long IDlastCard;
    int lastposition=0;
    int nbBeats=0;
    int total=0;
    int callSolve=0;
    Boolean firstClick=true;
    String nikname;
    ScoreDbHandler mdb=new ScoreDbHandler(this);


   private void doSomeHardWork()
   {
       //.... hard work

       //update the UI using the handler and the runnable
       myHandler.post(updateRunnable);

   }

    public void reinitializeCards()
    {
        lastCardSelected.setColorFilter(Color.GREEN);
        cardSelected.setColorFilter(Color.GREEN);
        cardSelected=null;
        lastCardSelected=null;
    }

    final Runnable updateRunnable = new Runnable() {
        public void run() {
            //call the activity method that updates the UI
            reinitializeCards();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        chrono=(Chronometer) findViewById(R.id.chrono);
        txtNbBeats=(TextView) findViewById(R.id.txtNbBeats);
        gridGame=(GridView) findViewById(R.id.gridView);
        gridGame.setAdapter(new CardAdapter(this));

        SharedPreferences settings = getSharedPreferences("prefNickname", 0);
        nikname=settings.getString("nickname", null);

        gridGame.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.i("TrackinRidley","IDCARD:"+id+" Position:"+position);


                nbBeats++;

                txtNbBeats.setText(Integer.toString(nbBeats));

                cardSelected =(ImageView) view;


               cardSelected.clearColorFilter();
               if(firstClick)
                {
                    firstClick=false;
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    Log.i("TrackinRidley","Chrono Start");
                }

                if (lastCardSelected==null )
                {

                    IDlastCard=id;
                    lastposition=position;
                    lastCardSelected=cardSelected;
                    Log.i("TrackinRidley","Initialisation de la carte");
                }
                else if (IDlastCard!=id)
                {
                    Log.i("TrackinRidley","Démarrage du thread");

                    Log.i("TrackinRidley","CURRENTCARD "+id);
                    Log.i("TrackinRidley","IDLASTCARD BeforeThread"+IDlastCard);
                    Log.i("TrackinRidley","lastCardSelected BeforeThread"+lastCardSelected);
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(500);

                                doSomeHardWork();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    IDlastCard=0;
                    Log.i("TrackinRidley","IDLASTCARD AfterThread"+IDlastCard);
                    Log.i("TrackinRidley","lastCardSelected AfterThread"+lastCardSelected);
                }
                else if(IDlastCard==id && lastposition!=position)
                {
                    lastCardSelected.setVisibility(View.INVISIBLE);
                    cardSelected.setVisibility(View.INVISIBLE);
                    lastCardSelected=null;
                    total++;
                    if (total==18)
                    {
                        chrono.stop();
                        long timeElapased=SystemClock.elapsedRealtime()-chrono.getBase();

                        int hours=(int)(timeElapased/3600000);
                        int mins=(int)(timeElapased-hours*3600000)/60000;
                        int secs=(int)(timeElapased-hours*3600000-mins*60000)/1000;

                        String time=hours+":"+mins+":"+secs;


                        ScoreGames newScore = new ScoreGames(nikname,nbBeats,time);

                        mdb.addScore(newScore);

                        Bundle bun=new Bundle();

                        dfragment = dfragment.newInstance(hours,mins,secs,nbBeats);

                        dfragment.show(fm,"Hello");




                    }
                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickSolve(View v)
    {
        int nbView= gridGame.getChildCount();

        if (callSolve==0){
            callSolve++;
            for(int i=0; i<nbView;i++)
            {
                ImageView currentCard= (ImageView)gridGame.getChildAt(i);

                currentCard.setColorFilter(null);
            }
        }else {
            for(int i=0; i<nbView;i++)
            {
                ImageView currentCard= (ImageView)gridGame.getChildAt(i);

                currentCard.setColorFilter(Color.GREEN);
            }
        }
    }

}
