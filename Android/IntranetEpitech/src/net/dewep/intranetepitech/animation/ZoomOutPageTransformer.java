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

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    private void positionInfinityNegative(View view) {
        // [-Infinity,-1)
        // This page is way off-screen to the left.
        view.setAlpha(0);
    }

    private void positionPositive(View view, float position) {
        // [-1,1]
        // Modify the default slide transition to shrink the page as well
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();
        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        float vertMargin = pageHeight * (1 - scaleFactor) / 2;
        float horzMargin = pageWidth * (1 - scaleFactor) / 2;
        if (position < 0) {
            view.setTranslationX(horzMargin - vertMargin / 2);
        } else {
            view.setTranslationX(-horzMargin + vertMargin / 2);
        }
        // Scale the page down (between MIN_SCALE and 1)
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
        // Fade the page relative to its size.
        view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
    }

    private void positionInfinityPositive(View view) {
        // (1,+Infinity]
        // This page is way off-screen to the right.
        view.setAlpha(0);
    }

    public void transformPage(View view, float position) {
        if (position < -1) {
            positionInfinityNegative(view);
        } else if (position <= 1) {
            positionPositive(view, position);
        } else {
            positionInfinityPositive(view);
        }
    }

}
