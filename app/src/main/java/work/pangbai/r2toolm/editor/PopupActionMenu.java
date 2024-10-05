package work.pangbai.r2toolm.editor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.databinding.XrefListBinding;
import work.pangbai.r2toolm.r2.R2;
import work.pangbai.r2toolm.tool.DialogUtils;
import work.pangbai.r2toolm.tool.ThreadUtil;
import work.pangbai.r2toolm.tool.util;
import work.pangbai.r2toolm.view.XrefListAdapter;

public class PopupActionMenu extends PopupMenu {
    Context context;
    AlertDialog alertDialog;


    public PopupActionMenu(@NonNull Context context, @NonNull View anchor) {
        super(context, anchor);
        this.context = context;

    }

    public void setPopupOption(String sym, R2EditorView editorView,int editorType) {
        //   public void setPopupOption(int[] pos, R2EditorView editorView) {
        setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            //   String sym = editorView.getText().subSequence(pos[0], pos[1]).toString();

            if (id == R.id.fun_goto) {

                try {
                    String html = R2.current.disFunSym(sym);
                  if (editorType==R2EditorView.TYPE_EDITOR) ThreadUtil.thread(() -> editorView.setTextH(html));
                } catch (Exception ignored) {

                }


            } else if (id == R.id.fun_xref) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                // SymListBinding binding=SymListBinding.inflate(LayoutInflater.from(context));
                XrefListBinding binding = XrefListBinding.inflate(LayoutInflater.from(context));


                XrefListAdapter adapter = new XrefListAdapter(addr -> {
                    try {
                        String html = R2.current.disFunAddr(addr);
                        if (editorType==R2EditorView.TYPE_EDITOR)   ThreadUtil.thread(() -> editorView.setTextH(html));
                        alertDialog.dismiss();
                    } catch (Exception ignored) {

                    }
                });


                binding.xrefList.setAdapter(adapter);
                binding.xrefList.setLayoutManager(new LinearLayoutManager(context));
                try {
                    Log.e(getClass().getName(), sym);
                    adapter.setData(R2.current.getFunAddrBySym(sym));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (adapter.getItemCount() == 0) {
                    Toast.makeText(context, "Not found Xref", Toast.LENGTH_SHORT).show();
                    return false;
                }
                builder.setView(binding.getRoot())
                        .setTitle("Xref")
                        //  .setPositiveButton(context.getString(R.string.confirm), )
                        .setNegativeButton(context.getString(R.string.cancle), null);
                builder.setBackground(context.getDrawable(R.drawable.drawer_round_bg));
                alertDialog = builder.create();
                alertDialog.getWindow().setWindowAnimations(R.style.DialogAnim);
                alertDialog.show();


            } else if (id == R.id.fun_copy) {
                util.copyToClipboard(context, sym);

            } else if (id == R.id.fun_rename) {
                DialogUtils.showInputDialog(context, "Rename Function", userInput -> {
                    try {
                        userInput = userInput.trim();
                        boolean result = R2.current.renameFun(sym, userInput);
                        if (result) {
                            String html = R2.current.disFunAddr(R2.current.preFun);
                            if (editorType==R2EditorView.TYPE_EDITOR)   ThreadUtil.thread(() -> editorView.setTextH(html));
                        } else {
                            Toast.makeText(context, "Name Exist", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            }
            return false;
        });
    }


    public void show(int x, int y) {
        try {
            // 通过反射获取 mPopup 字段
            Field mPopupField = PopupMenu.class.getDeclaredField("mPopup");
            mPopupField.setAccessible(true);
            Object menuPopupHelper = mPopupField.get(this);

            // 获取 tryShow(int, int) 方法
            Method tryMethod = menuPopupHelper.getClass().getDeclaredMethod("tryShow", int.class, int.class);
            tryMethod.setAccessible(true);
            tryMethod.invoke(menuPopupHelper, x, y);
             /*
            tryMethod = menuPopupHelper.getClass().getDeclaredMethod("setForceShowIcon", Boolean.class);
            tryMethod.setAccessible(true);
            tryMethod.invoke(menuPopupHelper, true);
            */

            tryMethod = menuPopupHelper.getClass().getDeclaredMethod("getListView");
            tryMethod.setAccessible(true);
            ListView listView = (ListView) tryMethod.invoke(menuPopupHelper);
            FrameLayout frameLayout = (FrameLayout) listView.getParent();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
            params.width = (int) util.dip2px(frameLayout.getContext(), 120);
            frameLayout.setLayoutParams(params);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
