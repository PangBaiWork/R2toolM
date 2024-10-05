package work.pangbai.r2toolm.editor;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

public class MLinkMovement extends LinkMovementMethod {
    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
      boolean r=  super.onTouchEvent(widget, buffer, event);
      Selection.removeSelection(buffer);
      return r;
    }
}
