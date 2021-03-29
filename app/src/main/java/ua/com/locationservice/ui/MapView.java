package ua.com.locationservice.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.RequiresApi;

public class MapView extends View {
    Paint mPaint,nPaint, otherPaint, outerPaint, mTextPaint;
    RectF mRectF;
    int mPadding;

    float arcLeft, arcTop, arcRight, arcBottom;

    Path mPath;


    public MapView(Context context) {
        super(context);

        mPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

        nPaint = new Paint();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);


        mTextPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(pxFromDp(context, 24));


        otherPaint = new Paint();

        outerPaint = new Paint();
        outerPaint.setStyle(Paint.Style.FILL);
        outerPaint.setColor(Color.YELLOW);

        mPadding = 100;


        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);


        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        arcLeft = pxFromDp(context, 20);
        arcTop = pxFromDp(context, 20);
        arcRight = pxFromDp(context, 100);
        arcBottom = pxFromDp(context, 100);


        Point p1 = new Point((int) pxFromDp(context, 80) + (screenWidth / 2), (int) pxFromDp(context, 40));
        Point p2 = new Point((int) pxFromDp(context, 40) + (screenWidth / 2), (int) pxFromDp(context, 80));
        Point p3 = new Point((int) pxFromDp(context, 120) + (screenWidth / 2), (int) pxFromDp(context, 80));

        mPath = new Path();
        mPath.moveTo(p1.x, p1.y);
        mPath.lineTo(p2.x, p2.y);
        mPath.lineTo(p3.x, p3.y);
        mPath.close();

        mRectF = new RectF(screenWidth / 4, screenHeight / 3, screenWidth / 6, screenHeight / 2);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(300, 250, 20, mPaint);



    }


    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}

