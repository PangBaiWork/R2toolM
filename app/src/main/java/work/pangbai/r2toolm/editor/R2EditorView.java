package work.pangbai.r2toolm.editor;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import org.ahocorasick.trie.Emit;

import java.util.Collection;

import work.pangbai.r2toolm.databinding.LayoutR2editorviewBinding;
import work.pangbai.r2toolm.r2.R2;
import work.pangbai.r2toolm.tool.util;

public class R2EditorView extends androidx.appcompat.widget.AppCompatTextView {
    //CodeEditor {
    ScaleGestureDetector scaleGestureDetector;
    GestureDetector singleTapDetector;
    private float scale = 1.0f;
    LayoutR2editorviewBinding binding;
    public static int TYPE_EDITOR = 1;
    public static int TYPE_PSEUDOC = 2;
    public int TYPE = TYPE_EDITOR;
    ProgressBar progressBar;

    public void bind(LayoutR2editorviewBinding binding) {
        this.binding = binding;
    }
    public void setProgressBar(ProgressBar progressBar){
        this.progressBar=progressBar;
    }

    public void setEditorType(int type) {
        this.TYPE = type;
    }


    public R2EditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        singleTapDetector = new GestureDetector(context, new GestureListener());
        scaleGestureDetector.setQuickScaleEnabled(false);
        setHighlightColor(0xFF000000);
        setCustomSelectionActionModeCallback(new Action(getContext(), this));
        setTypeface(Typeface.createFromAsset(context.getAssets(), "font/JetBrainsMono-Bold.ttf"));


    }

    public void setTextH(String html) {


        Spanned sp = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        SpannableString st = new SpannableString(sp);
        Collection<Emit> emits = R2.current.trie.parseText(st);
        for (Emit e : emits) {
            st.setSpan(new RClickSpan(new int[]{e.getStart(), e.getEnd() + 1}, TYPE), e.getStart(), e.getEnd() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        util.runOnUiThread(() -> {
            if (progressBar!=null)progressBar.setIndeterminate(false);
            setText(st);
            setMovementMethod(new MLinkMovement());
        });
        binding.scrollH.scrollTo(0, 0);
        binding.scrollV.scrollTo(0, 0);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getPointerCount() > 1) {
            getParent().requestDisallowInterceptTouchEvent(event.getPointerCount() > 1);
            return scaleGestureDetector.onTouchEvent(event);
        } else {
            singleTapDetector.onTouchEvent(event);
            return super.onTouchEvent(event);
        }

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = detector.getScaleFactor();
            if (scale < 0.99f || scale > 1.01f) {
                boolean increase = scale > 1.f;
                float fontSize = util.pxToSp(getContext(), getTextSize());
                fontSize += (increase ? 1 : -1) * 2;
                fontSize = Math.max(8f, Math.min(fontSize, 30f));
                setTextSize(fontSize);
            }
            return true;
        }
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // 处理点击事件
            performClick(); // 调用TextView的点击事件处理
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            performLongClick();
            super.onLongPress(e);
        }
    }


}
