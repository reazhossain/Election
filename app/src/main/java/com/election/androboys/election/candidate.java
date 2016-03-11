package com.election.androboys.election;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//for http client
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//for http client


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class candidate extends AppCompatActivity {

    /////////////////Limit 100ta array value, change korle pb nai
    String[] result2;

    int ary;  /////array variable
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
    TextView txt_result_show;
    ImageView btn_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);
        initializeAll();
        division_method();
        district_method();
        municipility_method();
        searching();

        result2= new String[100];

        search_box.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

    }

    public void Reaz()
    {

            /// TextView t1 = (TextView) findViewById(R.id.t1);
            ArrayList results = new ArrayList();

            for (int i = 1; i <= ary; i++) {

               //// txt_result_show.setText(result2[i]);

                results.add(result2[i]);
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, results);
            ListView listView = (ListView) findViewById(R.id.mobile_list);
           listView.setAdapter(adapter);


            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String food = String.valueOf(parent.getItemAtPosition(position));

                            Intent i = new Intent(getApplicationContext(), candidate_Ex.class);
                            i.putExtra("name",""+food);
                            startActivity(i);




                            Toast.makeText(candidate.this, food, Toast.LENGTH_LONG).show();
                        }
                    }
            );




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
                            ///// new HttpAsyncTask().execute("http://chat.alaponbd.com/sm/android/url2.php?url=candidate");

                            new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=candidatelist&muni="+municipility);

                        }
                        else if (pos == 2) {


                            municipility="dohar";
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




//for initialization

    private void initializeAll(){
        division_spinner= (Spinner) findViewById(R.id.divison_spinner);
        district_spinner= (Spinner) findViewById(R.id.district_spinner);
        municipility_spinner= (Spinner) findViewById(R.id.municipility_spinner);

        search_box= (EditText) findViewById(R.id.txt_search);
        btn_find=(TextView) findViewById(R.id.btn_find);

        txt_result_show=(TextView) findViewById(R.id.txt_result_show);
        btn_search= (ImageView) findViewById(R.id.btn_search);

    }

//    for search

    private void searching(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            String      str;
            @Override
            public void onClick(View v) {
                str=search_box.getText().toString();
                new HttpAsyncTask().execute("http://seekingsoft.com/android/election/url.php?action=candidate&searchName="+str);

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



            ///txt_result_show.setText(result2[2]);


          Reaz();

            ////Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            ////txt_result_show.setText(result);
            /////result = result.replace("<br>", "\n");
            ////result_set.setText(result);
//            etResponse.setText("\n\n\n\n"+ result);
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

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        ary =0;

        while((line = bufferedReader.readLine()) != null) {
            result = result + "\n" + line;

                        /////Important : porita line k akta array te nia rakhbe
            ary = ary + 1;
            ///TextView t1 = (TextView) findViewById(R.id.t1);
            // /t1.setText(line);
            result2[ary] = line;
        }
        inputStream.close();
        return result;
    }

    //database work end


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_candidate, menu);
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
