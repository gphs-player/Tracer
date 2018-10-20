package com.trace.framework;

import android.graphics.PointF;

import java.io.Serializable;

/**
 * <p>Date:2018/10/20.下午4:36</p>
 * <p>Author:niu bao</p>
 * <p>Desc:</p>
 */
public class Action {
    public static final String TYPE_ACTIVITY = "1";
    public static final String TYPE_KEYBOARD = "2";


    long time;
    Operate action;
    PointF point;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Operate getAction() {
        return action;
    }

    public void setAction(Operate action) {
        this.action = action;
    }

    public PointF getPoint() {
        return point;
    }

    public void setPoint(PointF point) {
        this.point = point;
    }

    static class Operate implements Serializable{
        String actionType;
        String action;

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }
}
