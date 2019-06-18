package com.example.studymate;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;


import static android.content.ContentValues.TAG;

public class Image extends Activity {
    ImageView imageView;
    byte []bit;
    Bitmap bitmap;
    Boolean isImageFitToScreen;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView=findViewById(R.id.imageView3);
        Bundle basket=getIntent().getExtras();
        if (basket != null) {
            bit=basket.getByteArray("image");
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        bitmap=BitmapFactory.decodeByteArray(bit,0,bit.length);

        Log.e(TAG, "orig img size " + options.outWidth + "x" +
                options.outHeight);

//        ByteArrayInputStream inputStream = new ByteArrayInputStream(bit);
//        bitmap=BitmapFactory.decodeStream(inputStream);
       // bitmap= BitmapFactory.decodeStream(inputStream);

        imageView.setImageBitmap(bitmap);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if(isImageFitToScreen) {
////                    isImageFitToScreen=false;
////                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
////                    imageView.setAdjustViewBounds(true);
////                }else{
//                    isImageFitToScreen=true;
//                    imageView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
//                    //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
////                }
//            }
//        });
    }
}
