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
package net.dewep.intranetepitech.ui;

import fr.qinder.api.APICallback;
import fr.qinder.api.APIImage;
import fr.qinder.layout.ImageViewLoader;
import net.dewep.intranetepitech.EpitechAccount;
import net.dewep.intranetepitech.R;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TODO: Comments this class
 * 
 * @author Maigret Aurelien
 * @author Colin Julien
 */
public class NavigationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.ui_drawerlayout_navigation, container, false);

        final ImageViewLoader personalPicture = (ImageViewLoader) rootView.findViewById(R.id.ui_navigation_personal_picture);
        APIImage personalPictureApi = new APIImage(personalPicture);
        personalPictureApi.setCallback(new APICallback() {
            @Override
            public void onResult() {
                ImageView imageView = personalPicture.getImage();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                personalPicture.getImage().setImageBitmap(NavigationFragment.getCroppedBitmap(bitmap));
            }
        });
        personalPictureApi.execute("https://cdn.local.epitech.eu/userprofil/profilview/maigre_a.jpg");

        ((TextView) rootView.findViewById(R.id.ui_navigation_personal_title)).setText(EpitechAccount.getTitle());
        ((TextView) rootView.findViewById(R.id.ui_navigation_personal_login)).setText(EpitechAccount.getLogin());

        ((TextView) rootView.findViewById(R.id.ui_navigation_state)).setText("Etat : déconnecté");

        return rootView;
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp) {
        Bitmap sbmp;
        int radius = bmp.getWidth();

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / factor), (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        int border = 5;
        Bitmap output = Bitmap.createBitmap(radius + border * 2, radius + border * 2, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setStyle(Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(radius / 2 + border, radius / 2 + border, radius / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, border, border, paint);

        paint.setXfermode(null);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(border);
        canvas.drawCircle(radius / 2 + border, radius / 2 + border, radius / 2, paint);

        return output;
    }
}
