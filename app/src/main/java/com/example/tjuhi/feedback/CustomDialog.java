package com.example.tjuhi.feedback;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tjuhi on 6/1/2017.
 */

public class CustomDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public RatingBar mRate;
    public EditText mComment;
    public static final String ROOT_URL = "http://ric-tiiciiitm.webhostingforstudents.com/";
    public Button mDone,mCancel;
    public CustomDialog(Activity a){
  super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        mRate=(RatingBar)findViewById(R.id.rate) ;
        mComment=(EditText)findViewById(R.id.feed);
        mDone=(Button)findViewById(R.id.done);
        mCancel=(Button)findViewById(R.id.cancel);
        mDone.setOnClickListener(this);
        mCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.done:

                insertUser();
                dismiss();
                break;
            case R.id.cancel:
                dismiss();

        }

    }
    private void insertUser(){
        RestAdapter adapter=new RestAdapter.Builder()
                .setEndpoint(ROOT_URL).build();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");

        String strDate = mdformat.format(calendar.getTime());
        Toast.makeText(getContext(),strDate,Toast.LENGTH_LONG).show();
        InsertAPI api=adapter.create(InsertAPI.class);
        api.insertUser(
                ProductMain.s,
                "Anonymous",
                mRate.getRating(),
                mComment.getText().toString(),
                strDate
                ,

                new Callback<Response>() {

                    @Override
                    public void success(Response result, Response response) {
                        BufferedReader reader = null;
                        String output = "";
                        try{
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(), output, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
