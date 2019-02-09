package com.example.thisisnt2048anymore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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



        final ImageView node0 = (ImageView) findViewById(R.id.node0);
        final ImageView node1 = (ImageView) findViewById(R.id.node1);
        final ImageView node2 = (ImageView) findViewById(R.id.node2);
        final ImageView node3 = (ImageView) findViewById(R.id.node3);
        final ImageView node4 = (ImageView) findViewById(R.id.node4);
        final ImageView node5 = (ImageView) findViewById(R.id.node5);
        final ImageView node6 = (ImageView) findViewById(R.id.node6);
        final ImageView node7 = (ImageView) findViewById(R.id.node7);
        final ImageView node8 = (ImageView) findViewById(R.id.node8);
        final ImageView node9 = (ImageView) findViewById(R.id.node9);
        final ImageView node10 = (ImageView) findViewById(R.id.node10);
        final ImageView node11 = (ImageView) findViewById(R.id.node11);
        final ImageView node12 = (ImageView) findViewById(R.id.node12);
        final ImageView node13 = (ImageView) findViewById(R.id.node13);
        final ImageView node14 = (ImageView) findViewById(R.id.node14);
        final ImageView node15 = (ImageView) findViewById(R.id.node15);
        final ImageView node16 = (ImageView) findViewById(R.id.node16);
        final ImageView node17 = (ImageView) findViewById(R.id.node17);
        final ImageView node18 = (ImageView) findViewById(R.id.node18);

        final ImageView[] nodes = {node0,node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,node11,node12,node13,node14,node15,node16,node17,node18};

        myView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeUp(){
                game.onSwipe(HexGame.UP);
//                myText.setText(game.toString());

                for (int i = 0; i < HexGame.NUMBER_NODES; i++) {
                    //do things here
                }



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

        if(game.game_end_tag){
            myText.setText("GAME OVER");
        }
    }
}
