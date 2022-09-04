package com.abhiarya.tictactoy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    int isFirst;
    HashSet<Integer> first;
    HashSet<Integer> second;
    ArrayList<Button> btns;
    boolean isWinner;
    boolean isDraw;
    boolean isAutoPlay;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFirst = 1;
        isWinner = false;
        isDraw = false;
        isAutoPlay = false;
        first = new HashSet<>();
        second = new HashSet<>();
        btns = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        btns.add(button1);btns.add(button2);btns.add(button3);btns.add(button4);btns.add(button5);btns.add(button6);btns.add(button7);btns.add(button8);btns.add(button9);
        button1.setOnClickListener(view -> {onClick(button1,1);});
        button2.setOnClickListener(view -> {onClick(button2,2);});
        button3.setOnClickListener(view -> {onClick(button3,3);});
        button4.setOnClickListener(view -> {onClick(button4,4);});
        button5.setOnClickListener(view -> {onClick(button5,5);});
        button6.setOnClickListener(view -> {onClick(button6,6);});
        button7.setOnClickListener(view -> {onClick(button7,7);});
        button8.setOnClickListener(view -> {onClick(button8,8);});
        button9.setOnClickListener(view -> {onClick(button9,9);});
        button10.setOnClickListener(view -> {autoPlay();});
        button11.setOnClickListener(view -> {reset();});
    }

    private void autoPlay() {
        reset();
        if(isAutoPlay) {
            button10.setText("auto play  on");
            button10.setBackgroundColor(Color.BLUE);
            isAutoPlay = false;
        }
        else {
            button10.setText("auto play off");
            button10.setBackgroundColor(Color.RED);
            isAutoPlay = true;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void reset(){
        button1.setText(" ");
        button2.setText(" ");
        button3.setText(" ");
        button4.setText(" ");
        button5.setText(" ");
        button6.setText(" ");
        button7.setText(" ");
        button8.setText(" ");
        button9.setText(" ");
        button1.setBackgroundColor(Color.BLUE);
        button2.setBackgroundColor(Color.BLUE);
        button3.setBackgroundColor(Color.BLUE);
        button4.setBackgroundColor(Color.BLUE);
        button5.setBackgroundColor(Color.BLUE);
        button6.setBackgroundColor(Color.BLUE);
        button7.setBackgroundColor(Color.BLUE);
        button8.setBackgroundColor(Color.BLUE);
        button9.setBackgroundColor(Color.BLUE);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);
        isFirst = 1;
        isWinner = false;
        isDraw = false;
        first.clear();
        second.clear();
    }
    @SuppressLint("ResourceAsColor")
    private void onClick(Button button, int btnNo){

        if(isWinner){
                if (isFirst%2 != 0)
                    Toast.makeText(this, "Winner is (O) Please Reset", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Winner is (X) Please Reset", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(isDraw){
            Toast.makeText(this, "Draw match Please Reset", Toast.LENGTH_SHORT).show();
            return;
        }

        button.setBackgroundColor(Color.RED);
        if(isFirst%2==1 || isAutoPlay) {
            button.setText("X");
            first.add(btnNo);
            if(isAutoPlay){
                if(first.size()+second.size()<9)
                    autoPlayFunctionality();
                winner();
                return;
            }
        }
        else {
            button.setText("O");
            second.add(btnNo);
        }
        button.setEnabled(false);
        isFirst++;
        winner();
    }

    private void autoPlayFunctionality(){
        Object obj = new Object();
        int buttonNo = -1;
        while (buttonNo == -1 || first.contains(buttonNo)||second.contains(buttonNo))
            buttonNo = (int)(Math.random()*(9-1+1)+1);
        btns.get(buttonNo - 1).setBackgroundColor(Color.RED);
        btns.get(buttonNo - 1).setText("O");
        btns.get(buttonNo-1).setEnabled(false);
        isFirst++;
        second.add(buttonNo);
    }

    private void winner() {
        if ((first.contains(1) && first.contains(2) && first.contains(3)) || (first.contains(4) && first.contains(5) && first.contains(6)) || (first.contains(7) && first.contains(8) && first.contains(9)) || (first.contains(1) && first.contains(4) && first.contains(7)) || (first.contains(2) && first.contains(5) && first.contains(8)) || (first.contains(3) && first.contains(6) && first.contains(9)) || (first.contains(1) && first.contains(5) && first.contains(9)) || (first.contains(3) && first.contains(5) && first.contains(7))){
            Toast.makeText(this, "First Player Winner (X)", Toast.LENGTH_SHORT).show();
            isWinner = true;
        }
        else if((second.contains(1) && second.contains(2) && second.contains(3)) || (second.contains(4) && second.contains(5) && second.contains(6)) || (second.contains(7) && second.contains(8) && second.contains(9)) || (second.contains(1) && second.contains(4) && second.contains(7)) || (second.contains(2) && second.contains(5) && second.contains(8)) || (second.contains(3) && second.contains(6) && second.contains(9)) || (second.contains(1) && second.contains(5) && second.contains(9)) || (second.contains(3) && second.contains(5) && second.contains(7))){
            Toast.makeText(this, "Second Player Winner (O)", Toast.LENGTH_SHORT).show();
            isWinner = true;
        }
        else if((first.size()+second.size())==9){
            Toast.makeText(this, "Match Draw", Toast.LENGTH_SHORT).show();
            isDraw = true;
        }
    }
}