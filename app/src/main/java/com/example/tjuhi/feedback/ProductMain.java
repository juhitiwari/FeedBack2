package com.example.tjuhi.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tjuhi on 6/2/2017.
 */

public class ProductMain extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<Feedback> data;
    private ProductAdapter mProductAdapter;
    private int mPosition = RecyclerView.NO_POSITION;
    public static String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

    }
    private void initViews(){

        loadJSON();
    }
    private void loadJSON(){
        try{
            ProductInterface service=ApiClient.getRetrofit().create(ProductInterface.class);
            Call<List<Product>> call=service.getProduct();
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    final List<Product> productList=response.body();
                    mRecyclerView=(RecyclerView)findViewById(R.id.squawks_recycler_view);
                    mRecyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mProductAdapter=new ProductAdapter(productList);
                    mRecyclerView.setAdapter(mProductAdapter);
                   mProductAdapter.SetOnItemClickListener(new ProductAdapter.FeedbackAdapterOnClickHandler() {
                        @Override
                        public void onClick(int rate) {

                            Intent intent=new Intent(ProductMain.this,MainActivity.class);
                            s=productList.get(rate).getmProductID();
                            Toast.makeText(ProductMain.this,s,Toast.LENGTH_LONG).show();
                            startActivity(intent);

                        }
                    });


                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Toast.makeText(ProductMain.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

