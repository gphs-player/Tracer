package com.trace.framework;

/**
 * 行为操作
 * Created by pxw on 2018/10/23.
 */

public class WindowBehavior {
    public int x1, y1, x2, y2;

    public WindowBehavior(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public WindowBehavior(float x1, float y1, float x2, float y2) {
        this.x1 = (int) x1;
        this.y1 = (int) y1;
        this.x2 = (int) x2;
        this.y2 = (int) y2;
    }
}
