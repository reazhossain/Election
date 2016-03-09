package com.election.androboys.election;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//for http client
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//for http client

public class Choose_candi extends AppCompatActivity {


    //    for database
    String query = "";
    String url ="";
    String data="";
    String url2 ="";





    Spinner division_spinner;
    Spinner district_spinner;
    Spinner municipility_spinner;


    EditText search_box;
    TextView btn_find;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_candi);


        initializeAll();
        division_method();
        district_method();
        municipility_method();




    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_candi, menu);
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




    private void initializeAll(){
        division_spinner= (Spinner) findViewById(R.id.divison_spinner);
        district_spinner= (Spinner) findViewById(R.id.district_spinner);
        municipility_spinner= (Spinner) findViewById(R.id.municipility_spinner);

        search_box= (EditText) findViewById(R.id.txt_search);
        btn_find=(TextView) findViewById(R.id.btn_find);



    }








    //    For division Spinner work
    private void division_method(){
        String[] division_item= new String[]{"ঢাকা"};
        ArrayAdapter<String> division_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,division_item);
        division_spinner.setAdapter(division_Adapter);

        division_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                // Information not found
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
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

                btn_find.setOnClickListener(new View.OnClickListener() {
                    int pos=position;
                    @Override
                    public void onClick(View v) {
                        if (pos == 1) {


                            ///new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=canditatelist");

                            ////new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=candidate");
                        }
                        if (pos == 2) {




                            //////new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=canditatelist");

                            ////// new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=canditatelist");

                        }

                    }
                });
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

                btn_find.setOnClickListener(new View.OnClickListener() {
                    int pos=position;
                    String municipility;
                    String dis;

                    String type;
                    @Override
                    public void onClick(View v) {
                        if (pos == 1) {
                            municipility="savar";
                            dis="Dhaka";
                            ///// new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=candidate");

                            new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=candidatelist&muni="+municipility);

                        }
                        else if (pos == 2) {


                            municipility="Bheramara";
                            dis="Kushtia";


                            new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=candidatelist&muni="+municipility);

                            ///////new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=canditatelist");

                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }









    //    for search




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
            result = result +"\n" + line;

        inputStream.close();
        return result;
    }

    //database work end

}
