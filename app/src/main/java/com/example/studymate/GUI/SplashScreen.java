package com.example.studymate.GUI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.view.MotionEvent;
import android.widget.VideoView;

import com.example.studymate.MainActivity;
import com.example.studymate.R;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        VideoView videoHolder=findViewById(R.id.videoView2);
        try {
//            VideoView videoHolder = new VideoView(this);
//            setContentView(videoHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.presentation);
            videoHolder.setVideoURI(video);

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}