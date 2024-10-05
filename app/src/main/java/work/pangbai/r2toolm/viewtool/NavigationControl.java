package work.pangbai.r2toolm.viewtool;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import work.pangbai.r2toolm.MainActivityJava;
import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.r2.R2;
import work.pangbai.r2toolm.tool.DialogUtils;
import work.pangbai.r2toolm.tool.Init;
import work.pangbai.r2toolm.tool.ThreadUtil;
import work.pangbai.r2toolm.tool.util;
import work.pangbai.r2toolm.view.FileListSelect;

public class NavigationControl implements NavigationView.OnNavigationItemSelectedListener {
    MainActivityJava mainActivityJava;

    public NavigationControl(MainActivityJava mainActivityJava) {
        this.mainActivityJava = mainActivityJava;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();



        if (id == R.id.project_add) {

            new FileListSelect(mainActivityJava, "Open Binary", false, Init.sdcardPath, file -> {
                mainActivityJava.binding.drawerLayout.closeDrawer(GravityCompat.START);

                mainActivityJava.binding.progressbar.setIndeterminate(true);
                /*
                 * There are some different between ThreadPoolExecutor and Thread
                 * I wander it . Someone can give the answer ?
                 */
                ThreadUtil.executor(() -> {
                    try {
                        if (R2.current != null) R2.current.quit();
                        new R2(mainActivityJava.pseudoc);
                        Log.e("FileSelect", file.getAbsolutePath());
                        R2.current.setR2(file.getAbsolutePath());
                        String html = R2.current.disFunSym("entry0");
                        if (html == null) {
                            util.runOnUiThread(() -> Toast.makeText(mainActivityJava, "Not a known binary type", Toast.LENGTH_SHORT).show());
                            return;
                        }
                        mainActivityJava.editor.setTextH(html);
                        util.runOnUiThread(() -> mainActivityJava.setRightSide());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                });


//            ThreadUtil.thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//
//
//                    r2 = new R2(pseudoc);
//
//                    r2.setR2(Init.busyboxPath);
//                    String html = r2.disFunSym("entry0");
//                    editor.setTextH(html);
//                    util.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            setRightSide();
//                        }
//                    });
//                    }catch (Exception e){
//
//                    }
//                }
//            });
            }).showChooseDialog();
        } else if (id == R.id.project_open) {
            new FileListSelect(mainActivityJava, "Open Project Folder", true, Init.projectPath, file -> {
                if (file == null) return;
                mainActivityJava.binding.drawerLayout.closeDrawer(GravityCompat.START);

                mainActivityJava.binding.progressbar.setIndeterminate(true);
                ThreadUtil.executor(() -> {
                    try {
                        if (R2.current != null) R2.current.quit();
                        new R2(mainActivityJava.pseudoc);

                        R2.current.loadProject(file);
                        String html = R2.current.disFunSym("entry0");
                        mainActivityJava.editor.setTextH(html);
                        util.runOnUiThread(() -> mainActivityJava.setRightSide());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            }).showChooseDialog();
        } else if (id == R.id.project_info) {
            if (R2.current==null)return false;
            try {
                String html=R2.current.cmd("i");
                Spanned sp= Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
                DialogUtils.showConfirmationDialog(mainActivityJava,"Binary Information",sp,null,null);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
