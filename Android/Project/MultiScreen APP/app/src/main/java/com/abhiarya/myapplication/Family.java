package com.abhiarya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {
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
        setContentView(R.layout.activity_family);
        ArrayList<Words> word = new ArrayList<>();

        word.add(new Words(getResources().getString(R.string.daug), "beti",R.drawable.family_daughter,R.raw.family_daughter));
        word.add(new Words(getResources().getString(R.string.father), "pita",R.drawable.family_father,R.raw.family_father));
        word.add(new Words(getResources().getString(R.string.gfather), "dada",R.drawable.family_grandfather,R.raw.family_grandfather));
        word.add(new Words(getResources().getString(R.string.grandmo), "dadi",R.drawable.family_grandmother,R.raw.family_grandmother));
        word.add(new Words(getResources().getString(R.string.mother), "maa",R.drawable.family_mother,R.raw.family_mother));
        word.add(new Words(getResources().getString(R.string.oldB), "bada bhai",R.drawable.family_older_brother,R.raw.family_older_brother));
        word.add(new Words(getResources().getString(R.string.oldersister), "badi behen",R.drawable.family_older_sister,R.raw.family_older_sister));
        word.add(new Words(getResources().getString(R.string.son), "beta",R.drawable.family_son,R.raw.family_son));
        word.add(new Words(getResources().getString(R.string.yb), "chhota bhai",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        word.add(new Words(getResources().getString(R.string.ysis), "chhoti behen",R.drawable.family_younger_sister,R.raw.family_younger_sister));



        WordAdapter itemsAdapter = new WordAdapter(this, word,R.color.greenist);
        ListView listView =findViewById(R.id.famroot);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
                Words myword = word.get(position);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result ==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(Family.this,myword.getAud());
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