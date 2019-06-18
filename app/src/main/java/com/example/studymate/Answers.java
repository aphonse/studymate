package com.example.studymate;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studymate.DbConnectors.DbConn;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;


public class Answers extends Activity implements View.OnClickListener {
    static Answers INSTANCE;
    EditText text,commentEditText;
    ImageView imageView;
    Cursor res1;
    TextView tv,ans,tv2,commentTextView;
    ArrayList<String> arry=new ArrayList<>();
    Button b,commentButton;
    byte[] by;
    Bitmap bitmap;
    String Comments;
    private static String question;
    ArrayList<String> a=new ArrayList<>();
    ArrayList<String> commentArray=new ArrayList<>();
    private static String answer;
    boolean isImageFitToScreen;
    private DbConn db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
        this.setTitle("Answers");
        setContentView(R.layout.answer);
       db=new DbConn(Answers.this);
       commentButton=findViewById(R.id.buttonComment);
       CardView cardView=findViewById(R.id.cardViewAnswe);
       commentEditText=findViewById(R.id.editTextComment);
       commentTextView=findViewById(R.id.textViewComment);
        tv=findViewById(R.id.section_label);
        imageView =findViewById(R.id.imageView2);
        text=findViewById(R.id.editTextAnswer);
        tv2=findViewById(R.id.textView3);
        b=findViewById(R.id.answersearch);
        ans=findViewById(R.id.textView2);
        Bundle basket=getIntent().getExtras();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Answers.this,Main.class);
//                startActivity(intent);
//            }
//        });

        if (basket != null) {
            question=basket.getString("question");
            by=basket.getByteArray("image");
            if (by!=null){
                ByteArrayInputStream inputStream = new ByteArrayInputStream(by);
                bitmap= BitmapFactory.decodeStream(inputStream);
            }
        }
        res1=db.getAnswer(question);
        while (res1.moveToNext()){
            String answers=res1.getString(2);
            a.add(answers);
//            ans.setText(answers);
            Log.d("hello world", "getAnswer2: "+a);
        }

        String an = "This question has no answer yet";
        String an1=null;
        if(!a.isEmpty()){
            an = a.get(0);
            if (a.size()>1){
                an1 = a.get(1);
            }
        }
        Log.d("hello world", "array: "+an);
        ans.setText(an);
        tv2.setText(an1);
        if (question!=null){
            String caps=question.substring(0,1).toUpperCase() + question.substring(1);
            tv.setText(caps);
        }
        if(bitmap!=null){
            imageView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putByteArray("image",by);
                    Intent intent=new Intent(Answers.this,Image.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });
        }
        b.setOnClickListener(this);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comments=commentEditText.getText().toString();
                commentArray.add(Comments);
                commentTextView.append(Comments);
                commentTextView.append("\n");
//                for (int i=0;i<commentArray.size();i++){
//                    commentTextView.append(commentArray.get(i));
//                    commentTextView.append("\n");
//                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        answer=text.getText().toString();
//        boolean set=db.setAnswer(question,answer);
        boolean set=db.InsertAns(answer,question);
        if(set){
            Toast.makeText(this,"answer uploaded",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"answer not uploaded",Toast.LENGTH_LONG).show();

        }
    }
    public static Answers getINSTANCE(){
        return INSTANCE;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}
