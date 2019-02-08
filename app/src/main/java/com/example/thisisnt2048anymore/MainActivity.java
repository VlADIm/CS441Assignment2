package com.example.thisisnt2048anymore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View myView = findViewById(R.id.main_view);

        final TextView myText = (TextView) findViewById(R.id.text_box);

        myView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeUp(){
                myText.setText("Swipe Up Detected");
            }

            @Override
            public void onSwipeLeftUp(){
                myText.setText("Swipe Left Up Detected");
            }

            @Override
            public void onSwipeLeftDown(){
                myText.setText("Swipe Left Down Detected");
            }

            @Override
            public void onSwipeDown(){
                myText.setText("Swipe Down Detected");
            }

            @Override
            public void onSwipeRightDown(){
                myText.setText("Swipe Right Down Detected");
            }

            @Override
            public void onSwipeRightUp(){
                myText.setText("Swipe Right Up Detected");
            }
        });
    }
}
