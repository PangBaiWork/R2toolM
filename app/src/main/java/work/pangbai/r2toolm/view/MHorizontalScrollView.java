package work.pangbai.r2toolm.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import androidx.viewpager.widget.ViewPager;

public class MHorizontalScrollView extends HorizontalScrollView {
    public MHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }


}
