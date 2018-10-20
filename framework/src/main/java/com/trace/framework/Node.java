package com.trace.framework;

import java.util.Queue;
import java.util.Stack;

/**
 * <p>Date:2018/10/20.下午4:42</p>
 * <p>Author:niu bao</p>
 * <p>Desc:</p>
 */
public class Node {

    String className;
    LifeCycle classLifeCycle;
    StringBuffer action;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LifeCycle getClassLifeCycle() {
        return classLifeCycle;
    }

    public void setClassLifeCycle(LifeCycle classLifeCycle) {
        this.classLifeCycle = classLifeCycle;
    }

    public StringBuffer getAction() {
        return action;
    }

    public void appendAction(String aAction) {
        this.action.append(action);
    }

    public enum LifeCycle {
        START,
        STOP,
        CREATE
    }
}
