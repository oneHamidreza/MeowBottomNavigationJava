package com.etebarian.meowbottomnavigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 1HE on 2020-02-02.
 */

@SuppressWarnings("unused")
public final class MeowBottomNavigation extends FrameLayout {

    public interface ClickListener{
        void onClickItem(Model item);
    }
    public interface ShowListener{
        void onShowItem(Model item);
    }
    public interface ReselectListener{
        void onReselectItem(Model item);
    }

    private ArrayList<Model> models;
    private ArrayList<MeowBottomNavigationCell> cells;
    private boolean callListenerWhenIsSelected;
    private int selectedId;
    private ClickListener onClickedListener;
    private ShowListener onShowListener;
    private ReselectListener onReselectListener;
    private int heightCell;
    private boolean isAnimating;
    private int defaultIconColor;
    private int selectedIconColor;
    private int backgroundBottomColor;
    private int circleColor;
    private int shadowColor;
    private int countTextColor;
    private int countBackgroundColor;

    private Typeface countTypeface;
    private int rippleColor;
    private boolean allowDraw;
    private LinearLayout ll_cells;
    private BezierView bezierView;
    private HashMap<Integer, View> _$_findViewCache;

    public MeowBottomNavigation(Context context) {
        super(context);
        this.models = new ArrayList<>();
        this.cells = new ArrayList<>();
        this.selectedId = -1;
        this.defaultIconColor = Color.parseColor("#757575");
        this.selectedIconColor = Color.parseColor("#2196f3");
        this.backgroundBottomColor = Color.parseColor("#ffffff");
        this.circleColor = Color.parseColor("#ffffff");
        this.shadowColor = -4539718;
        this.countTextColor = Color.parseColor("#ffffff");
        this.countBackgroundColor = Color.parseColor("#ff0000");
        this.rippleColor = Color.parseColor("#757575");
        this.heightCell = Utils.dip(this.getContext(), 72);
        this.initializeViews();
    }

