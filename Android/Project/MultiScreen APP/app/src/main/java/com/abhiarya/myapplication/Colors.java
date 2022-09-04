package com.abhiarya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|| focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else  if (focusChange== AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if (focusChange== AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private  MediaPlayer.OnCompletionListener onCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);



        ArrayList<Words> color = new ArrayList<>();

        color.add(new Words(getResources().getString(R.string.black), "kala", R.drawable.color_black,R.raw.color_black));
        color.add(new Words(getResources().getString(R.string.brown), "bhura", R.drawable.color_brown,R.raw.color_brown));
        color.add(new Words(getResources().getString(R.string.dusty), "yellow",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        color.add(new Words(getResources().getString(R.string.gray), "grey",R.drawable.color_gray,R.raw.color_gray));
        color.add(new Words(getResources().getString(R.string.green), "hra",R.drawable.color_green,R.raw.color_green));
        color.add(new Words(getResources().getString(R.string.musterd), "yellow",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        color.add(new Words(getResources().getString(R.string.red), "lal",R.drawable.color_red,R.raw.color_red));
        color.add(new Words(getResources().getString(R.string.white), "safed",R.drawable.color_white,R.raw.color_white));



        WordAdapter itemsAdapter = new WordAdapter(this, color,R.color.purpulist);
        ListView listView =findViewById(R.id.colroot);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Words myword = color.get(position);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result ==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(Colors.this,myword.getAud());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
    }
    @Override
    protected  void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }



    private  void  releaseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }

    }
}