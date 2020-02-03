package com.etebarian.meowbottomnavigation;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by 1HE on 2020-02-02.
 */

@SuppressWarnings({"unused", "SameParameterValue"})
final class MeowBottomNavigationCell extends RelativeLayout {

    private int defaultIconColor;
    private int selectedIconColor;
    private int circleColor;
    private int icon;

    private String count;
    private int iconSize;
    private int countTextColor;
    private int countBackgroundColor;

    private Typeface countTypeface;
    private int rippleColor;
    private boolean isFromLeft;
    private long duration;
    private float progress;
    private boolean isEnabledCell;

    private MeowBottomNavigation.ClickListener onClickListener;

    public View containerView;
    private boolean allowDraw;

    public static final String EMPTY_VALUE = "empty";

    private HashMap<Integer, View> _$_findViewCache;

    public MeowBottomNavigationCell( Context context) {
        super(context);
        this.count = "empty";
        this.iconSize = Utils.dip(this.getContext(), 48);
        this.initializeView();
    }

    public MeowBottomNavigationCell( Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.count = "empty";
        this.iconSize = Utils.dip(this.getContext(), 48);
        this.setAttributeFromXml(context, attrs);
        this.initializeView();
    }

    public MeowBottomNavigationCell( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.count = "empty";
        this.iconSize = Utils.dip(this.getContext(), 48);
        this.setAttributeFromXml(context, attrs);
        this.initializeView();
    }

    public final int getDefaultIconColor() {
        return this.defaultIconColor;
    }

    public final void setDefaultIconColor(int value) {
        this.defaultIconColor = value;
        if (this.allowDraw) {
            CellImageView var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setColor(!this.isEnabledCell ? this.defaultIconColor : this.selectedIconColor);
        }

    }

    public final int getSelectedIconColor() {
        return this.selectedIconColor;
    }

    public final void setSelectedIconColor(int value) {
        this.selectedIconColor = value;
        if (this.allowDraw) {
            CellImageView var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setColor(this.isEnabledCell ? this.selectedIconColor : this.defaultIconColor);
        }

    }

    public final int getCircleColor() {
        return this.circleColor;
    }

    public final void setCircleColor(int value) {
        this.circleColor = value;
        if (this.allowDraw) {
            this.setEnabledCell(this.isEnabledCell);
        }

    }

    public final int getIcon() {
        return this.icon;
    }

    public final void setIcon(int value) {
        this.icon = value;
        if (this.allowDraw) {
            CellImageView var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setResource(value);
        }

    }


    public final String getCount() {
        return this.count;
    }

    public final void setCount(String value) {
        this.count = value;
        if (this.allowDraw) {
            TextView var12;
            if (this.count != null && this.count.equals("empty")) {
                var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                var12.setText("");
                var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                var12.setVisibility(INVISIBLE);
            } else {
                String var10000;
                if (this.count != null) {
                    var10000 = this.count;
                    if (var10000.length() >= 3) {
                        MeowBottomNavigationCell var11 = this;
                        String var2 = this.count;
                        byte var3 = 0;
                        byte var4 = 1;
                        boolean var5 = false;

                        var10000 = var2.substring(var3, var4);
                        String var7 = var10000;
                    }
                }

                float var13;
                label43:
                {
                    var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                    var12.setText(this.count);
                    var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                    var12.setVisibility(VISIBLE);
                    var10000 = this.count;
                    if (var10000 != null) {
                        boolean var10 = false;
                        if (((CharSequence) var10000).length() == 0) {
                            var13 = 0.5F;
                            break label43;
                        }
                    }

                    var13 = 1.0F;
                }

                float scale = var13;
                var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                var12.setScaleX(scale);
                var12 = (TextView) this._$_findCachedViewById(R.id.tv_count);
                var12.setScaleY(scale);
            }
        }

    }

    private void setIconSize(int value) {
        this.iconSize = value;
        if (this.allowDraw) {
            CellImageView var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setSize(value);
            var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setPivotX((float) this.iconSize / 2.0F);
            var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
            var10000.setPivotY((float) this.iconSize / 2.0F);
        }

    }

    public final int getCountTextColor() {
        return this.countTextColor;
    }

