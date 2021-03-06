package com.example.ridley.memorygameesgi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ManageNicknameActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "prefNickname";
    String nickname;
    EditText editNick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_nickname);

        editNick=(EditText) findViewById(R.id.editNickname);

        editNick.setTextColor(Color.WHITE);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String login=settings.getString("nickname", null);

        if ( login!=null)
        {
            editNick.setText(login);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_manage_nickname, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.backHome:
                this.finish();
                return  true;

            case R.id.actionScores:
                Intent intent= new Intent(ManageNicknameActivity.this, HistoryActivity.class);
                startActivity(intent);
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);


        }




    }

    public  void  submitNickname(View v){
         nickname= editNick.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences("prefNickname", MODE_PRIVATE).edit();
        editor.putString("nickname", nickname);
        editor.apply();


        Intent intent =new Intent(ManageNicknameActivity.this,HomeActivity.class);
        startActivity(intent);
    }


}
