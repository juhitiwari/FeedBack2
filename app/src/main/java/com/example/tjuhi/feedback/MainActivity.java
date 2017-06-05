package com.example.tjuhi.feedback;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity{
private RecyclerView mRecyclerView;
    private ArrayList<Feedback> data;
    private FeedbackAdapter mFeedbackAdapter;
    private int mPosition = RecyclerView.NO_POSITION;
   String Joutput;
    public static int in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        initViews();

    }
private void initViews(){

    loadJSON();
}
    private void loadJSON(){
        try {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint("http://ric-tiiciiitm.webhostingforstudents.com/").build();
            WhereInterface api = adapter.create(WhereInterface.class);
            api.where(ProductMain.s
                    , new retrofit.Callback<retrofit.client.Response>() {
                        @Override
                        public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                            BufferedReader reader = null;
                            String output = "";
                            try {
                                reader = new BufferedReader(new InputStreamReader(response.getBody().in()));

                                //Reading the output in the string
                                output = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
Joutput=output;

                            List<Feedback> feedbacks=new ArrayList<>();
                            try{

                                JSONArray Array=new JSONArray(Joutput);
                              // in=Array.length();
                                for (int i = 0; i < Array.length(); i++) {


                                    JSONObject current = Array.getJSONObject(i);


                                    String user = current.getString("user");


                                    String rating=current.getString("rating");

                                    String comment = current.getString("comment");
                                    String date=current.getString("date");

                                    Feedback feedback = new Feedback(user,rating,comment,date);

                                    feedbacks.add(feedback);
                                    mRecyclerView=(RecyclerView)findViewById(R.id.squawks_recycler_view);
                                    mRecyclerView.setHasFixedSize(true);
                                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                                    mRecyclerView.setLayoutManager(layoutManager);
                                    mFeedbackAdapter=new FeedbackAdapter(feedbacks);
                                    mRecyclerView.setAdapter(mFeedbackAdapter);
                                }
                            }
                            catch (JSONException e){
                                Log.e("QueryUtils", "Problem parsing the JSON results", e);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            // Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    });
        }
        catch (Exception e){

        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            CustomDialog cd=new CustomDialog(MainActivity.this);
            cd.show();

        }

        return super.onOptionsItemSelected(item);
    }


}
