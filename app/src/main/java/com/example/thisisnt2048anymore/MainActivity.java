package com.example.thisisnt2048anymore;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the hex game

        Resources res = getResources();



        final HexGame game = new HexGame();

        final TextView myText = (TextView) findViewById(R.id.text_box);

        final TextView node0 = (TextView) findViewById(R.id.node0);
        final TextView node1 = (TextView) findViewById(R.id.node1);
        final TextView node2 = (TextView) findViewById(R.id.node2);
        final TextView node3 = (TextView) findViewById(R.id.node3);
        final TextView node4 = (TextView) findViewById(R.id.node4);
        final TextView node5 = (TextView) findViewById(R.id.node5);
        final TextView node6 = (TextView) findViewById(R.id.node6);
        final TextView node7 = (TextView) findViewById(R.id.node7);
        final TextView node8 = (TextView) findViewById(R.id.node8);
        final TextView node9 = (TextView) findViewById(R.id.node9);
        final TextView node10 = (TextView) findViewById(R.id.node10);
        final TextView node11 = (TextView) findViewById(R.id.node11);
        final TextView node12 = (TextView) findViewById(R.id.node12);
        final TextView node13 = (TextView) findViewById(R.id.node13);
        final TextView node14 = (TextView) findViewById(R.id.node14);
        final TextView node15 = (TextView) findViewById(R.id.node15);
        final TextView node16 = (TextView) findViewById(R.id.node16);
        final TextView node17 = (TextView) findViewById(R.id.node17);
        final TextView node18 = (TextView) findViewById(R.id.node18);

        final TextView[] nodes = {node0,node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,node11,node12,node13,node14,node15,node16,node17,node18};

        Button restart = (Button) findViewById(R.id.restart_button);
        final TextView high_score_text = (TextView) findViewById(R.id.high_score);
        final TextView game_score_text = (TextView) findViewById(R.id.game_score);

        View myView = findViewById(R.id.main_view);

        game.onSwipe(HexGame.UP);
        updateBoard(nodes, game, high_score_text, game_score_text);

        restart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    game.reset();
                    game.generateRandomTiles();
                    updateBoard(nodes,game, high_score_text, game_score_text);
                    myText.setText(R.string.app_name);
                }

                return true;
            }
        }
        );

        myView.setOnTouchListener(new OnSwipeTouchListener(this){

            @Override
            public void onSwipeUp(){
                game.onSwipe(HexGame.UP);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

            @Override
            public void onSwipeLeftUp(){
                game.onSwipe(HexGame.LEFT_UP);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

            @Override
            public void onSwipeLeftDown(){
                game.onSwipe(HexGame.LEFT_DOWN);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

            @Override
            public void onSwipeDown(){
                game.onSwipe(HexGame.DOWN);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

            @Override
            public void onSwipeRightDown(){
                game.onSwipe(HexGame.RIGHT_DOWN);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

            @Override
            public void onSwipeRightUp() {
                game.onSwipe(HexGame.RIGHT_UP);
                updateBoard(nodes, game, high_score_text, game_score_text);
                if(game.game_end_tag == true){
                    myText.setText("GAME OVER");
                }
            }

        });




//        for (int i = 0; i < HexGame.NUMBER_NODES; i++) {
//            System.out.println(game.game_board.get(i));
//            nodes[i].setText("5");
//        }


    }

    public void updateBoard(TextView nodes[], HexGame game, TextView high_score_text, TextView game_score_text){
        int temp;

        high_score_text.setText(Integer.toString(game.high_score));
        game_score_text.setText(Integer.toString(game.game_score));

        Resources res = getResources();
        int[] colors = res.getIntArray(R.array.colors);

        for (int i = 0; i < HexGame.NUMBER_NODES; i++) {
            temp = game.game_board.get(i);
            if(temp != 0){
                nodes[i].setText(Integer.toString(game.game_board.get(i)));

//                nodes[i].setBackgroundColor(colors[((int)Math.round(Math.sqrt(i)))]);
                for(Drawable drawable : nodes[i].getCompoundDrawables()){
                    if(drawable != null){
//
                        drawable.setColorFilter(new PorterDuffColorFilter(colors[((int)Math.round(Math.sqrt(i)))], PorterDuff.Mode.SRC_IN));
                    }
                }
            } else {
                nodes[i].setText("");
                for(Drawable drawable : nodes[i].getCompoundDrawables()){
                    if(drawable != null){
                        drawable.setColorFilter(new PorterDuffColorFilter(colors[0], PorterDuff.Mode.SRC_IN));
                    }
                }

            }
        }



    }

}

