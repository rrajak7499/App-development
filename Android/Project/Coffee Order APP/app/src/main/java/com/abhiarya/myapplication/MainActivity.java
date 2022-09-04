package com.abhiarya.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText= findViewById(R.id.editTextTextPersonName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dis2(amount);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                dis2(amount);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                dis2(amount);
            }
        });

    }
        int amount=1;

        public void  pl(View view){
            if(amount==50)
                return;
            amount+=1;
            dis(amount);
            dis2(amount);
        }

        public void  mi(View view){
            if(amount==1)
                return;
            amount-=1;
            dis(amount);
            dis2(amount);
        }
       private void dis(int amount){
            String st= String.valueOf(amount);
            TextView tw= findViewById(R.id.tw);
            tw.setText(st);
        }
        private String dis2(int amount){
            EditText editText= findViewById(R.id.editTextTextPersonName);
            String name= editText.getText().toString();
            int pricedis;
            CheckBox checkBox= findViewById(R.id.checkBox);
            CheckBox checkBox2= findViewById(R.id.checkBox2);
            if(checkBox.isChecked() && checkBox2.isChecked())
                pricedis= 10*amount+amount*5+amount*3;
            else  if(checkBox.isChecked() && !checkBox2.isChecked())
                pricedis= 10*amount+amount*5;
            else  if(!checkBox.isChecked() && checkBox2.isChecked())
                pricedis= 10*amount+amount*3;
            else
                pricedis= 10*amount;

            String top;
            if(checkBox.isChecked() && checkBox2.isChecked())
                top="Chocolate & Whipped Cream";
            else if(checkBox.isChecked() && !checkBox2.isChecked())
                top="Only Chocolate";
            else if(!checkBox.isChecked() && checkBox2.isChecked())
                top="Only Whipped Cream";
            else
                top="No";
            String toDis= "Name: "+name+"\nQuanntity: "+amount+"\nToppings: "+top+"\nprice: "+pricedis;
            TextView textView= findViewById(R.id.extra);
            textView.setText(toDis);
            return toDis;
        }
        public  void  ch(View view){
            dis2(amount);
        }
        public  void  we(View view){
            dis2(amount);
        }

        @SuppressLint("QueryPermissionsNeeded")
        public void order(View view){
            String email= "aky8507049610@gmailcom";
            Intent intent= new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, email);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Order");
            intent.putExtra(Intent.EXTRA_TEXT, dis2(amount));
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);

        }
}