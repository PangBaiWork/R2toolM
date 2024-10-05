package work.pangbai.r2toolm;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import work.pangbai.r2toolm.databinding.ActivityMainJavaBinding;
import work.pangbai.r2toolm.databinding.LayoutR2editorviewBinding;
import work.pangbai.r2toolm.databinding.LayoutTerminalBinding;
import work.pangbai.r2toolm.editor.R2EditorView;
import work.pangbai.r2toolm.misc.DrawerAnim;
import work.pangbai.r2toolm.r2.R2;
import work.pangbai.r2toolm.tool.Init;
import work.pangbai.r2toolm.tool.ThreadUtil;
import work.pangbai.r2toolm.view.ScriptsLogTextView;
import work.pangbai.r2toolm.view.SymsListAdapter;
import work.pangbai.r2toolm.viewtool.BottomTabControl;
import work.pangbai.r2toolm.viewtool.MainViewPagerAdapter;
import work.pangbai.r2toolm.viewtool.NavigationControl;

public class MainActivityJava extends AppCompatActivity {
    LayoutTerminalBinding cmdBinding;
    public ActivityMainJavaBinding binding;
    ScriptsLogTextView logTextView;

    public R2EditorView editor;
    public LayoutR2editorviewBinding pseudoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Init(this);


        setLayout();
        setTabLayout();
        setRightSide();

//
//        binding.floatActionUndo.setOnClickListener(v -> setRightSide());
//
//        binding.floatActionAdd.setOnClickListener(v -> {
//
//            binding.progressbar.setIndeterminate(true);

        /*
         * There are some different between ThreadPoolExecutor and Thread
         * I wander it . Someone can give the answer ?
         */
//            ThreadUtil.executor(() -> {
//                try {
//                    r2 = new R2(pseudoc);
//                    r2.setR2(Init.busyboxPath);
//                    String html = r2.disFunSym("entry0");
//                    editor.setTextH(html);
//                    util.runOnUiThread(MainActivityJava.this::setRightSide);
//
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//
//            });


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

//            });


    }


    void setTabLayout() {

        List<View> views = new ArrayList<>();
        cmdBinding = LayoutTerminalBinding.inflate(getLayoutInflater());
        cmdBinding.terminalBg.setBackgroundColor(ColorUtils.setAlphaComponent(com.google.android.material.R.attr.colorPrimary, 200));
        cmdBinding.ExtraKey.addView(cmdBinding.terminal.createKeyView());
        cmdBinding.ExtraKey.setBackgroundColor(Color.WHITE);
        cmdBinding.terminal.setTerminal(getFilesDir().getAbsolutePath());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.height = 400;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;

        pseudoc = LayoutR2editorviewBinding.inflate(getLayoutInflater());
        pseudoc.editor.bind(pseudoc);
        pseudoc.editor.setEditorType(R2EditorView.TYPE_PSEUDOC);


        views.add(cmdBinding.getRoot());
        views.add(pseudoc.getRoot());
        logTextView = new ScriptsLogTextView(this);
        logTextView.setLayoutParams(params);
        views.add(logTextView);


        MainViewPagerAdapter fragmentAdapter = new MainViewPagerAdapter(views);
        new BottomTabControl(binding, cmdBinding, fragmentAdapter).setup((status) -> {
            // status=1 for pseudoc tab
            if (status == 1) {
                try {
                    if (R2.current.preFun == null) return;
                    String html = R2.current.disFunAddr(R2.current.preFun);
                    ThreadUtil.thread(() -> editor.setTextH(html));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });
        editor = binding.r2editorview.editor;
        editor.bind(binding.r2editorview);
        editor.setProgressBar(binding.progressbar);


    }

    void setLayout() {

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        binding.drawerLayout.setScrimColor(Color.TRANSPARENT);
        binding.drawerLayout.addDrawerListener(new DrawerAnim(binding.navigationView));
        binding.navigationView.setNavigationItemSelectedListener(new NavigationControl(this));
        binding.toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_folder) {
                binding.drawerLayout.openDrawer(GravityCompat.END);
                binding.include.searchBar.setText("");
            } else if (id == R.id.menu_save_project) {
                if (R2.current == null) return false;
                try {
                    R2.current.cmd("e dir.projects");
                    boolean result = R2.current.saveProject();
                    if (result)
                        Snackbar.make(binding.getRoot(), "Saved", Snackbar.LENGTH_SHORT).show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
//            else if (id == R.id.menu_apply_patch) {
//              if(R2.current.applyPatch()){
//                  Snackbar.make(binding.getRoot(),"Apply to binary",Snackbar.LENGTH_SHORT).show();
//              }
//            }
            return false;
        });

    }

    public void setRightSide() {
        RecyclerView recyclerView = binding.include.recycleviewSym;
        SymsListAdapter adapter = new SymsListAdapter(addr -> {
            try {
                String html = R2.current.disFunAddr(addr);
                ThreadUtil.thread(() -> editor.setTextH(html));
                binding.drawerLayout.closeDrawer(GravityCompat.END);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, binding.include);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.include.funListTab.setOnClickListener(adapter);
        binding.include.symListTab.setOnClickListener(adapter);
        binding.include.strListTab.setOnClickListener(adapter);
        binding.include.searchBar.addTextChangedListener(adapter);
    }


    public native int Init(String path);

    @Override
    protected void onDestroy() {
        try {
            R2.current.quit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }
}