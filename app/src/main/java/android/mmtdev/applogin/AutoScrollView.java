package android.mmtdev.applogin;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class AutoScrollView extends ScrollView {
    private Handler handler;
    private int scrollDelay = 50; // Scroll delay in milliseconds (adjust for smoother or faster scrolling)
    private double scrollStep = 2; // Scroll step in pixels (adjust for slower or faster scrolling)
    private boolean autoScrollEnabled = true;
    private int lastScrollY = 0;

    public AutoScrollView(Context context) {
        super(context);
        init();
    }

    public AutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        handler = new Handler();
        // Start auto-scrolling
        startAutoScroll();
    }

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (autoScrollEnabled) {
                    lastScrollY += scrollStep;
                    scrollTo(0, lastScrollY);
                    if (lastScrollY >= getChildAt(0).getHeight() - getHeight()) {
                        lastScrollY = 0;
                        scrollTo(0, lastScrollY);
                    }
                }
                handler.postDelayed(this, scrollDelay);
            }
        }, scrollDelay);
    }

    public void setScrollStep(int step) {
        this.scrollStep = step;
    }

    public void setScrollDelay(int delay) {
        this.scrollDelay = delay;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE:
                autoScrollEnabled = false;
                break;
            // Disable auto-scrolling while manually scrolling
            case MotionEvent.ACTION_UP:
                autoScrollEnabled = true; // Enable auto-scrolling when manual scrolling stops
                lastScrollY = getScrollY(); // Save the last scroll position
                break;
        }
        return super.onTouchEvent(ev);
    }
}

