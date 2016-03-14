package com.election.androboys.election;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class candidate_Ex extends AppCompatActivity {



    TextView txt_result_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate__ex);


        txt_result_show=(TextView) findViewById(R.id.txt_result);


        String value;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("name");
            String base;
            try {
                base = URLEncoder.encode(value, "UTF-8");
                new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=CandidateData&name=" + base );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //////txt_result_show.setText(value);

           //// new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=CandidateData&name=1");
        }



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

               txt_result_show.setText(result);

            //////Toast.makeText(getBaseContext(), "" + result, Toast.LENGTH_LONG).show();
        }
    }

    ////////////////////Nothing to edit below
    public String GET(String url){
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

    private  String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
       

        while((line = bufferedReader.readLine()) != null) {
            result = result + "\n" + line;

        }

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