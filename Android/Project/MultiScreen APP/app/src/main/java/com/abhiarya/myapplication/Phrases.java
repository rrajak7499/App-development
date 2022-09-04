package com.abhiarya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        ArrayList<Words> word = new ArrayList<>();

        word.add(new Words(getResources().getString(R.string.hello), "namaste",R.raw.phrase_are_you_coming));
        word.add(new Words(getResources().getString(R.string.how),"app kese hai",R.raw.phrase_how_are_you_feeling));
        word.add(new Words(getResources().getString(R.string.who),"aap kon hai",R.raw.phrase_come_here));
        word.add(new Words(getResources().getString(R.string.look),"aap mast lag rahe ho",R.raw.phrase_im_coming));
        word.add(new Words(getResources().getString(R.string.good),"mai acha hu",R.raw.phrase_im_feeling_good));
        word.add(new Words(getResources().getString(R.string.where),"kaha jaye",R.raw.phrase_lets_go));
        word.add(new Words(getResources().getString(R.string.idnt),"mujhe nhi pata",R.raw.phrase_my_name_is));
        word.add(new Words(getResources().getString(R.string.nice),"acha vyakti",R.raw.phrase_what_is_your_name));
        word.add(new Words(getResources().getString(R.string.thisi),"yeh payara hai",R.raw.phrase_where_are_you_going));
        word.add(new Words(getResources().getString(R.string.chla),"chla ja bsdk",R.raw.phrase_yes_im_coming));



        WordAdapter itemsAdapter = new WordAdapter(this, word,R.color.blueist);
        ListView listView =findViewById(R.id.phroot);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Words myword = word.get(position);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result ==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(Phrases.this,myword.getAud());
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