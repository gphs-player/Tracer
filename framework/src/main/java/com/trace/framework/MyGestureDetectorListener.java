package com.trace.framework;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Stack;

/**
 * Created by pxw on 2018/10/24.
 */

public class MyGestureDetectorListener implements GestureDetector.OnGestureListener {

    private Stack<WindowBehavior> stack = null;

    public MyGestureDetectorListener() {
        stack = new Stack<>();
    }

    public Stack<WindowBehavior> getStack() {
        return stack;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        TracerUtils.wirteFile("ACTION_MD::TOUCH::(" + (int) motionEvent.getX() + "," + (int) motionEvent.getY() + ",MonkeyDevice.DOWN_AND_UP)\n");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent ev, MotionEvent ev1, float v, float v1) {
        stack.push(new WindowBehavior(ev.getX(), ev.getY(), ev1.getX(), ev1.getY()));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
