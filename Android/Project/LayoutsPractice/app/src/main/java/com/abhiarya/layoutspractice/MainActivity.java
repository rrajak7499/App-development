package com.abhiarya.layoutspractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {
    }

    public void cancle(View view) {
    }

    private class sharedRef{
        SharedPreferences shareref;
        public  sharedRef(Context context){
            shareref = context.getSharedPreferences("myref",Context.MODE_PRIVATE);
        }
        public  void  save(String username,String password){
            SharedPreferences.Editor s = shareref.edit();
            s.putString("username",username);
            s.putString("pass", password);
            s.commit();
        }

    }
}