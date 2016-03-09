package com.election.androboys.election;

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
//for http client

public class Login extends AppCompatActivity {

    String query = "";
    String url ="";
    String data="";
    String url2 ="";

    int Nid_value;
    String getbd_value;

    EditText nid;
    EditText birth;
    TextView btn_enter;
    TextView btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeAll();

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int num= Integer.parseInt(nid.getText().toString());

                String num1=birth.getText().toString();


                new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=login&nid="+num+"&birthday="+num1+"");

            }
        });




//      btn_enter.setOnClickListener(new View.OnClickListener() {
//           @Override
//
//            //made by tanjil
//
//            public void onClick(View v) {
//
//                new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=login&nid=Nid_value&birthday=getbd_value");
//            }
//        });




    }
    //for initialization

    private void initializeAll(){
        nid= (EditText) findViewById(R.id.txt_nid);
        birth=(EditText) findViewById(R.id.txt_birth);
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

            if(result.matches("(.*)Login Successfull(.*)")) {

                Intent i=new Intent(Login.this,Choose_candi.class);
                startActivity(i);

            }
            else if(result.matches("(.*)Login Failed(.*)")) {
                Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            }

            else if(result.matches("(.*)Registration Successfull(.*)")) {
                Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(getBaseContext(), "Problem In Registration", Toast.LENGTH_SHORT).show();
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