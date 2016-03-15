package com.election.androboys.election;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ballot extends AppCompatActivity {

    Button b1,b2,b3,b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ballot);



        b1 = (Button)findViewById(R.id.buttonreRezaul);
        b2 = (Button)findViewById(R.id.buttonMannan);
        b3 = (Button)findViewById(R.id.buttonKalu);
        b4 = (Button)findViewById(R.id.buttonShaka);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = 1;
                String value;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    value = extras.getString("nid");

                    new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=vote&votefor=" + num + "&nid=" + value + "");
                }



            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = 2;
                String value;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    value = extras.getString("nid");

                    new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=vote&votefor=" + num + "&nid=" + value + "");
                }



            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = 3;
                String value;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    value = extras.getString("nid");

                    new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=vote&votefor=" + num + "&nid=" + value + "");
                }



            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = 4;
                String value;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    value = extras.getString("nid");

                    new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=vote&votefor=" + num + "&nid=" + value + "");
                }



            }
        });



    }




    // for database connection
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

//            Toast.makeText(getBaseContext(), ""+result, Toast.LENGTH_LONG).show();
//
            AlertDialog.Builder builder=new AlertDialog.Builder(Ballot.this);
            builder.setTitle("তথ্য!");
            builder.setMessage(result);
            builder.setNegativeButton("Ok", null);
            builder.create();
            builder.show();

            if(result.matches("(.*)Login Successfull(.*)")) {


            }
            else {
               /// Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
            }




            ///////////////
            ////////login result
//            if(result=="ok")
//            {
//                Toast.makeText(getBaseContext(), "Login sucessfull", Toast.LENGTH_SHORT).show();
//
//            }
//            else {
//                Toast.makeText(getBaseContext(), "Login Failed" + result, Toast.LENGTH_SHORT).show();
//            }

            ////// nid.setText(result);

            ///result = result.replace("<br>", "\n");
//            result_set.setText(result);
//            etResponse.setText("\n\n\n\n"+ result);
        }
    }

    ////////////////////Nothing to edit below
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    //database work end




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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