    public MeowBottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.models = new ArrayList<>();
        this.cells = new ArrayList<>();
        this.selectedId = -1;
        this.defaultIconColor = Color.parseColor("#757575");
        this.selectedIconColor = Color.parseColor("#2196f3");
        this.backgroundBottomColor = Color.parseColor("#ffffff");
        this.circleColor = Color.parseColor("#ffffff");
        this.shadowColor = -4539718;
        this.countTextColor = Color.parseColor("#ffffff");
        this.countBackgroundColor = Color.parseColor("#ff0000");
        this.rippleColor = Color.parseColor("#757575");
        this.heightCell = Utils.dip(this.getContext(), 72);
        this.setAttributeFromXml(context, attrs);
        this.initializeViews();
    }

    public MeowBottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.models = new ArrayList<>();
        this.cells = new ArrayList<>();
        this.selectedId = -1;
        this.defaultIconColor = Color.parseColor("#757575");
        this.selectedIconColor = Color.parseColor("#2196f3");
        this.backgroundBottomColor = Color.parseColor("#ffffff");
        this.circleColor = Color.parseColor("#ffffff");
        this.shadowColor = -4539718;
        this.countTextColor = Color.parseColor("#ffffff");
        this.countBackgroundColor = Color.parseColor("#ff0000");
        this.rippleColor = Color.parseColor("#757575");
        this.heightCell = Utils.dip(this.getContext(), 72);
        this.setAttributeFromXml(context, attrs);
        this.initializeViews();
    }

    public final ArrayList<Model> getModels() {
        return this.models;
    }

    public final void setModels(ArrayList<Model> var1) {
        this.models = var1;
    }

    public final ArrayList<MeowBottomNavigationCell> getCells() {
        return this.cells;
    }

    public final int getDefaultIconColor() {
        return this.defaultIconColor;
    }

    public final void setDefaultIconColor(int value) {
        this.defaultIconColor = value;
        this.updateAllIfAllowDraw();
    }

    public final int getSelectedIconColor() {
        return this.selectedIconColor;
    }

    public final void setSelectedIconColor(int value) {
        this.selectedIconColor = value;
        this.updateAllIfAllowDraw();
    }

    public final int getBackgroundBottomColor() {
        return this.backgroundBottomColor;
    }

    public final void setBackgroundBottomColor(int value) {
        this.backgroundBottomColor = value;
        this.updateAllIfAllowDraw();
    }

    public final int getCircleColor() {
        return this.circleColor;
    }

    public final void setCircleColor(int value) {
        this.circleColor = value;
        this.updateAllIfAllowDraw();
    }

    public final int getCountTextColor() {
        return this.countTextColor;
    }

    public final void setCountTextColor(int value) {
        this.countTextColor = value;
        this.updateAllIfAllowDraw();
    }

    public final int getCountBackgroundColor() {
        return this.countBackgroundColor;
    }

    public final void setCountBackgroundColor(int value) {
        this.countBackgroundColor = value;
        this.updateAllIfAllowDraw();
    }


    public final Typeface getCountTypeface() {
        return this.countTypeface;
    }

    public final void setCountTypeface( Typeface value) {
        this.countTypeface = value;
        this.updateAllIfAllowDraw();
    }

    /**
     * @deprecated
     */
    // $FF: synthetic method
    private static void ll_cells$annotations() {
    }

    private void setAttributeFromXml(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MeowBottomNavigation, 0, 0);

        try {
            boolean var5 = false;
            boolean var6 = false;
            this.setDefaultIconColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_defaultIconColor, this.defaultIconColor));
            this.setSelectedIconColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_selectedIconColor, this.selectedIconColor));
            this.setBackgroundBottomColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_backgroundBottomColor, this.backgroundBottomColor));
            this.setCircleColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_circleColor, this.circleColor));
            this.setCountTextColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_countTextColor, this.countTextColor));
            this.setCountBackgroundColor(a.getColor(R.styleable.MeowBottomNavigation_mbn_countBackgroundColor, this.countBackgroundColor));
            this.rippleColor = a.getColor(R.styleable.MeowBottomNavigation_mbn_rippleColor, this.rippleColor);
            this.shadowColor = a.getColor(R.styleable.MeowBottomNavigation_mbn_shadowColor, this.shadowColor);
            String typeface = a.getString(R.styleable.MeowBottomNavigation_mbn_countTypeface);
            if (typeface != null) {
                boolean var11 = false;
                if (((CharSequence) typeface).length() > 0) {
                    this.setCountTypeface(Typeface.createFromAsset(context.getAssets(), typeface));
                }
            }
        } finally {
            a.recycle();
        }

    }

    private void initializeViews() {
        this.ll_cells = new LinearLayout(this.getContext());

        LayoutParams params = new LayoutParams(-1, this.heightCell);
        params.gravity = 80;
        this.ll_cells.setLayoutParams(params);
        this.ll_cells.setOrientation(LinearLayout.HORIZONTAL);
        this.ll_cells.setClipChildren(false);
        this.ll_cells.setClipToPadding(false);
        this.bezierView = new BezierView(this.getContext());

        this.bezierView.setLayoutParams(new LayoutParams(-1, this.heightCell));
        this.bezierView.setColor(this.backgroundBottomColor);
        this.bezierView.setShadowColor(this.shadowColor);
        BezierView var10001 = this.bezierView;

        this.addView(var10001);
        LinearLayout var9 = this.ll_cells;

        this.addView(var9);
        this.allowDraw = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.selectedId == -1) {
            BezierView var10000 = this.bezierView;
            var10000.setBezierX(Build.VERSION.SDK_INT >= 17 && this.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL ? (float) this.getMeasuredWidth() + Utils.dipf(this.getContext(), 72) : -Utils.dipf(this.getContext(), 72));
        }

        if (this.selectedId != -1) {
            this.show(this.selectedId, false);
        }

    }

    public final void add(final MeowBottomNavigation.Model model) {
        final MeowBottomNavigationCell cell = new MeowBottomNavigationCell(this.getContext());
        android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(0, this.heightCell, 1.0F);
        cell.setLayoutParams(params);
        cell.setIcon(model.getIcon());
        cell.setCount(model.getCount());
        cell.setDefaultIconColor(this.defaultIconColor);
        cell.setSelectedIconColor(this.selectedIconColor);
        cell.setCircleColor(this.circleColor);
        cell.setCountTextColor(this.countTextColor);
        cell.setCountBackgroundColor(this.countBackgroundColor);
        cell.setCountTypeface(this.countTypeface);
        cell.setRippleColor(this.rippleColor);
        cell.setOnClickListener(new ClickListener() {

            @Override
            public void onClickItem(Model item) {
                if (MeowBottomNavigation.this.isShowing(model.getId())) {
                    MeowBottomNavigation.this.onReselectListener.onReselectItem(model);
                }

                if (!cell.isEnabledCell() && !MeowBottomNavigation.access$isAnimating$p(MeowBottomNavigation.this)) {
                    MeowBottomNavigation.show$default(MeowBottomNavigation.this, model.getId(), false, 2, null);
                    MeowBottomNavigation.this.onClickedListener.onClickItem(model);
                } else if (MeowBottomNavigation.access$getCallListenerWhenIsSelected$p(MeowBottomNavigation.this)) {
                    MeowBottomNavigation.access$getOnClickedListener$p(MeowBottomNavigation.this).onClickItem(model);
                }
            }
        });
        cell.disableCell();
        LinearLayout var10000 = this.ll_cells;

        var10000.addView(cell);
        this.cells.add(cell);
        this.models.add(model);
    }

    private void updateAllIfAllowDraw() {
        if (this.allowDraw) {
            Iterable<MeowBottomNavigationCell> $this$forEach$iv = this.cells;

            for (Object element$iv : $this$forEach$iv) {
                MeowBottomNavigationCell it = (MeowBottomNavigationCell) element$iv;
                it.setDefaultIconColor(this.defaultIconColor);
                it.setSelectedIconColor(this.selectedIconColor);
                it.setCircleColor(this.circleColor);
                it.setCountTextColor(this.countTextColor);
                it.setCountBackgroundColor(this.countBackgroundColor);
                it.setCountTypeface(this.countTypeface);
            }

            BezierView var10000 = this.bezierView;

            var10000.setColor(this.backgroundBottomColor);
        }
    }

    private void anim(MeowBottomNavigationCell cell, int id, boolean enableAnimation) {
        this.isAnimating = true;
        int pos = this.getModelPosition(id);
        int nowPos = this.getModelPosition(this.selectedId);
        int nPos = nowPos < 0 ? 0 : nowPos;
        int var8 = pos - nPos;
        boolean var9 = false;
        int dif = Math.abs(var8);
        long d = (long) dif * 100L + 150L;
        long animDuration = enableAnimation ? d : 1L;
        FastOutSlowInInterpolator animInterpolator = new FastOutSlowInInterpolator();
        ValueAnimator anim = ValueAnimator.ofFloat(0.0F, 1.0F);
        boolean $i$f$forEach = false;
        boolean var16 = false;
        anim.setDuration(animDuration);
        anim.setInterpolator(animInterpolator);
        BezierView var10000 = this.bezierView;

        float beforeX = var10000.getBezierX();
        anim.addUpdateListener(new MeowBottomNavigation$anim$$inlined$apply$lambda$1(beforeX, this, animDuration, animInterpolator, cell));
        anim.start();
        int var14 = pos - nowPos;
        boolean var26;
        if (Math.abs(var14) > 1) {
            ValueAnimator progressAnim = ValueAnimator.ofFloat(0.0F, 1.0F);
            boolean var17 = false;
            progressAnim.setDuration(animDuration);
            progressAnim.setInterpolator(animInterpolator);
            progressAnim.addUpdateListener(new MeowBottomNavigation$anim$$inlined$apply$lambda$2(this, animDuration, animInterpolator));
            progressAnim.start();
        }

        cell.setFromLeft(pos > nowPos);
        Iterable<MeowBottomNavigationCell> $this$forEach$iv = this.cells;

        for (Object element$iv : $this$forEach$iv) {
            MeowBottomNavigationCell it = (MeowBottomNavigationCell) element$iv;
            it.setDuration(d);
        }

    }

    // $FF: synthetic method
    static void anim$default(MeowBottomNavigation var0, MeowBottomNavigationCell var1, int var2, boolean var3, int var4, Object var5) {
        if ((var4 & 4) != 0) {
            var3 = true;
        }

        var0.anim(var1, var2, var3);
    }

    public final void show(int id, boolean enableAnimation) {
        int i = 0;

        for (int var4 = (this.models).size(); i < var4; ++i) {
            Object var10000 = this.models.get(i);
            MeowBottomNavigation.Model model = (MeowBottomNavigation.Model) var10000;
            var10000 = this.cells.get(i);
            MeowBottomNavigationCell cell = (MeowBottomNavigationCell) var10000;
            if (model.getId() == id) {
                this.anim(cell, id, enableAnimation);
                cell.enableCell(enableAnimation);
                this.onShowListener.onShowItem(model);
            } else {
                cell.disableCell();
            }
        }

        this.selectedId = id;
    }

    // $FF: synthetic method
    public static void show$default(MeowBottomNavigation var0, int var1, boolean var2, int var3, Object var4) {
        if ((var3 & 2) != 0) {
            var2 = true;
        }

        var0.show(var1, var2);
    }

    public final boolean isShowing(int id) {
        return this.selectedId == id;
    }

    public final MeowBottomNavigation.Model getModelById(int id) {
        Iterable<Model> $this$forEach$iv = this.models;
        Iterator<Model> var4 = $this$forEach$iv.iterator();

        MeowBottomNavigation.Model it;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            it = var4.next();
        } while (it.getId() != id);

        return it;
    }


    public final MeowBottomNavigationCell getCellById(int id) {
        return this.cells.get(this.getModelPosition(id));
    }

    public final int getModelPosition(int id) {
        int i = 0;

        for (int var3 = (this.models).size(); i < var3; ++i) {
            MeowBottomNavigation.Model item = this.models.get(i);
            if (item.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    public final void setCount(int id, String count) {
        MeowBottomNavigation.Model var10000 = this.getModelById(id);
        if (var10000 != null) {
            int pos = this.getModelPosition(id);
            var10000.setCount(count);
            MeowBottomNavigationCell var5 = this.cells.get(pos);
            var5.setCount(count);
        }
    }

    public final void clearCount(int id) {
        MeowBottomNavigation.Model var10000 = this.getModelById(id);
        if (var10000 != null) {
            int pos = this.getModelPosition(id);
            var10000.setCount("empty");
            MeowBottomNavigationCell var4 = this.cells.get(pos);
            var4.setCount("empty");
        }
    }

    public final void clearAllCounts() {
        Iterable<Model> $this$forEach$iv = this.models;
        for (Object element$iv : $this$forEach$iv) {
            Model it = (Model) element$iv;
            this.clearCount(it.getId());
        }

    }

    public final void setOnShowListener(ShowListener listener) {
        this.onShowListener = listener;
    }

    public final void setOnClickMenuListener(ClickListener listener) {
        this.onClickedListener = listener;
    }

    public final void setOnReselectListener(ReselectListener listener) {
        this.onReselectListener = listener;
    }

    // $FF: synthetic method
    public static ReselectListener access$getOnReselectListener$p(MeowBottomNavigation $this) {
        return $this.onReselectListener;
    }

    // $FF: synthetic method
    public static void access$setOnReselectListener$p(MeowBottomNavigation $this, ReselectListener var1) {
        $this.onReselectListener = var1;
    }

    // $FF: synthetic method
    public static boolean access$isAnimating$p(MeowBottomNavigation $this) {
        return $this.isAnimating;
    }

    // $FF: synthetic method
    public static void access$setAnimating$p(MeowBottomNavigation $this, boolean var1) {
        $this.isAnimating = var1;
    }

    // $FF: synthetic method
    public static ClickListener access$getOnClickedListener$p(MeowBottomNavigation $this) {
        return $this.onClickedListener;
    }

    // $FF: synthetic method
    public static void access$setOnClickedListener$p(MeowBottomNavigation $this, ClickListener var1) {
        $this.onClickedListener = var1;
    }

    // $FF: synthetic method
    public static boolean access$getCallListenerWhenIsSelected$p(MeowBottomNavigation $this) {
        return $this.callListenerWhenIsSelected;
    }

    // $FF: synthetic method
    public static void access$setCallListenerWhenIsSelected$p(MeowBottomNavigation $this, boolean var1) {
        $this.callListenerWhenIsSelected = var1;
    }

    // $FF: synthetic method
    public static BezierView access$getBezierView$p(MeowBottomNavigation $this) {
        return $this.bezierView;
    }

    // $FF: synthetic method
    public static void access$setBezierView$p(MeowBottomNavigation $this, BezierView var1) {
        $this.bezierView = var1;
    }

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

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }

    @SuppressWarnings("WeakerAccess")
    public static class Model {

        private String count;
        private int id;
        private int icon;

        public final String getCount() {
            return this.count;
        }

        public final void setCount(String var1) {
            this.count = var1;
        }

        public final int getId() {
            return this.id;
        }

        public final void setId(int var1) {
            this.id = var1;
        }

        public final int getIcon() {
            return this.icon;
        }

        public final void setIcon(int var1) {
            this.icon = var1;
        }

        public Model(int id, int icon) {
            this.id = id;
            this.icon = icon;
            this.count = "empty";
        }
    }


    class MeowBottomNavigation$anim$$inlined$apply$lambda$1 implements ValueAnimator.AnimatorUpdateListener {
        // $FF: synthetic field
        final float $beforeX;
        // $FF: synthetic field
        final MeowBottomNavigation this$0;
        // $FF: synthetic field
        final long $animDuration$inlined;
        // $FF: synthetic field
        final FastOutSlowInInterpolator $animInterpolator$inlined;
        // $FF: synthetic field
        final MeowBottomNavigationCell $cell$inlined;

        MeowBottomNavigation$anim$$inlined$apply$lambda$1(float var1, MeowBottomNavigation var2, long var3, FastOutSlowInInterpolator var5, MeowBottomNavigationCell var6) {
            this.$beforeX = var1;
            this.this$0 = var2;
            this.$animDuration$inlined = var3;
            this.$animInterpolator$inlined = var5;
            this.$cell$inlined = var6;
        }

        public final void onAnimationUpdate(ValueAnimator it) {
            float f = it.getAnimatedFraction();
            float newX = this.$cell$inlined.getX() + (float)(this.$cell$inlined.getMeasuredWidth() / 2);
            if (newX > this.$beforeX) {
                MeowBottomNavigation.access$getBezierView$p(this.this$0).setBezierX(f * (newX - this.$beforeX) + this.$beforeX);
            } else {
                MeowBottomNavigation.access$getBezierView$p(this.this$0).setBezierX(this.$beforeX - f * (this.$beforeX - newX));
            }

            if (f == 1.0F) {
                MeowBottomNavigation.access$setAnimating$p(this.this$0, false);
            }

        }
    }

    final class MeowBottomNavigation$anim$$inlined$apply$lambda$2 implements ValueAnimator.AnimatorUpdateListener {
        // $FF: synthetic field
        final MeowBottomNavigation this$0;
        // $FF: synthetic field
        final long $animDuration$inlined;
        // $FF: synthetic field
        final FastOutSlowInInterpolator $animInterpolator$inlined;

        MeowBottomNavigation$anim$$inlined$apply$lambda$2(MeowBottomNavigation var1, long var2, FastOutSlowInInterpolator var4) {
            this.this$0 = var1;
            this.$animDuration$inlined = var2;
            this.$animInterpolator$inlined = var4;
        }

        public final void onAnimationUpdate(ValueAnimator it) {
            float f = it.getAnimatedFraction();
            MeowBottomNavigation.access$getBezierView$p(this.this$0).setProgress(f * 2.0F);
        }
    }

}