package com.abhiarya.basketballscorecounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int scoreOfA=0;
    int scoreOfB=0;
        public void A3(View view) {
            scoreOfA+=3;
            Toast toast = Toast.makeText(this,"+3 Point to Team A", Toast.LENGTH_SHORT);
            toast.show();
            checkStatus(scoreOfA,scoreOfB);
            disA(scoreOfA);
        }
        public void A2(View view) {
            scoreOfA+=2;
            Toast toast = Toast.makeText(this,"+2 Point to Team A", Toast.LENGTH_SHORT);
            toast.show();
            checkStatus(scoreOfA,scoreOfB);
            disA(scoreOfA);
        }
        public void Af(View view) {
            scoreOfA+=1;
            Toast toast = Toast.makeText(this,"+1 Point to Team A", Toast.LENGTH_SHORT);
            checkStatus(scoreOfA,scoreOfB);
            disA(scoreOfA);
        }

        public void B3(View view) {
            scoreOfB+=3;
            Toast toast = Toast.makeText(this,"+3 Point to Team B", Toast.LENGTH_SHORT);
            toast.show();
            checkStatus(scoreOfA,scoreOfB);
            disB(scoreOfB);
        }
        public void B2(View view) {
            scoreOfB+=2;
            Toast toast = Toast.makeText(this,"+2 Point to Team B", Toast.LENGTH_SHORT);
            toast.show();
            checkStatus(scoreOfA,scoreOfB);
            disB(scoreOfB);
        }
        public void Bf(View view) {
            scoreOfB+=1;
            Toast toast = Toast.makeText(this,"+1 Point to Team B", Toast.LENGTH_SHORT);
            toast.show();
            checkStatus(scoreOfA,scoreOfB);
            disB(scoreOfB);
        }
        public void reset(View view) {
            scoreOfB=0;
            scoreOfA=0;
            checkStatus(scoreOfA,scoreOfB);
            disB(scoreOfB);
            disA(scoreOfA);
            Toast toast = Toast.makeText(this,"Reset", Toast.LENGTH_SHORT);
            toast.show();
        }

    private void checkStatus(int scoreOfA, int scoreOfB){

        TextView textViewA = (TextView) findViewById(R.id.scoreTeamA);
        TextView textViewB = (TextView) findViewById(R.id.scoreTeamB);
        TextView status = (TextView) findViewById(R.id.status);
            if(scoreOfA>scoreOfB) {
                textViewA.setTextColor(getResources().getColor(R.color.green));
                textViewB.setTextColor(getResources().getColor(R.color.red));
                status.setText("Team A lead by " + String.valueOf(scoreOfA-scoreOfB));
            }
            else if(scoreOfA<scoreOfB) {
                textViewA.setTextColor(getResources().getColor(R.color.red));
                textViewB.setTextColor(getResources().getColor(R.color.green));
                status.setText("Team B lead by " + String.valueOf(scoreOfB-scoreOfA));
            }
            else if(scoreOfA==scoreOfB) {
                textViewA.setTextColor(getResources().getColor(R.color.black));
                textViewB.setTextColor(getResources().getColor(R.color.black));

                if(scoreOfA!=0 && scoreOfB!=0)
                status.setText("Draw now with score of " + String.valueOf(scoreOfA));
                else
                    status.setText("Start the game");
            }
    }

    private void disA(int scoreOfA){

            String st= String.valueOf(scoreOfA);
            TextView textView = (TextView) findViewById(R.id.scoreTeamA);
            textView.setText(st);
    }

    private void disB(int scoreOfB){
        String st= String.valueOf(scoreOfB);
        TextView textView =(TextView) findViewById(R.id.scoreTeamB);
        textView.setText(st);
    }


}