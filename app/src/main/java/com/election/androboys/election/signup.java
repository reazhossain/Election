package com.election.androboys.election;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//for http client
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class signup extends AppCompatActivity {

    EditText nid;
    EditText birth;
    Spinner district_spinner;
    Spinner municipility_spinner;

    TextView btn_enter;
    TextView btn_signup;

    String district,muni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initializeAll();
        district_method();
        municipility_method();



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = nid.getText().toString();
             //// String num = Integer.parseInt(nid.getText().toString());
                String num1 = birth.getText().toString();

                new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=register&nid=" + num + "&birthday=" + num1+"&muni=" + muni+"&dis=" + district);
            }
        });

    }

    //for initialization

    private void initializeAll(){
        nid= (EditText) findViewById(R.id.txt_nid);
        birth=(EditText) findViewById(R.id.txt_birth);

        district_spinner= (Spinner) findViewById(R.id.district_spinner);
        municipility_spinner= (Spinner) findViewById(R.id.municipility_spinner);


        btn_enter= (TextView) findViewById(R.id.btn_enter);
        btn_signup=(TextView) findViewById(R.id.btn_signup);
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
            ////Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder=new AlertDialog.Builder(signup.this);
            builder.setTitle("তথ্য!");



      if(result.matches("(.*)Registration Successfull(.*)")) {
//                Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
          builder.setMessage("নিবন্ধনটি সফল হয়েছে ।");
            }

            else {
          builder.setMessage("নিবন্ধনটি সম্পন্ন হয়নি ।");

//                Toast.makeText(getBaseContext(), "Problem In Registration", Toast.LENGTH_SHORT).show();
            }
            builder.setNegativeButton("Ok", null);
            builder.create();
            builder.show();


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


    //for district
    private void district_method(){
        String[] district_item= new String[]{"ঢাকা"};
        ArrayAdapter<String> district_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,district_item);
        district_spinner.setAdapter(district_Adapter);
        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       final int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


    //for district
    private void municipility_method(){
        String[] municipility_item= new String[]{"- - - - - -","সাভার","দোহার"};
        ArrayAdapter<String> municipility_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,municipility_item);
        municipility_spinner.setAdapter(municipility_Adapter);
        municipility_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       final int position, long id) {

                if (position==1){
                    muni="savar";
                    district="Dhaka";
                }
                else if(position==2){
                    muni="dohar";
                    district="Dhaka";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


}