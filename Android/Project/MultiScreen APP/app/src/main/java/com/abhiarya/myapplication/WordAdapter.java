package com.abhiarya.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Words> {
    private final int bgColor;
    public WordAdapter(Activity context, ArrayList<Words> word,int image){
        super(context,0,word);
        bgColor=image;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view= convertView;
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_view,parent,false);
        }
        Words currentWords= getItem(position);
        TextView eng= view.findViewById(R.id.eng);
        eng.setText(currentWords.geteng());
        TextView hin = view.findViewById(R.id.hin);
        hin.setText(currentWords.getHin());

        ImageView imageView = view.findViewById(R.id.img);
        if(currentWords.hasImage()) {
            imageView.setImageResource(currentWords.getImg());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);

        View container = view.findViewById(R.id.textcon);
        int color= ContextCompat.getColor(getContext(),bgColor);
        container.setBackgroundColor(color);
        return view;
    }
}
