/*
 * MIT License
 *
 * Copyright (c) 2016 Srijith Narayanan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.example.srijith.bottomnavigationviewbehavior;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;

/**
 * Created by Srijith on 22-12-2016.
 */

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

    private boolean isVisible = true;

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
                slideUp(child);
                totalConsumed = 0;
                isVisible = true;
            }
        } else {
            // Scroll to bottom
            if (isVisible && totalConsumed >= height) {
                slideDown(child);
                totalConsumed = 0;
                isVisible = false;
            }
        }
        totalConsumed += absDy;
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View target, float velocityX, float velocityY, boolean consumed) {
        if (consumed) {
            if (velocityY < 0) {
                // Fling to top
                if (!isVisible) {
                    slideUp(child);
                    totalConsumed = 0;
                    isVisible = true;
                    return true;
                }
            } else {
                // Fling to bottom
                if (isVisible) {
                    slideDown(child);
                    totalConsumed = 0;
                    isVisible = false;
                    return true;
                }
            }
        }
        return false;
    }

    private void slideUp(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(height).setDuration(200);
    }

}
