package work.pangbai.r2toolm.editor;

import android.graphics.Color;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import work.pangbai.r2toolm.R;

public final class RClickSpan extends ClickableSpan {
    int[] pos;
    int editorType;

    public RClickSpan(int[] pos,int editorType) {
        this.pos = pos;
        this.editorType=editorType;
    }

    @Override
    public void onClick(@NonNull View widget) {

        R2EditorView editorView = (R2EditorView) widget;
        try {
            String sym = editorView.getText().subSequence(pos[0], pos[1]).toString();
            //Log.e(getClass().getName(),sym);

            Layout layout = editorView.getLayout();
            int line = editorView.getLayout().getLineForOffset(pos[0]);
            int x = (int) layout.getPrimaryHorizontal(pos[0]);
            int y = layout.getLineTop(line) + editorView.getPaddingTop();

            PopupActionMenu popupMenu = new PopupActionMenu(editorView.getContext(), editorView);
            popupMenu.getMenuInflater().inflate(R.menu.sym_click_action, popupMenu.getMenu());
            popupMenu.setPopupOption(sym, editorView,editorType);
            popupMenu.show(x, y);


            Log.e("SymClicked", sym);
            //   String html = R2.current.disFunSym(sym);
            //   editorView.setTextH(html);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(0xffd70000);
        ds.setUnderlineText(true);
        ds.bgColor= Color.TRANSPARENT;

    }


}