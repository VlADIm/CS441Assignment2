package com.example.thisisnt2048anymore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class HexGame {


    public static final int UP = 0;
    public static final int LEFT_UP = 1;
    public static final int LEFT_DOWN = 2;
    public static final int DOWN = 3;
    public static final int RIGHT_DOWN = 4;
    public static final int RIGHT_UP = 5;

    public static final int NUMBER_NODES = 19;

    private static final int[][] PROGRESSION = {{3,4,4,3},{4,5,5,4},{1,1,1,1}};

    private static final int[] to_up = {0,1,2,6,11};
    private static final int[] to_left_up = {2,1,0,3,7};
    private static final int[] to_left_down = {0,3,7,12,16};
    private static final int[] to_down = {18,17,16,12,7};
    private static final int[] to_right_down = {16,17,18,15,11};
    private static final int[] to_right_up = {2,6,11,15,18};

    private static final int[][] STARTING_NODES = {to_up, to_left_up, to_left_down, to_down, to_right_down, to_right_up};

    HashMap<Integer, Integer> game_board = new HashMap<>();

    Queue<Integer> tile_changes = new LinkedList<Integer>();

    public HexGame() {
        for (int i = 0; i < NUMBER_NODES; i++) {
            this.game_board.put(i, 0);
        }
    }

    private int[][] loadIntoArray(int direction){
        int[][] board_state = new int[5][5];
        int progression_start = 0;
        int progression_end = 1;
        int progression_index;
        int progression_direction = (direction < 3) ? 1 : -1 ;
        for (int i = 0; i < 5; i++) {
            board_state[i][0] = this.game_board.get(STARTING_NODES[direction][i]);
        }
        for (int i = 0; i < 5; i++) {
            progression_index = progression_start;
            for (int j = 0; ((((i==0)||(i==4))&&(j<3))||(((i==1)||(i==3))&&(j<4))||((i==2)&&(j<5))); j++) {
                board_state[i][j] = this.game_board.get(STARTING_NODES[direction][(board_state[i][j-1]) + (progression_direction * PROGRESSION[direction%3][progression_index])]);
                progression_index++;
            }
            if(progression_end == 3){progression_start++;}
            else{progression_end++;}
        }
        return board_state;
    }

    private void loadIntoMap(int direction, int[][] board_state){
        int progression_start = 0;
        int progression_end = 1;
        int progression_index;
        int progression_direction = (direction < 3) ? 1 : -1 ;
        for (int i = 0; i < 5; i++) {
            this.game_board.replace(STARTING_NODES[direction][i], board_state[i][0]);
        }
        for (int i = 0; i < 5; i++) {
            progression_index = progression_start;
            for (int j = 0; ((((i==0)||(i==4))&&(j<3))||(((i==1)||(i==3))&&(j<4))||((i==2)&&(j<5))); j++) {
                this.game_board.replace((STARTING_NODES[direction][(board_state[i][j-1]) + (progression_direction * PROGRESSION[direction%3][progression_index])]), board_state[i][j]);
                progression_index++;
            }
            if(progression_end == 3){progression_start++;}
            else{progression_end++;}
        }
    }

    private int[][] collapseArray(int direction, int[][] board_state){
        //TODO: Figure out how to make the board collapse

        for (int i = 0; i < 5; i++) {
            for (int j = 0; ((((i==0)||(i==4))&&(j<3))||(((i==1)||(i==3))&&(j<4))||((i==2)&&(j<5))); j++) {

                if(board_state[i][j] == board_state[i][j+1]){
                    board_state[i][j] = board_state[i][j] * 2;
                    board_state[i][j+1] = 0;
                } else if(board_state[i][j] == 0){
                    board_state[i][j] = 1;
                }

            }
        }
        return board_state;
    }

    public void onSwipe(int direction){
        loadIntoMap(direction, collapseArray(direction,loadIntoArray(direction)));
    }


    private void detectMapChange(){
        //TODO: figure out where this goes for the animations
    }
}
