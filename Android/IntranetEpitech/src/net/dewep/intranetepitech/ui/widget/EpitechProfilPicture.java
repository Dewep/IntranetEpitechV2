package net.dewep.intranetepitech.ui.widget;

import net.dewep.intranetepitech.R;
import net.dewep.intranetepitech.api.Configurations;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import fr.qinder.api.APICallback;
import fr.qinder.api.APIImage;
import fr.qinder.layout.ImageViewLoader;

public class EpitechProfilPicture extends ImageViewLoader {

    private boolean mCroppingCircular;
    private int mCroppingCircularBorderSize;
    private int mCroppingCircularBorderColor;

    public EpitechProfilPicture(Context context) {
        super(context);
        getConfigs(context, null);
    }

    public EpitechProfilPicture(Context context, AttributeSet attrs) {
        super(context, attrs);
        getConfigs(context, attrs);
    }

    public EpitechProfilPicture(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getConfigs(context, attrs);
    }

    private void getConfigs(Context context, AttributeSet attrs) {
        mCroppingCircular = false;
        mCroppingCircularBorderSize = 0;
        mCroppingCircularBorderColor = Color.WHITE;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EpitechProfilPicture);
            mCroppingCircular = ta.getBoolean(0, false);
            mCroppingCircularBorderSize = getDimensionValue(ta, 1, 0);
            mCroppingCircularBorderColor = ta.getColor(2, Color.WHITE);
            ta.recycle();
        }
    }

    public void execute(String login) {
        APIImage api = new APIImage(this);
        if (mCroppingCircular) {
            api.setCallback(new APICallback() {
                @Override
                public void onResult() {
                    ImageView imageView = getImage();
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    getImage().setImageBitmap(getCircleBitmap(bitmap));
                }
            });
        }
        api.execute(Configurations.getUrlProfilPicture(login));
    }

    public Bitmap getCircleBitmap(Bitmap bmp) {
        Bitmap sbmp;
        int radius = bmp.getWidth();

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp, (int) (bmp.getWidth() / factor), (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius + mCroppingCircularBorderSize * 2, radius + mCroppingCircularBorderSize * 2, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setStyle(Style.FILL);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(mCroppingCircularBorderColor);
        canvas.drawCircle(radius / 2 + mCroppingCircularBorderSize, radius / 2 + mCroppingCircularBorderSize, radius / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, mCroppingCircularBorderSize, mCroppingCircularBorderSize, paint);

        paint.setXfermode(null);
        paint.setStyle(Style.STROKE);
        paint.setColor(mCroppingCircularBorderColor);
        paint.setStrokeWidth(mCroppingCircularBorderSize);
        canvas.drawCircle(radius / 2 + mCroppingCircularBorderSize, radius / 2 + mCroppingCircularBorderSize, radius / 2, paint);

        return output;
    }
}
