package com.election.androboys.election;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class Election extends AppCompatActivity {

    TextView btn_candidate;
    TextView btn_polling_station;
    TextView btn_vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        initializeAll();
        btn_candidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Election.this, candidate.class);
                startActivity(i);
            }
        });
        btn_polling_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Election.this,PollingStation.class);
                startActivity(i);
            }
        });
        btn_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Election.this,Login.class);
                startActivity(i);
            }
        });
    }


    public void initializeAll(){
        btn_candidate= (TextView) findViewById(R.id.btn_candidate);
        btn_polling_station= (TextView) findViewById(R.id.menu_polling);
        btn_vote= (TextView) findViewById(R.id.btn_vote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_election, menu);
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
