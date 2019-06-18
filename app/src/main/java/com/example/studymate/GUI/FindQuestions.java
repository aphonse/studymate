package com.example.studymate.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.studymate.DbConnectors.DbConn;
import com.example.studymate.Main;
import com.example.studymate.R;

import static android.content.ContentValues.TAG;

public class FindQuestions extends Fragment implements View.OnClickListener {

    EditText topics;
    static FindQuestions INSTANCE;
    Button searchbtn;
    public String topic1;
    DbConn dbConn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        INSTANCE=this;
        View rootView = inflater.inflate(R.layout.find, container, false);
        dbConn = new DbConn(getActivity());
        topics = rootView.findViewById(R.id.topicsearch);
        searchbtn = rootView.findViewById(R.id.search);
        searchbtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:

                if (topics.getText().toString().trim().length()>0){
                    topic1 = topics.getText().toString();
//               dbConn.topic=topic1;
                    Bundle basket = new Bundle();
                    Log.d(TAG, "onClick: Clicked");
                    basket.putString("topic", topic1);
                    Intent intent = new Intent(getContext(), Main.class);
                    intent.putExtras(basket);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Enter a Topic!",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public static FindQuestions getINSTANCE(){
        return INSTANCE;
    }
    public String getTopic1(){
        return topic1;
    }


//    private void startService(String topic1) {
//        Bundle basket=new Bundle();
//        basket.putString("topic",topic1);
//        Intent myIntent = new Intent(getContext(),Data.class);
//        myIntent.putExtras(basket);
//        startService(myIntent);
//    }
}