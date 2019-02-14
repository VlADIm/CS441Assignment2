package com.example.thisisnt2048anymore;

import android.os.SystemClock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class HexGame {

    public boolean game_end_tag = false;

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

    public HashMap<Integer, Integer> game_board = new HashMap<>();

    public Queue<Integer> tile_changes = new LinkedList<Integer>();

    public List<Integer> empty_nodes = new ArrayList<Integer>();

    public int game_score = 0;
    public int high_score = 0;
    public boolean generate_new_tiles = false;

    public HexGame() {
        for (int i = 0; i < NUMBER_NODES; i++) {
            this.game_board.put(i, 0);
        }

        for (int i = 0; i < NUMBER_NODES; i++) {
            this.empty_nodes.add(i);
        }
        generate_new_tiles = true;
        generateRandomTiles();
    }

    private int[][] loadIntoArray(int direction){
        int[] array_size = {3,4,5,4,3};
        int[][] board_state = new int[5][5];
        int progression_start = 0;
        int progression_end = 1;
        int progression_index;
        int progression_direction = (direction < 3) ? 1 : -1 ;
        int next_node;

        for (int i = 0; i < 5; i++) {
            progression_index = progression_start;
            next_node = STARTING_NODES[direction][i];
            for (int j = 0; j < array_size[i]; j++) {
                board_state[i][j] = this.game_board.get(next_node);
                if(progression_index <= progression_end) {
                    next_node = next_node + (progression_direction * PROGRESSION[direction % 3][progression_index]);
                    progression_index++;
                }
            }
            if(progression_end == 3){ progression_start++; }
            else{ progression_end++; }
        }

//        System.out.println(Arrays.toString(board_state[0]));
//        System.out.println(Arrays.toString(board_state[1]));
//        System.out.println(Arrays.toString(board_state[2]));
//        System.out.println(Arrays.toString(board_state[3]));
//        System.out.println(Arrays.toString(board_state[4]));


        return board_state;
    }

    private void loadIntoMap(int direction, int[][] board_state){
        int[] array_size = {3,4,5,4,3};
        int progression_start = 0;
        int progression_end = 1;
        int progression_index;
        int progression_direction = (direction < 3) ? 1 : -1 ;
        int next_node;

        this.empty_nodes.clear();

        for (int i = 0; i < 5; i++) {
            progression_index = progression_start;
            next_node = STARTING_NODES[direction][i];
            for (int j = 0; j < array_size[i]; j++) {

                this.game_board.replace(next_node, board_state[i][j]);

                if(board_state[i][j] == 0){
                    this.empty_nodes.add(next_node);
                }
                if(progression_index <= progression_end) {
                    next_node = next_node + (progression_direction * PROGRESSION[direction % 3][progression_index]);
                    progression_index++;
                }
            }
            if(progression_end == 3){ progression_start++; }
            else{ progression_end++; }
        }
    }

    private int[][] collapseArray(int direction, int[][] board_state){

        int[][] board_state_start = new int[5][5];

        int[] array_size = {3,4,5,4,3};
        int next_index_fill;
        int i,j,k;
        int temp1;

        for (i = 0; i < 5; i++) {
            for(j = 0; j < array_size[i]; j++){
                board_state_start[i][j] = board_state[i][j];
            }
        }

        for (i = 0; i < 5; i++) {
            next_index_fill = 0;
            j = 0;
            while(j < array_size[i]){
                if(board_state[i][j]!=0){
                    k = j + 1;
                    while((k < array_size[i]) && (board_state[i][k] == 0)){
                        k++;
                    }
                    if(k < array_size[i]){
                        if(board_state[i][j] == board_state[i][k]){
                            board_state[i][j] = 0;
                            game_score += (board_state[i][k]) *2;
                            board_state[i][next_index_fill] = (board_state[i][k]) * 2;
                            board_state[i][k] = 0;
                            next_index_fill++;
                            j = k + 1;
                        } else {
                            temp1 = board_state[i][j];
                            board_state[i][j] = 0;
                            board_state[i][next_index_fill] = temp1;
                            next_index_fill++;
                            j = k;
                        }
                    } else {
                        temp1 = board_state[i][j];
                        board_state[i][j] = 0;
                        board_state[i][next_index_fill] = temp1;
                        j = k;
                    }
                } else {
                    j++;
                }
            }
        }

        for (i = 0; i < 5; i++) {
            for(j = 0; j < array_size[i]; j++){
                if(board_state_start[i][j] != board_state[i][j]){
                    generate_new_tiles = true;
                    break;
                }
            }
        }


//        int l;
//        for (l = 0; l < 5; l++) {
//            if(board_state_start[i] != board_state[i]){
//                break;
//            }
//        }
//        if(l != 5){
//            generateRandomTiles();
//        }
        return board_state;
    }

    public void onSwipe(int direction){
        if(game_end_tag == true) {return;}
        loadIntoMap(direction, collapseArray(direction,loadIntoArray(direction)));
        generateRandomTiles();
        if(game_score > high_score){
            high_score = game_score;
        }
    }

    public void reset(){
        for (int i = 0; i < NUMBER_NODES; i++) {
            this.game_board.put(i, 0);
        }
        game_score = 0;
        generate_new_tiles = true;
        this.game_end_tag = false;
    }

    public void generateRandomTiles(){

        if(generate_new_tiles == false){
            return;
        }

        Random random = new Random();

        int num_nodes = random.nextInt(2) + 1;
        int random_value = random.nextInt(2) + 1;
        int index;
        int node;

//        System.out.println(this.empty_nodes.size());

        if(this.empty_nodes.size() == 0){
            this.game_end_tag = detectGameDeadlock();

        } else {

            for (int i = 0; (this.empty_nodes.size() != 0) && (i < num_nodes);i++) {
                index = random.nextInt(this.empty_nodes.size());
                node = empty_nodes.remove(index);
                this.game_board.replace(node, random_value * 2);
                game_score += random_value;
                random_value = random.nextInt(2) + 1;
            }

        }

        generate_new_tiles = false;

    }


    private void detectMapChange(){
        //TODO: figure out where this goes for the animations
    }

    public boolean detectGameDeadlock(){
        int[] array_size = {3,4,5,4,3};
        int[][] board_state = new int[5][5];
        for (int i = 0; i < 6; i++) {
            board_state = loadIntoArray(i);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < array_size[j]-1; k++) {
                    if(board_state[j][k] == board_state[j][k+1]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String return_string = "\nTHIS IS THE GAME\n\n";
        return_string += ("\t\t" + Integer.toString(this.game_board.get(2)) + "\n");
        return_string += ("\t" + Integer.toString(this.game_board.get(1))+ "\t" + Integer.toString(this.game_board.get(5)) + "\t" + Integer.toString(this.game_board.get(6)) + "\n");
        return_string += (Integer.toString(this.game_board.get(0)) + "\t" + Integer.toString(this.game_board.get(4)) + "\t" + Integer.toString(this.game_board.get(9)) + "\t" + Integer.toString(this.game_board.get(10)) + "\t" + Integer.toString(this.game_board.get(11)) + "\n" );
        return_string += (Integer.toString(this.game_board.get(3)) + "\t" + Integer.toString(this.game_board.get(8)) + "\t" + Integer.toString(this.game_board.get(13)) + "\t" + Integer.toString(this.game_board.get(14)) + "\t" + Integer.toString(this.game_board.get(15)) + "\n" );
        return_string += (Integer.toString(this.game_board.get(7)) + "\t" + Integer.toString(this.game_board.get(12)) + "\t" + Integer.toString(this.game_board.get(16)) + "\t" + Integer.toString(this.game_board.get(17)) + "\t" + Integer.toString(this.game_board.get(18)) + "\n\n" );
        return return_string;
    }

//    public static void main(String[] args){
//        HexGame game = new HexGame();
//        HexGame deadlocked = new HexGame();
//
//        for (int i = 0; i < NUMBER_NODES; i++) {
//            deadlocked.game_board.replace(i,i);
//        }
//
//        deadlocked.detectGameDeadlock();
//
//        System.out.println(deadlocked.game_end_tag);
//
//        Random random = new Random();
//
//
//
//        while(!game.game_end_tag){
//            game.onSwipe(random.nextInt(6));
//
//        }
//
//        System.out.println(game.toString());
//        int direction;
//
//        /*TEST FOR COLLAPSING ON UP*/
//        direction = 0;
//        game.game_board.replace(16,2);
//        game.game_board.replace(13,0);
//        game.game_board.replace(9 ,2);
//        game.game_board.replace(5 ,4);
//        game.game_board.replace(2 ,4);
//        game.onSwipe(direction);
//        System.out.println(game.toString());
//        game.reset();
//
//        /*TEST FOR COLLAPSING ON LEFT UP*/
//        direction = 1;
//        game.game_board.replace(18,2);
//        game.game_board.replace(14,0);
//        game.game_board.replace(9,2);
//        game.game_board.replace(4,4);
//        game.game_board.replace(0,4);
//        game.onSwipe(direction);
//        System.out.println(game.toString());
//        game.reset();
//
//        /*TEST FOR COLLAPSING ON LEFT DOWN*/
//        direction = 2;
//        game.game_board.replace(11,2);
//        game.game_board.replace(10,0);
//        game.game_board.replace(9,2);
//        game.game_board.replace(8,4);
//        game.game_board.replace(7,4);
//        game.onSwipe(direction);
//        System.out.println(game);
//        game.reset();
//
//        /*TEST FOR COLLAPSING ON DOWN*/
//        direction = 3;
//        game.game_board.replace(2,2);
//        game.game_board.replace(5,0);
//        game.game_board.replace(9,2);
//        game.game_board.replace(13,4);
//        game.game_board.replace(16,4);
//        game.onSwipe(direction);
//        System.out.println(game);
//        game.reset();
//
//        /*TEST FOR COLLAPSING ON RIGHT DOWN*/
//        direction = 4;
//        game.game_board.replace(0,2);
//        game.game_board.replace(4,0);
//        game.game_board.replace(9,2);
//        game.game_board.replace(14,4);
//        game.game_board.replace(18,4);
//        game.onSwipe(direction);
//        System.out.println(game);
//        game.reset();
//
//        /*TEST FOR COLLAPSING ON RIGHT UP*/
//        direction = 5;
//        game.game_board.replace(7,2);
//        game.game_board.replace(8,0);
//        game.game_board.replace(9,2);
//        game.game_board.replace(10,4);
//        game.game_board.replace(11,4);
//        game.onSwipe(direction);
//        System.out.println(game);
//        game.reset();
//    }
}
