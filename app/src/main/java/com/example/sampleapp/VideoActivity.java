package com.example.sampleapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView vwVideo;
    Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vwVideo = (VideoView)findViewById(R.id.vwVideo);

        videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/sampleapp-43de6.appspot.com/o/SampleVideo.mp4?alt=media&token=42cbd49d-e54e-450e-abde-f0c39debbb72");
        vwVideo.setVideoURI(videoUri);
        vwVideo.requestFocus();
        vwVideo.start();
    }
}
