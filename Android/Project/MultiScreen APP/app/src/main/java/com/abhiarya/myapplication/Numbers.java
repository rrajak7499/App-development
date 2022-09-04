package com.abhiarya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
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
        setContentView(R.layout.activity_numbers);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Words> word = new ArrayList<>();
         word.add(new Words(getResources().getString(R.string.one), "ek",R.drawable.number_one,R.raw.number_one));
         word.add(new Words(getResources().getString(R.string.two), "Do",R.drawable.number_two,R.raw.number_two));
         word.add(new Words(getResources().getString(R.string.three), "Tin",R.drawable.number_three,R.raw.number_three));
         word.add(new Words(getResources().getString(R.string.four), "char",R.drawable.number_four, R.raw.number_four));
         word.add(new Words(getResources().getString(R.string.five), "pach",R.drawable.number_five,R.raw.number_five));
         word.add(new Words(getResources().getString(R.string.six), "cheh",R.drawable.number_six,R.raw.number_six));
         word.add(new Words(getResources().getString(R.string.seven), "sat",R.drawable.number_seven,R.raw.number_seven));
         word.add(new Words(getResources().getString(R.string.eight), "aath",R.drawable.number_eight,R.raw.number_eight));
         word.add(new Words(getResources().getString(R.string.nine), "nou",R.drawable.number_nine,R.raw.number_nine));
         word.add(new Words(getResources().getString(R.string.ten), "das",R.drawable.number_ten,R.raw.number_ten));

//        LinearLayouw linearLayout= findViewById(R.id.rootview);
//        for (int i=0; i<words.size(); i++){
//            TextView textView= new TextView(this);
//            textView.setText(words.get(i));
//            linearLayout.addView(textView);
//        }

        WordAdapter itemsAdapter = new WordAdapter(this, word,R.color.yellowist);
        ListView listView =findViewById(R.id.numroot);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Words myword = word.get(position);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result ==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(Numbers.this,myword.getAud());
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