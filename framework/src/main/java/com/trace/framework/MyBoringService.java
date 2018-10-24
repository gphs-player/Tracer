package com.trace.framework;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by pxw on 2018/10/24.
 */

public class MyBoringService extends AccessibilityService {
    private final String TAG = "TAG";

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        AccessibilityServiceInfo config = new AccessibilityServiceInfo();
        //配置监听的事件类型为界面变化|点击事件
        config.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED | AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED;
        config.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        if (Build.VERSION.SDK_INT >= 18) {
            config.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS | AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS;
            ;
        }
        config.packageNames = new String[]{"com.trace"};
        setServiceInfo(config);
    }


    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        return super.onKeyEvent(event);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                //界面文字改动
                CharSequence beforeText = event.getBeforeText();
                CharSequence charSequence = event.getText().get(0);
                if (charSequence.length() > beforeText.length()) {
                    String replace = charSequence.toString().replace(beforeText, "");
                    TracerUtils.wirteFile("ACTION_MR::SLEEP::(0.5)\n");
                    TracerUtils.wirteFile("ACTION_MD::INPUT::(" + replace + ")\n");
                    TracerUtils.wirteFile("ACTION_MR::SLEEP::(0.5)\n");
                }
                break;
        }

    }

    @Override
    public void onInterrupt() {

    }
}
