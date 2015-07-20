package com.example.ridley.memorygameesgi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;


public class HistoryActivity extends Activity implements SearchView.OnQueryTextListener {

    ListView listScores;
    SearchView mSearchView;
    TextView mStatusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_history);

        mStatusView=(TextView)findViewById(R.id.txtSearchView);
        listScores=(ListView) findViewById(R.id.listScores);

        ScoreDbHandler mdb= new ScoreDbHandler(this);
        ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

        lScore=mdb.getAllScores();

       listScores.setAdapter( new AdapterListScore(this,0,lScore));




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        /*getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;*/

       super.onCreateOptionsMenu(menu);

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_history,menu);
        MenuItem searchItem=menu.findItem(R.id.action_searchUserName);
        mSearchView=(SearchView)searchItem.getActionView();
        setupSearchView(searchItem);
        return true;
    }

    private void setupSearchView(MenuItem searchItem){
        /*if (isAlwaysExpanded()){
            mSearchView.setIconifiedByDefault(false);
        }else{
            searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM|
            MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }


        SearchManager searchManager=(SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager!=null){
            List searchables=searchManager.getSearchableInfo(getComponentName());

            for (SearchableInfo inf:searchables){
                if(inf.getSuggestAuthority()!=null
                    && inf.getSuggestAuthority().startsWith("applications")){

                }
            }
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.action_searchUserName:
                listScores=(ListView) findViewById(R.id.listScores);

                ScoreDbHandler mdb= new ScoreDbHandler(this);
                ArrayList<ScoreGames> lScore=new ArrayList<ScoreGames>();

                lScore=mdb.getAllScores();

                listScores.setAdapter( new AdapterListScore(this,0,lScore));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