    public final void setCountTextColor(int value) {
        this.countTextColor = value;
        if (this.allowDraw) {
            ((TextView) this._$_findCachedViewById(R.id.tv_count)).setTextColor(this.countTextColor);
        }

    }

    public final int getCountBackgroundColor() {
        return this.countBackgroundColor;
    }

    public final void setCountBackgroundColor(int value) {
        this.countBackgroundColor = value;
        if (this.allowDraw) {
            GradientDrawable d = new GradientDrawable();
            d.setColor(this.countBackgroundColor);
            d.setShape(GradientDrawable.OVAL);
            ViewCompat.setBackground(this._$_findCachedViewById(R.id.tv_count), d);
        }

    }


    public final Typeface getCountTypeface() {
        return this.countTypeface;
    }

    public final void setCountTypeface(Typeface value) {
        this.countTypeface = value;
        if (this.allowDraw && this.countTypeface != null) {
            TextView var10000 = (TextView) this._$_findCachedViewById(R.id.tv_count);
            var10000.setTypeface(this.countTypeface);
        }

    }

    public final int getRippleColor() {
        return this.rippleColor;
    }

    public final void setRippleColor(int value) {
        this.rippleColor = value;
        if (this.allowDraw) {
            this.setEnabledCell(this.isEnabledCell);
        }

    }

    public final boolean isFromLeft() {
        return this.isFromLeft;
    }

    public final void setFromLeft(boolean var1) {
        this.isFromLeft = var1;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long var1) {
        this.duration = var1;
    }

    private void setProgress(float value) {
        this.progress = value;
        FrameLayout var10000 = (FrameLayout) this._$_findCachedViewById(R.id.fl);
        var10000.setY((1.0F - this.progress) * (float) Utils.dip(this.getContext(), 18) + (float) Utils.dip(this.getContext(), -2));
        CellImageView var5 = (CellImageView) this._$_findCachedViewById(R.id.iv);
        var5.setColor(this.progress == 1.0F ? this.selectedIconColor : this.defaultIconColor);
        float scale = (1.0F - this.progress) * -0.2F + 1.0F;
        var5 = (CellImageView) this._$_findCachedViewById(R.id.iv);
        var5.setScaleX(scale);
        var5 = (CellImageView) this._$_findCachedViewById(R.id.iv);
        var5.setScaleY(scale);
        GradientDrawable d = new GradientDrawable();
        d.setColor(this.circleColor);
        d.setShape(GradientDrawable.OVAL);
        ViewCompat.setBackground(this._$_findCachedViewById(R.id.v_circle), d);
        ViewCompat.setElevation(this._$_findCachedViewById(R.id.v_circle), this.progress > 0.7F ? Utils.dipf(this.getContext(), this.progress * 4.0F) : 0.0F);
        int m = Utils.dip(this.getContext(), 24);
        View var6 = this._$_findCachedViewById(R.id.v_circle);
        var6.setX((1.0F - this.progress) * (float) (this.isFromLeft ? -m : m) + (float) (this.getMeasuredWidth() - Utils.dip(this.getContext(), 48)) / 2.0F);
        var6 = this._$_findCachedViewById(R.id.v_circle);
        var6.setY((1.0F - this.progress) * (float) this.getMeasuredHeight() + (float) Utils.dip(this.getContext(), 6));
    }

    public final boolean isEnabledCell() {
        return this.isEnabledCell;
    }

    public final void setEnabledCell(boolean value) {
        this.isEnabledCell = value;
        GradientDrawable d = new GradientDrawable();
        d.setColor(this.circleColor);
        d.setShape(GradientDrawable.OVAL);
        if (Build.VERSION.SDK_INT >= 21 && !this.isEnabledCell) {
            FrameLayout var10000 = (FrameLayout) this._$_findCachedViewById(R.id.fl);
            var10000.setBackground(new RippleDrawable(ColorStateList.valueOf(this.rippleColor), null, d));
        } else {
            this._$_findCachedViewById(R.id.fl).postDelayed(new Runnable() {
                public final void run() {
                    try {
                        MeowBottomNavigationCell.this._$_findCachedViewById(R.id.fl).setBackgroundColor(0);
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }

                }
            }, 200L);
        }

    }

