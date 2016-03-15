package com.election.androboys.election;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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

ImageView profilepic;

    ImageView markapic;


    TextView txt_result_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate__ex);

        profilepic = (ImageView)findViewById(R.id.img1);
        markapic = (ImageView)findViewById(R.id.img2);

        txt_result_show=(TextView) findViewById(R.id.txt_result);


        String value;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("name");


if(value.equals("মির্জা রেজাউল করিম(দুলাল)"))
{
    profilepic.setImageResource(R.drawable.mirza);
    markapic.setImageResource(R.drawable.jug);
}

else  if(value.equals("প্রফেসর আব্দুল মান্নান"))
{
    profilepic.setImageResource(R.drawable.mannan);
    markapic.setImageResource(R.drawable.mobile);
}

else  if(value.equals("আব্দুর রহিম কালু"))
{
    profilepic.setImageResource(R.drawable.kalu);
    markapic.setImageResource(R.drawable.bnp);
}
else  if(value.equals("এ্যাডভোকেট সাখায়াত হোসেন(সাখো)"))
{
    profilepic.setImageResource(R.drawable.shaku);
    markapic.setImageResource(R.drawable.lig);
}
else  if(value.equals("মোঃ আবুল বাশার"))
{
    profilepic.setImageResource(R.drawable.bashar);
    markapic.setImageResource(R.drawable.mosal);
}
else  if(value.equals("মোঃ নাজিম উদ্দিন মিয়া"))
{
    profilepic.setImageResource(R.drawable.najim);
    markapic.setImageResource(R.drawable.pakhi);
}
else  if(value.equals("মোঃ রেজাউল করিম "))
{
    profilepic.setImageResource(R.drawable.karim);
    markapic.setImageResource(R.drawable.lamp);
}
else  if(value.equals("মোছাঃ কামরুন্নাহার "))
{
    profilepic.setImageResource(R.drawable.nahar);
    markapic.setImageResource(R.drawable.scissor);
}



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