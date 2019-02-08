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

        //Create the hex game

        final HexGame game = new HexGame();


        View myView = findViewById(R.id.main_view);
        final TextView myText = (TextView) findViewById(R.id.text_box);

        myView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeUp(){
                game.onSwipe(HexGame.UP);
                myText.setText(game.toString());
            }

            @Override
            public void onSwipeLeftUp(){
                game.onSwipe(HexGame.LEFT_UP);
                myText.setText(game.toString());
            }

            @Override
            public void onSwipeLeftDown(){
                game.onSwipe(HexGame.LEFT_DOWN);
                myText.setText(game.toString());
            }

            @Override
            public void onSwipeDown(){
                game.onSwipe(HexGame.DOWN);
                myText.setText(game.toString());
            }

            @Override
            public void onSwipeRightDown(){
                game.onSwipe(HexGame.RIGHT_DOWN);
                myText.setText(game.toString());
            }

            @Override
            public void onSwipeRightUp(){
                game.onSwipe(HexGame.RIGHT_UP);
                myText.setText(game.toString());
            }
        });
    }
}
