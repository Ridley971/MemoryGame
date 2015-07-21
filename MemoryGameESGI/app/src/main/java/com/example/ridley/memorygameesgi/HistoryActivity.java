package com.example.ridley.memorygameesgi;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class HistoryActivity extends ActionBarActivity {

    ListView listScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        listScores=(ListView) findViewById(R.id.listScores);
        registerForContextMenu(listScores);


        ScoreDbHandler mdb= new ScoreDbHandler(this);
        ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

        lScore=mdb.getAllScores();

       listScores.setAdapter( new AdapterListScore(this,0,lScore));

        handleIntent(getIntent());



    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query=intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);

        }


    }

    private void doMySearch(String query) {
        ScoreDbHandler mdb= new ScoreDbHandler(this);
        ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

        lScore=mdb.getScoreFromUser(query);

            Toast.makeText(getApplicationContext(),
                    lScore.size()+getString(R.string.NumberRowsFound)
                    ,Toast.LENGTH_LONG).show();


        listScores.setAdapter(new AdapterListScore(this, 0, lScore));

    }

    @Override
    protected  void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        /*getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;*/

        MenuInflater inflater =getMenuInflater();

        inflater.inflate(R.menu.menu_history,menu);

        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) menu.findItem(R.id.menu_action_searchUserName).getActionView();


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setQueryHint(getResources().getString(R.string.EnterUserSearch));

        return true;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.menu_action_searchUserName:
                listScores=(ListView) findViewById(R.id.listScores);

                ScoreDbHandler mdb= new ScoreDbHandler(this);
                ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

                lScore=mdb.getAllScores();

                listScores.setAdapter( new AdapterListScore(this,0,lScore));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        if (v.getId()==R.id.listScores) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

           String[] menuItems = getResources().getStringArray(R.array.menuItemList);

            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        if (  menuItemIndex==0) {
            ScoreDbHandler mdb=new ScoreDbHandler(this);

            ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

            lScore=mdb.getAllScores();

            ScoreGames scoreToDelete =lScore.get(info.position);


            Toast.makeText(getApplicationContext(),getResources().getString(R.string.scoreDeleted),
                    Toast.LENGTH_LONG).show();

            mdb.deleteScore(scoreToDelete);

            finish();
            startActivity(getIntent());

        }

        return true;
    }

}
