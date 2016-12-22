package com.example.srijith.bottomnavigationviewbehavior;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by Srijith on 22-12-2016.
 */

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private int height;
    private int totalConsumed = 0;

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int absDy = Math.abs(dyConsumed);
        if (dyConsumed < 0) {
            // Scroll to top
            if (totalConsumed >= height) {
                child.clearAnimation();
                child.animate().translationY(0).setDuration(200);
                totalConsumed = 0;
            }
        } else {
            // Scroll to bottom
            if (totalConsumed >= height) {
                child.clearAnimation();
                child.animate().translationY(height).setDuration(200);
                totalConsumed = 0;
            }
        }
        totalConsumed += absDy;
    }

}
