package work.pangbai.r2toolm.editor;

import android.content.Context;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.r2.R2;
import work.pangbai.r2toolm.tool.DialogUtils;
import work.pangbai.r2toolm.tool.ThreadUtil;
import work.pangbai.r2toolm.tool.util;

public class Action implements ActionMode.Callback {
    private Menu mMenu;
    public Context mContext;
    public R2EditorView r2EditorView;

    public Action(Context mContext, R2EditorView r2EditorView) {
        this.r2EditorView = r2EditorView;
        this.mContext = mContext;
    }


    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return true;//返回false则不会显示弹窗
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater menuInflater = actionMode.getMenuInflater();
        menu.clear();
        menuInflater.inflate(R.menu.code_select_menu, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.code_asm) {//全选
            Log.e(getClass().getName(), "asm");
            int start = r2EditorView.getLayout().getLineForOffset(r2EditorView.getSelectionStart());
            int end = r2EditorView.getLayout().getLineForOffset(r2EditorView.getSelectionEnd());
            int len = (end - start) + 1;
            int lineStart = r2EditorView.getLayout().getLineStart(start);
            int lineEnd = r2EditorView.getLayout().getLineEnd(start);
            String asm = r2EditorView.getText().subSequence(lineStart, lineEnd).toString();
            String regex = "0x[0-9a-f]+";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(asm);
            if (!matcher.find()) {
                Toast.makeText(mContext, "Cant change this assembly", Toast.LENGTH_SHORT).show();
                return false;
            }

            try {
                String asm_addr=matcher.group();
                String disasmString = R2.current.getAsmAtAddr(asm_addr, len);
                DialogUtils.showInputDialog(mContext, "Assembly", disasmString, new DialogUtils.DialogInputListener() {
                    @Override
                    public void onConfirm(String userInput) {
                        R2.current.patchAsmAt(asm_addr, userInput);
                        try {
                         String   html = R2.current.disFunAddr(R2.current.preFun);
                            ThreadUtil.thread(() -> r2EditorView.setTextH(html));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        Log.e("disam", userInput);
                    }
                });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        } else if (itemId == R.id.code_copy) {
            Toast.makeText(mContext, "选中的内容已复制到剪切板", Toast.LENGTH_SHORT).show();
            util.copyToClipboard(mContext, r2EditorView.getText().subSequence(r2EditorView.getSelectionStart(), r2EditorView.getSelectionEnd()));
        }
        mMenu.close();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }
}