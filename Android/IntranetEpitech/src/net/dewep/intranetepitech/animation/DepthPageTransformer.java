/*
 * Copyright (C) 2014 Maigret Aurelien / Colin Julien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.dewep.intranetepitech.animation;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    private void positionInfinityNegative(View view, float position) {
        // [-Infinity,-1)
        // This page is way off-screen to the left.
        view.setAlpha(0);
    }

    private void positionNegative(View view, float position) {
        // [-1,0]
        // Use the default slide transition when moving to the left page
        view.setAlpha(1);
        view.setTranslationX(0);
        view.setScaleX(1);
        view.setScaleY(1);
    }

    private void positionPositive(View view, float position) {
        // (0,1]
        // Fade the page out.
        int pageWidth = view.getWidth();
        view.setAlpha(1 - position);
        // Counteract the default slide transition
        view.setTranslationX(pageWidth * -position);
        // Scale the page down (between MIN_SCALE and 1)
        float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
    }

    private void positionInfinityPositive(View view, float position) {
        // (1,+Infinity]
        // This page is way off-screen to the right.
        view.setAlpha(0);
    }

    public void transformPage(View view, float position) {
        if (position < -1) {
            positionInfinityNegative(view, position);
        } else if (position <= 0) {
            positionNegative(view, position);
        } else if (position <= 1) {
            positionPositive(view, position);
        } else {
            positionInfinityPositive(view, position);
        }
    }

}
