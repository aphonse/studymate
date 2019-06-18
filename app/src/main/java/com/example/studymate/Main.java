package com.example.studymate;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.studymate.DbConnectors.DbConn;

import java.util.ArrayList;


public class Main extends AppCompatActivity {

    private static final String TAG = "Main";
    //vars
    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<byte[]> image=new ArrayList<byte[]>();
    DbConn dab;
    TextView tv;
    String topc;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclermain);
        Log.d(TAG, "onCreate: Reached answers");
        this.setTitle("Questions");
//        gestures

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Main.this, "ive beeen clicked", Toast.LENGTH_SHORT).show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent=new Intent(Main.this,MainActivity.class);
                startActivity(intent);


            }
        });


        dab=new DbConn(this);
        tv=findViewById(R.id.image_name);
        Bundle basket=getIntent().getExtras();
        if (basket != null) {
            topc=basket.getString("topic");
//            Log.d("topic logged",topc);
        }

        res = dab.getData();
//        dab.setTopic(topc);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

//        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
//        mNames.add("Havasu Falls");

        while (res.moveToNext()) {
            String text1 = res.getString(2);
            byte[] bitmap=res.getBlob(3);
            mNames.add(text1);
            image.add(bitmap);
        }
//        String text2="bio bio";
//        mNames.add(text2);
        initRecyclerView();

    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames,image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void start(){
        Intent intent=new Intent(getBaseContext(),Answers.class);
        startActivity(intent);
    }


}