    public final MeowBottomNavigation.ClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public final void setOnClickListener(MeowBottomNavigation.ClickListener value) {
        this.onClickListener = value;
        final CellImageView var10000 = (CellImageView) this._$_findCachedViewById(R.id.iv);
        if (var10000 != null) {
            var10000.setOnClickListener(new OnClickListener() {
                public final void onClick(View it) {
                    MeowBottomNavigationCell.this.getOnClickListener().onClickItem(new MeowBottomNavigation.Model(0,0));
                }
            });
        }

    }


    public View getContainerView() {
        return this.containerView;
    }

    public void setContainerView( View var1) {
        this.containerView = var1;
    }

    private void setAttributeFromXml(Context context, AttributeSet attrs) {
    }

    private void initializeView() {
        this.allowDraw = true;
        View var10001 = LayoutInflater.from(this.getContext()).inflate(R.layout.meow_navigation_cell, this);
        this.setContainerView(var10001);
        this.draw();
    }

    private void draw() {
        if (this.allowDraw) {
            this.setIcon(this.icon);
            this.setCount(this.count);
            this.setIconSize(this.iconSize);
            this.setCountTextColor(this.countTextColor);
            this.setCountBackgroundColor(this.countBackgroundColor);
            this.setCountTypeface(this.countTypeface);
            this.setRippleColor(this.rippleColor);
            this.setOnClickListener(this.onClickListener);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setProgress(this.progress);
    }

    public final void disableCell() {
        if (this.isEnabledCell) {
            animateProgress$default(this, false, false, 2, null);
        }

        this.setEnabledCell(false);
    }

    public final void enableCell(boolean isAnimate) {
        if (!this.isEnabledCell) {
            this.animateProgress(true, isAnimate);
        }

        this.setEnabledCell(true);
    }

    // $FF: synthetic method
    public static void enableCell$default(MeowBottomNavigationCell var0, boolean var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = true;
        }

        var0.enableCell(var1);
    }

    private void animateProgress(boolean enableCell, boolean isAnimate) {
        long d = enableCell ? this.duration : 250L;
        ValueAnimator anim = ValueAnimator.ofFloat(0.0F, 1.0F);
        boolean var7 = false;
        boolean var8 = false;
        anim.setStartDelay(enableCell ? d / (long) 4 : 0L);
        anim.setDuration(isAnimate ? d : 1L);
        anim.setInterpolator((new FastOutSlowInInterpolator()));
        anim.addUpdateListener((new MeowBottomNavigationCell$animateProgress$$inlined$apply$lambda$1(this, enableCell, d, isAnimate)));
        anim.start();
    }

    // $FF: synthetic method
    static void animateProgress$default(MeowBottomNavigationCell var0, boolean var1, boolean var2, int var3, Object var4) {
        if ((var3 & 2) != 0) {
            var2 = true;
        }

        var0.animateProgress(var1, var2);
    }

    // $FF: synthetic method
    public static float access$getProgress$p(MeowBottomNavigationCell $this) {
        return $this.progress;
    }

    // $FF: synthetic method
    public static void access$setProgress$p(MeowBottomNavigationCell $this, float var1) {
        $this.setProgress(var1);
    }

    @SuppressLint("UseSparseArrays")
    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap<>();
        }

        View var2 = this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    final class MeowBottomNavigationCell$animateProgress$$inlined$apply$lambda$1 implements ValueAnimator.AnimatorUpdateListener {
        // $FF: synthetic field
        final MeowBottomNavigationCell this$0;
        // $FF: synthetic field
        final boolean $enableCell$inlined;
        // $FF: synthetic field
        final long $d$inlined;
        // $FF: synthetic field
        final boolean $isAnimate$inlined;

        MeowBottomNavigationCell$animateProgress$$inlined$apply$lambda$1(MeowBottomNavigationCell var1, boolean var2, long var3, boolean var5) {
            this.this$0 = var1;
            this.$enableCell$inlined = var2;
            this.$d$inlined = var3;
            this.$isAnimate$inlined = var5;
        }

        public final void onAnimationUpdate(ValueAnimator it) {
            float f = it.getAnimatedFraction();
            MeowBottomNavigationCell.access$setProgress$p(this.this$0, this.$enableCell$inlined ? f : 1.0F - f);
        }
    }
}

