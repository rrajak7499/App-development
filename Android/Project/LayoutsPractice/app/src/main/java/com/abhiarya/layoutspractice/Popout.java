package com.abhiarya.layoutspractice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class Popout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popout);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {
//            android.app.FragmentManager fragmentManager = getFragmentManager();
//            CustomeFragment customeFragment = new CustomeFragment();
//            customeFragment.show(fragmentManager, "hello");

        });
    }
    void setDate(String date){
        Toast.makeText(this,date, Toast.LENGTH_SHORT).show();
    }

    private class  CustomeFragment extends DialogFragment implements View.OnClickListener {
        View view;
        CalendarView calendar;
        Button button;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.pop_up, container, false);
            button = view.findViewById(R.id.button);
            calendar = view.findViewById(R.id.calendarView);
            button.setOnClickListener(this);
            return  view;
        }

        @Override
        public void onClick(View view) {
            this.dismiss();
            String date = String.valueOf(calendar.getDate());
            Popout po = (Popout)getActivity();
            po.setDate(date);
        }
    }
}