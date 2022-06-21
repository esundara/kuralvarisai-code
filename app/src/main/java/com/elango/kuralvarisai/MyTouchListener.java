package com.elango.kuralvarisai;

import android.content.ClipData;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public  class MyTouchListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            Log.e("Elango", "START  LOCATION");
            return true;

        } else {
            return false;
        }
    }
}