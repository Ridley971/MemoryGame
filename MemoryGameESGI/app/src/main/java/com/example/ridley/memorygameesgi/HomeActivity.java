package com.example.ridley.memorygameesgi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends ActionBarActivity {
    public static final String PREFS_NAME = "prefNickname";

    TextView textWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textWelcome=(TextView)findViewById(R.id.txtViewWelcome);

        SharedPreferences pref =getSharedPreferences(PREFS_NAME,MODE_APPEND);
        String nickname =pref.getString("nickname",null);

        String strNicknameFormat= getResources().getString(R.string.WelcomeUser);
        String strNicknamemsg=String.format(strNicknameFormat,nickname);

         textWelcome.setText(strNicknamemsg);

    }

    public void btnManageNickname(View v){
        //getResources().getString(R.string.WelcomeUser,"Toto");
        //Toast.makeText(this, "toto", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(HomeActivity.this, ManageNicknameActivity.class);
        startActivity(intent);
    }

    public void btnResume(View v){
    }

    public void btnLastGames(View v)
    {
        Intent intent= new Intent(HomeActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
    public void btnNewGame(View v){
        Intent intent= new Intent(HomeActivity.this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
}
