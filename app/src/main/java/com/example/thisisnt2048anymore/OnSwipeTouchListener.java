package com.example.thisisnt2048anymore;

import android.content.Context;
import android.gesture.Gesture;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context c){
        gestureDetector = new GestureDetector(c, new GestureListener());
    }


    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener{

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_THRESHOLD_VELOCITY = 100;

        @Override
        public boolean onDown(MotionEvent event){
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){

            boolean result = false;

            try {

                double diff_x = event1.getX() - event2.getX();
                double diff_y = event1.getY() - event2.getY();

//                get unit vector quantities for diff_x and diff_y called unit_diff_x and unit_diff_y

                double magnitude = Math.sqrt(Math.pow(diff_x,2) + Math.pow(diff_y,2));

                double velocity_magnitude = Math.sqrt(Math.pow(velocityX,2) + Math.pow(velocityY,2));

                double unit_diff_x = diff_x / magnitude;
                double unit_diff_y = diff_y / magnitude;

                if(velocity_magnitude > SWIPE_THRESHOLD_VELOCITY){

                    if(unit_diff_y > 0){
                       if(unit_diff_y > Math.sqrt(3)/2){
                            onSwipeUp();
                       } else if(unit_diff_x > 0.45){
                            onSwipeLeftUp();
                       } else {
                           onSwipeRightUp();
                       }
                    } else {
                        if(unit_diff_y < -(Math.sqrt(3)/2)){
                            onSwipeDown();
                        } else if(unit_diff_x > 0.45){
                            onSwipeLeftDown();
                        } else {
                            onSwipeRightDown();
                        }
                    }

                }


            } catch(Exception exception){
                exception.printStackTrace();
            }

            return result;
        }
    }

    public void onSwipeUp(){

    }

    public void onSwipeLeftUp(){

    }

    public void onSwipeLeftDown(){

    }

    public void onSwipeDown(){

    }

    public void onSwipeRightDown(){

    }

    public void onSwipeRightUp(){

    }
}
