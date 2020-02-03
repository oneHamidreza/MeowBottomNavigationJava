package com.etebarian.meowbottomnavigation;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by 1HE on 2020-02-01.
 */

class Utils {

    private static float getDP(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    static float dipf(Context context, float f) {
        return f * getDP(context);
    }

    static float dipf(Context context, int i) {
        return i * getDP(context);
    }

    static int dip(Context context, int i) {
        return (int) (i * getDP(context));
    }

    static int dip(Context context, float f) {
        return (int) (f * getDP(context));
    }

    static Drawable getDrawableCompat(Context context, int res) {
        return ContextCompat.getDrawable(context, res);
    }

    static Drawable changeColorDrawableVector(Context c, int resDrawable, int color) {
        if (c == null)
            return null;

        Drawable d = VectorDrawableCompat.create(c.getResources(), resDrawable, null);
        assert d != null;
        d.mutate();
        if (color != -2)
            d.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return d;
    }

    public static Drawable changeColorDrawableRes(Context c, int resDrawable, int color) {
        if (c == null)
            return null;

        Drawable d = ContextCompat.getDrawable(c, resDrawable);
        assert d != null;
        d.mutate();
        if (color != -2)
            d.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return d;
    }
}
