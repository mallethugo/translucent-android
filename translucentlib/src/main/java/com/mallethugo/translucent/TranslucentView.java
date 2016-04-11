package com.mallethugo.translucent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.widget.TextView;

/**
 * Created by hugo
 * on 06/04/16
 * of Translucent
 */
public class TranslucentView extends TextView {

    // region static properties
    private final static int DEFAULT_FONT_SIZE = 22;
    private final static String MULTILINE_SEPARATOR = "\n";

    private final static int DEFAULT_ATTR_TEXT_SIZE = 0;
    private final static int DEFAULT_ATTR_TEXT = 1;

    private static final int[] DEFAULT_ATTRS = new int[] {
            android.R.attr.textSize,
            android.R.attr.text};
    // endregion

    private Context mContext;
    private String mText;

    // region position
    private int mStartX = 0;
    private int mStartY = 0;
    // endregion

    // region paint properties
    private Paint mBackgroundPaint;
    private Paint mTextPaint;
    // endregion

    // region background properties
    private Bitmap mBackgroundBitmap;
    private int mBackgroundWidth;
    private int mBackgroundHeight;
    private Canvas mBackgroundCanvas;
    // endregion

    // region constructors
    public TranslucentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if(!isInEditMode()) {
            getScreenSize();
            init(attrs);
        }
    }

    public TranslucentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if(!isInEditMode()) {
            getScreenSize();
            init(attrs);
        }
    }
    // endregion

    // region setters
    /**
     * Set background size in pixels
     * @param width Background width in pixels
     * @param height Background height in pixels
     */
    public void setBackgroundSize(int width, int height) {
        mBackgroundWidth = width;
        mBackgroundHeight = height;

        mBackgroundBitmap = Bitmap.createBitmap(mBackgroundWidth, mBackgroundHeight, Bitmap.Config.ALPHA_8);
        mBackgroundCanvas = new Canvas(mBackgroundBitmap);
    }

    /**
     * Set start position in pixels
     * @param startX start x position in pixels
     * @param startY start y position in pixels
     */
    public void setStartPositions(int startX, int startY) {
        mStartX = startX;
        mStartY = startY;
    }
    // endregion

    @Override
    protected void onDraw(Canvas canvas) {

        if (hasNullProperties()) {
            return;
        }

        mBackgroundCanvas.drawRect(0, 0, mBackgroundWidth, mBackgroundHeight, mBackgroundPaint);

        for (String line: mText.split(MULTILINE_SEPARATOR)) {
            mBackgroundCanvas.drawText(line, mStartX, mStartY, mTextPaint);
            mStartY += mTextPaint.descent() - mTextPaint.ascent();
        }

        canvas.drawBitmap(mBackgroundBitmap, 0, 0, mBackgroundPaint);
    }

    // region private methods
    private void init(AttributeSet attrs) {
        Log.d("Translucent", "init");
        TypedArray attributesArray = mContext.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TranslucentView,
                0, 0);

        final TypedArray defaultAttributesArray = mContext.obtainStyledAttributes(attrs, DEFAULT_ATTRS);

        float fontSize;
        int backgroundColor;
        try {
            mText = defaultAttributesArray.getString(DEFAULT_ATTR_TEXT);
            if (mText == null) {
                mText = "";
            }
            fontSize = defaultAttributesArray.getDimensionPixelSize(DEFAULT_ATTR_TEXT_SIZE, DEFAULT_FONT_SIZE);
            backgroundColor = attributesArray.getColor(R.styleable.TranslucentView_translucentBackgroundColor, Color.WHITE);
        } finally {
            attributesArray.recycle();
            defaultAttributesArray.recycle();
        }

        mTextPaint = new Paint();
        mTextPaint.setTextSize(fontSize);
        mTextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mTextPaint.setAntiAlias(true);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(backgroundColor);

        mBackgroundBitmap = Bitmap.createBitmap(mBackgroundWidth, mBackgroundHeight, Bitmap.Config.ALPHA_8);
        mBackgroundCanvas = new Canvas(mBackgroundBitmap);

        mStartY = getStatusBarHeight() + getToolBarHeight();
    }

    private void getScreenSize() {
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mBackgroundWidth = size.x;
        mBackgroundHeight = size.y;
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int getToolBarHeight() {
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        return 0;
    }

    private boolean hasNullProperties() {
        return mBackgroundCanvas == null
                || mBackgroundBitmap == null
                || mBackgroundPaint == null
                || mTextPaint == null;
    }
    // endregion
}
