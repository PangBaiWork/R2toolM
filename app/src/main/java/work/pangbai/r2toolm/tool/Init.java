package work.pangbai.r2toolm.tool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.preference.PrefManager;


public class Init {


    public static String filesDirPath;


    //  public static String shellPath;
    public static String libDir, binDir, nodeDir, r2toomDir;

    public static String busyboxPath, shPath,scriptsPath;
    public static String[] envp;
    final public static String sdcardPath = "/storage/emulated/0/";
    final public static String linker = "/system/bin/linker64";
    public static String keyPath;
    public static String projectPath="/data/user/0/work.pangbai.r2toolm/files/.local/share/radare2/projects";
    final public static boolean android10 = Build.VERSION.SDK_INT >= 29;
    boolean result;


    AlertDialog dialog;
    public static String systemShell = "/system/bin/sh";

    public Init(Activity ct) {
        PrefManager.init(ct);
        PrefManager.isFirstOrReinstall(ct);
        File files = ct.getFilesDir();


        filesDirPath = files.getAbsolutePath();
        libDir = filesDirPath + "/usr/lib";
        binDir = filesDirPath + "/usr/bin";
        // busyboxPath =  ct.getApplicationInfo().nativeLibraryDir + "/busybox";
        busyboxPath = binDir + "/busybox";
        shPath = binDir + "/sh";
        nodeDir = binDir + "/node";
        r2toomDir = filesDirPath + "/r2toolm";
        keyPath = filesDirPath + "/r2toolm/keys";
        scriptsPath = filesDirPath + "/script/";
        String[] envp = {
                //"PATH=" + "/system/bin"
                "PATH=" + Init.binDir + ":/product/bin:/apex/com.android.runtime/bin:/apex/com.android.art/bin:/system_ext/bin:/system/bin:/system/xbin:/vendor/bin",
                "LD_PRELOAD=" +  ct.getApplicationInfo().nativeLibraryDir+ "/libexec.so",
                "HOME=" + Init.filesDirPath,
                "TMPDIR=" + Init.filesDirPath + "/usr/tmp",
                "PREFIX=" + Init.filesDirPath + "/usr",
                "LD_LIBRARY_PATH=" +libDir,
                "PS1=\\[\\e[1\\;31m\\])➜ \\[\\e[1;36m\\]\\W\\[\\e[m\\] ",
                "TERM=xterm-256color",
                "LANG=en_US.UTF-8",
                android10 ? "ANDROID10=1 " : "ANDROID10=0",
                "ANDROID_DATA=/data",
                "ANDROID_ROOT=/system"
        };
        Init.envp = envp;






        if (PrefManager.isFirstOrReinstall_flag!=0) {
            //First installation
            dialog = DialogUtils.showLoadingDialog(ct, ct.getString(R.string.load_resources));
            ThreadUtil.thread(() -> {
                        try {
                            result = installEnv(ct);

                        } catch (IOException | InterruptedException e) {
                            Log.e("InstallEnv  Failed",e.getMessage());
                            result = false;
                        }
                        util.runOnUiThread(() -> checkPermission(ct));
                        dialog.dismiss();


                    }
            );

        }

    }

    @SuppressLint("SuspiciousIndentation")
    boolean installEnv(Context context) throws IOException, InterruptedException {
        //  String name = "busybox";
        IO.deleteFileOrFolder(filesDirPath+"/usr");
        //new File(filesDirPath).mkdirs();
        IO.copyAssetsDirToSDCard(context, "busybox", binDir);
      //  IO.copyAssetsDirToSDCard(context, "libexec.so", libDir);
        new File(busyboxPath).setExecutable(true);
        cmdExer.setCwd(Init.binDir);
        // cmdExer.execute(busyboxPath + " --install -s " + binDir, false, true);
        cmdExer.execute("ln -s busybox env", false, false);
        cmdExer.execute("ln -s busybox sh", false, false);
        cmdExer.execute("ln -s busybox basename", false, false);
        cmdExer.setCwd(Init.filesDirPath);



        cmdExer.execute("tar -xJf - -C /", false, false);

        OutputStream outputStream = cmdExer.process.getOutputStream();


        InputStream inputStream = context.getResources().getAssets().open("env.tar.xz");
        byte[] buffer = new byte[2048];
        int bytesRead;
        while (true) {

            assert inputStream != null;
            if ((bytesRead = inputStream.read(buffer)) == -1) break;

            outputStream.write(buffer, 0, bytesRead);

        }
        inputStream.close();
        outputStream.close();
        //  int exit = cmdExer.process.waitFor();
        cmdExer.process.waitFor() ;
        File[] libs = new File(context.getApplicationInfo().nativeLibraryDir).listFiles();
        assert libs != null;
        for (File lib : libs) {
            String libPath=lib.getAbsolutePath();
            if (!libPath.contains("exec")) {
                cmdExer.execute("ln -sf "+libPath+" "+libDir+"/"+lib.getName(), false, true);
            }
        }
        return true;

    }







    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public void checkPermission(Activity ct) {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                dialog = DialogUtils.showConfirmationDialog(ct, ct.getString(R.string.sdcard_permisson),
                        ct.getString(R.string.sdcard_description), () -> {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                            intent.setData(Uri.parse("package:" + ct.getPackageName()));
                            ct.startActivity(intent);
                        }, null);

            } else {
                //   havePermission = true;
                Log.i("Permission", "Android 11以上，当前已有权限");
            }
        } else {
            if (ActivityCompat.checkSelfPermission(ct, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                dialog = DialogUtils.showConfirmationDialog(ct, ct.getString(R.string.sdcard_permisson),
                        ct.getString(R.string.sdcard_description), () -> {
                            ActivityCompat.requestPermissions(ct, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                        }, null);
            } else {
                // havePermission = true;
                Log.i("Permission", "Android 6.0以上，11以下，当前已有权限");
            }
        }
    }


}