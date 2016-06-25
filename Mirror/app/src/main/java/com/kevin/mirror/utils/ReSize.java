package com.kevin.mirror.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.kevin.mirror.MyApp;

/**
 * Created by kevin on 16/6/21.
 */
public class ReSize {
    private BitmapFactory.Options options;

    public static int sp2px(float spValue) {
        final float fontScale = MyApp.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = MyApp.context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void bitmapResize(Bitmap bitmap, ImageView iv) {
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postScale(0.5f,0.5f);
            bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

            int d = bitmap.getHeight() - (bitmap.getWidth() * iv.getHeight() / iv.getWidth());
            Bitmap out = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() - d, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(out);
            canvas.drawBitmap(bitmap, 0, 0, new Paint());
            bitmap.recycle();



            iv.setImageBitmap(out);

        }

//        if (bitmap != null) {
//            int d = bitmap.getHeight() -(bitmap.getWidth() * holder.imageView.getHeight()/holder.imageView.getWidth());
//            Log.d("SpecialAdapter", "d:" + d);
//            Bitmap out = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight()-d, Bitmap.Config.ARGB_4444);
//            Canvas canvas = new Canvas(out);
//
//            // canvas.drawBitmap(bitmap,0,0,new Paint());
//            Rect src = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()-d);
//            Rect dst = new Rect(0,0,out.getWidth(),out.getHeight());
//            canvas.drawBitmap(bitmap,src,dst,null);
//            bitmap.recycle();
//            Log.d("SpecialAdapter", "out.getHeight():" + out.getHeight());
//            //  Log.d("SpecialAdapter", "bitmap.getHeight():" + bitmap.getHeight());
//            holder.imageView.setImageBitmap(out);
//            holder.progressBar.setVisibility(View.GONE);
//        }
    }

}
