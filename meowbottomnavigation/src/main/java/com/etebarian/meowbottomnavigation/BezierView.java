package com.etebarian.meowbottomnavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 1HE on 2020-02-01.
 */

public final class BezierView extends View {

    private Paint mainPaint;
    private Paint shadowPaint;
    private Path mainPath;
    private Path shadowPath;
    private PointF[] outerArray;
    private PointF[] innerArray;
    private PointF[] progressArray;
    private float width;
    private float height;
    private float bezierInnerWidth;
    private float bezierInnerHeight;
    private float shadowHeight;
    private int color;
    private int shadowColor;
    private float bezierX;
    private float progress;

    @SuppressWarnings("unused")
    @SuppressLint({"NewApi"})
    public BezierView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews();
    }

    public BezierView(Context context) {
        super(context);
        initializeViews();
    }

    @SuppressWarnings("unused")
    public final int getColor() {
        return color;
    }

    public final void setColor(int value) {
        color = value;
        if (mainPaint != null) {
            mainPaint.setColor(color);
        }

        invalidate();
    }

    @SuppressWarnings("unused")
    public final int getShadowColor() {
        return shadowColor;
    }

    public final void setShadowColor(int value) {
        shadowColor = value;
        if (shadowPaint != null) {
            shadowPaint.setShadowLayer(Utils.dipf(getContext(), 4), 0.0F, 0.0F, shadowColor);
        }
        invalidate();
    }

    public final float getBezierX() {
        return this.bezierX;
    }

    public final void setBezierX(float value) {
        if (value != bezierX) {
            bezierX = value;
            invalidate();
        }
    }

    @SuppressWarnings("unused")
    public final float getProgress() {
        return progress;
    }

    public final void setProgress(float value) {
        if (value != progress) {
            progress = value;
            if (progressArray == null)
                return;


            progressArray[1].x = this.bezierX - this.bezierInnerWidth / (float)2;
            progressArray[2].x = this.bezierX - this.bezierInnerWidth / (float)4;
            progressArray[3].x = this.bezierX - this.bezierInnerWidth / (float)4;
            progressArray[4].x = this.bezierX;
            progressArray[5].x = this.bezierX + this.bezierInnerWidth / (float)4;
            progressArray[6].x = this.bezierX + this.bezierInnerWidth / (float)4;
            progressArray[7].x = this.bezierX + this.bezierInnerWidth / (float)2;

            for(int i = 2; i <= 6; ++i) {
                if (progress <= 1.0F) {
                    progressArray[i].y = calculate(innerArray[i].y, outerArray[i].y);
                } else {
                    progressArray[i].y = calculate(outerArray[i].y, innerArray[i].y);
                }
            }

            if (progress == 2.0F) {
                progress = 0.0F;
            }

            invalidate();
        }
    }

    private void initializeViews() {
        shadowHeight = Utils.dipf(getContext(), 8);
        setWillNotDraw(false);

        mainPath = new Path();
        shadowPath = new Path();
        outerArray = new PointF[11];
        innerArray = new PointF[11];
        progressArray = new PointF[11];

        for (int i= 0;i<11;i++) {
            outerArray[i] = new PointF();
            innerArray[i] = new PointF();
            progressArray[i] = new PointF();
        }


        mainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainPaint.setStrokeWidth(0f);
        mainPaint.setAntiAlias(true);
        mainPaint.setStyle(Paint.Style.FILL);
        mainPaint.setColor(color);

        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setShadowLayer(Utils.dipf(getContext(), 4), 0f, 0f, shadowColor);

        setColor(color);
        setShadowColor(this.shadowColor);
        this.setLayerType(1, this.shadowPaint);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = (float)MeasureSpec.getSize(widthMeasureSpec);
        this.height = (float)MeasureSpec.getSize(heightMeasureSpec);

        float bezierOuterWidth = Utils.dipf(getContext(), 72);
        float bezierOuterHeight = Utils.dipf(getContext(), 8);
        this.bezierInnerWidth = Utils.dipf(getContext(), 124);
        this.bezierInnerHeight = Utils.dipf(getContext(), 16);
        float extra = this.shadowHeight;

        if (outerArray == null)
            return;

        outerArray[0] = new PointF(0.0F, bezierOuterHeight + extra);
        outerArray[1] = new PointF(this.bezierX - bezierOuterWidth / (float)2, bezierOuterHeight + extra);
        outerArray[2] = new PointF(this.bezierX - bezierOuterWidth / (float)4, bezierOuterHeight + extra);
        outerArray[3] = new PointF(this.bezierX - bezierOuterWidth / (float)4, extra);
        outerArray[4] = new PointF(this.bezierX, extra);
        outerArray[5] = new PointF(this.bezierX + bezierOuterWidth / (float)4, extra);
        outerArray[6] = new PointF(this.bezierX + bezierOuterWidth / (float)4, bezierOuterHeight + extra);
        outerArray[7] = new PointF(this.bezierX + bezierOuterWidth / (float)2, bezierOuterHeight + extra);
        outerArray[8] = new PointF(this.width, bezierOuterHeight + extra);
        outerArray[9] = new PointF(this.width, this.height);
        outerArray[10] = new PointF(0.0F, this.height);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mainPath == null) {
            return;
        }
        mainPath.reset();

        if (shadowPaint == null) {
            return;
        }
        shadowPath.reset();

        if (this.progress == 0.0F) {
            this.drawInner(canvas, true);
            this.drawInner(canvas, false);
        } else {
            this.drawProgress(canvas, true);
            this.drawProgress(canvas, false);
        }

    }

    private void drawInner(Canvas canvas, boolean isShadow) {
        Paint paint = isShadow ? this.shadowPaint : this.mainPaint;
        Path path = isShadow ? this.shadowPath : this.mainPath;
        this.calculateInner();
        if (path == null || innerArray == null) {
            return;
        }


        path.lineTo(innerArray[0].x, innerArray[0].y);
        path.lineTo(innerArray[1].x, innerArray[1].y);
        path.cubicTo(innerArray[2].x, innerArray[2].y, innerArray[3].x, innerArray[3].y, innerArray[4].x, innerArray[4].y);
        path.cubicTo(innerArray[5].x, innerArray[5].y, innerArray[6].x, innerArray[6].y, innerArray[7].x, innerArray[7].y);
        path.lineTo(innerArray[8].x, innerArray[8].y);
        path.lineTo(innerArray[9].x, innerArray[9].y);
        path.lineTo(innerArray[10].x, innerArray[10].y);

        this.progressArray = innerArray.clone();
        canvas.drawPath(path, paint);
    }

    private void calculateInner() {
        float extra = this.shadowHeight;
        innerArray[0] = new PointF(0f, bezierInnerHeight + extra);
        innerArray[1] = new PointF((bezierX - bezierInnerWidth / 2), bezierInnerHeight + extra);
        innerArray[2] = new PointF(bezierX - bezierInnerWidth / 4, bezierInnerHeight + extra);
        innerArray[3] = new PointF(bezierX - bezierInnerWidth / 4, height - extra);
        innerArray[4] = new PointF(bezierX, height - extra);
        innerArray[5] = new PointF(bezierX + bezierInnerWidth / 4, height - extra);
        innerArray[6] = new PointF(bezierX + bezierInnerWidth / 4, bezierInnerHeight + extra);
        innerArray[7] = new PointF(bezierX + bezierInnerWidth / 2, bezierInnerHeight + extra);
        innerArray[8] = new PointF(width, bezierInnerHeight + extra);
        innerArray[9] = new PointF(width, height);
        innerArray[10] = new PointF(0f, height);
    }

    private void drawProgress(Canvas canvas, boolean isShadow) {
        Paint paint = isShadow ? this.shadowPaint : this.mainPaint;
        Path path = isShadow ? this.shadowPath : this.mainPath;

        path.lineTo(progressArray[0].x, progressArray[0].y);
        path.lineTo(progressArray[1].x, progressArray[1].y);
        path.cubicTo(progressArray[2].x, progressArray[2].y, progressArray[3].x, progressArray[3].y, progressArray[4].x, progressArray[4].y);
        path.cubicTo(progressArray[5].x, progressArray[5].y, progressArray[6].x, progressArray[6].y, progressArray[7].x, progressArray[7].y);
        path.lineTo(progressArray[8].x, progressArray[8].y);
        path.lineTo(progressArray[9].x, progressArray[9].y);
        path.lineTo(progressArray[10].x, progressArray[10].y);

        canvas.drawPath(path, paint);
    }

    private float calculate(float start, float end) {
        float p = this.progress;
        if (p > 1.0F) {
            p = this.progress - 1.0F;
        }

        if (p >= 0.9F && p <= 1.0F) {
            this.calculateInner();
        }

        return p * (end - start) + start;
    }

}
