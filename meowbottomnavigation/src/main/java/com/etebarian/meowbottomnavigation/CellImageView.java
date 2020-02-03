package com.etebarian.meowbottomnavigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by 1HE on 2020-02-02.
 */

public final class CellImageView extends AppCompatImageView {

    private boolean isBitmap;
    private boolean useColor;
    private int resource;
    private int color;
    private int size;
    private boolean actionBackgroundAlpha;
    private boolean changeSize;
    private boolean fitImage;
    private ValueAnimator colorAnimator;
    private boolean allowDraw;

    public CellImageView(Context context) {
        super(context);
        this.useColor = true;
        Context var10001 = this.getContext();
        this.size = Utils.dip(var10001, 24);
        this.changeSize = true;
        this.initializeView();
    }

    public CellImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.useColor = true;
        Context var10001 = this.getContext();
        this.size = Utils.dip(var10001, 24);
        this.changeSize = true;
        this.setAttributeFromXml(context, attrs);
        this.initializeView();
    }

    public CellImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.useColor = true;
        Context var10001 = this.getContext();
        this.size = Utils.dip(var10001, 24);
        this.changeSize = true;
        this.setAttributeFromXml(context, attrs);
        this.initializeView();
    }

    public final boolean isBitmap() {
        return this.isBitmap;
    }

    public final void setBitmap(boolean value) {
        this.isBitmap = value;
        this.draw();
    }

    public final boolean getUseColor() {
        return this.useColor;
    }

    public final void setUseColor(boolean value) {
        this.useColor = value;
        this.draw();
    }

    public final int getResource() {
        return this.resource;
    }

    public final void setResource(int value) {
        this.resource = value;
        this.draw();
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int value) {
        this.color = value;
        this.draw();
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int value) {
        this.size = value;
        this.requestLayout();
    }

    private void setAttributeFromXml(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CellImageView, 0, 0);

        try {
            this.setBitmap(a.getBoolean(R.styleable.CellImageView_meow_imageview_isBitmap, this.isBitmap));
            this.setUseColor(a.getBoolean(R.styleable.CellImageView_meow_imageview_useColor, this.useColor));
            this.setResource(a.getResourceId(R.styleable.CellImageView_meow_imageview_resource, this.resource));
            this.setColor(a.getColor(R.styleable.CellImageView_meow_imageview_color, this.color));
            this.setSize(a.getDimensionPixelSize(R.styleable.CellImageView_meow_imageview_size, this.size));
            this.actionBackgroundAlpha = a.getBoolean(R.styleable.CellImageView_meow_imageview_actionBackgroundAlpha, this.actionBackgroundAlpha);
            this.changeSize = a.getBoolean(R.styleable.CellImageView_meow_imageview_changeSize, this.changeSize);
            this.fitImage = a.getBoolean(R.styleable.CellImageView_meow_imageview_fitImage, this.fitImage);
        } finally {
            a.recycle();
        }

    }

    private void initializeView() {
        this.allowDraw = true;
        this.draw();
    }

    private void draw() {
        if (this.allowDraw) {
            if (this.resource != 0) {
                if (this.isBitmap) {
                    try {
                        Drawable var6;
                        if (this.color == 0) {
                            Context var10000 = this.getContext();
                            var6 = Utils.getDrawableCompat(var10000, this.resource);
                        } else {
                            var6 = Utils.changeColorDrawableRes(this.getContext(), this.resource, this.color);
                        }

                        Drawable drawable = var6;
                        this.setImageDrawable(drawable);
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }

                } else if (!this.useColor || this.color != 0) {
                    int c = this.useColor ? this.color : -2;

                    try {
                        this.setImageDrawable(Utils.changeColorDrawableVector(this.getContext(), this.resource, c));
                    } catch (Exception var4) {
                        var4.printStackTrace();
                    }

                }
            }
        }
    }

    public final void changeColorByAnim(int newColor, long d) {
        if (this.color == 0) {
            this.setColor(newColor);
        } else {
            int lastColor = this.color;
            ValueAnimator var10000 = this.colorAnimator;
            if (var10000 != null) {
                var10000.cancel();
            }

            this.colorAnimator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F});
            var10000 = this.colorAnimator;
            if (var10000 != null) {
                ValueAnimator var5 = var10000;
                var5.setDuration(d);
                var5.setInterpolator((new FastOutSlowInInterpolator()));
                var5.addUpdateListener((new CellImageView$changeColorByAnim$$inlined$apply$lambda$1(this, d, newColor, lastColor)));
                var5.start();
            }

        }
    }

    // $FF: synthetic method
    public static void changeColorByAnim$default(CellImageView var0, int var1, long var2, int var4, Object var5) {
        if ((var4 & 2) != 0) {
            var2 = 250L;
        }

        var0.changeColorByAnim(var1, var2);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.fitImage) {
            Drawable d = this.getDrawable();
            if (d != null) {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                double var6 = (double)((float)width * (float)d.getIntrinsicHeight() / (float)d.getIntrinsicWidth());
                boolean var8 = false;
                int height = (int)Math.ceil(var6);
                this.setMeasuredDimension(width, height);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }

        } else if (!this.isBitmap && this.changeSize) {
            int newSize = MeasureSpec.makeMeasureSpec(this.size, MeasureSpec.EXACTLY);
            super.onMeasure(newSize, newSize);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}


final class CellImageView$changeColorByAnim$$inlined$apply$lambda$1 implements ValueAnimator.AnimatorUpdateListener {
    // $FF: synthetic field
    final CellImageView this$0;
    // $FF: synthetic field
    final long $d$inlined;
    // $FF: synthetic field
    final int $newColor$inlined;
    // $FF: synthetic field
    final int $lastColor$inlined;

    CellImageView$changeColorByAnim$$inlined$apply$lambda$1(CellImageView var1, long var2, int var4, int var5) {
        this.this$0 = var1;
        this.$d$inlined = var2;
        this.$newColor$inlined = var4;
        this.$lastColor$inlined = var5;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }
}

