package com.trace.framework;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * <p>Date:2018/10/20.下午4:04</p>
 * <p>Author:niu bao</p>
 * <p>Desc:</p>
 */
public class GestureProxy implements GestureDetector.OnGestureListener{
    Action action;
        Node node = new Node();
    private void read(){
        node.setClassLifeCycle(Node.LifeCycle.CREATE);
        PointF point = action.getPoint();
        long time = action.getTime();
        Action.Operate action = this.action.getAction();
        switch (action.getActionType()){
            case Action.TYPE_ACTIVITY:
                String operate = action.getAction();
                break;
            case Action.TYPE_KEYBOARD:

                break;
        }
    }
    @Override
    public boolean onDown(MotionEvent e) {
        int actionMasked = e.getActionMasked();
        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
                node.appendAction("ACTION_DOWN");
                break;
        }

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
