package net.dowhile.imageseeker.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by wz on 23/02/2017.
 */

public class AutoRotateTransformation extends BitmapTransformation {
    private float rotateRotationAngle = 0f;

    public AutoRotateTransformation(Context context) {
        super( context );
    }

    @Override

    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        if (toTransform.getWidth()>toTransform.getHeight())
            matrix.postRotate((float) 90.0);
        else matrix.postRotate((float) 0.0);
        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public String getId() {
        return "rotate" + rotateRotationAngle;
    }
}