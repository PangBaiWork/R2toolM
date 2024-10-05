package work.pangbai.r2toolm.r2;

import android.annotation.SuppressLint;
import android.util.Log;

import org.ahocorasick.trie.Trie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import work.pangbai.r2toolm.databinding.LayoutR2editorviewBinding;
import work.pangbai.r2toolm.tool.Init;
import work.pangbai.r2toolm.tool.ThreadUtil;
import work.pangbai.r2toolm.tool.util;

public class R2 {
    public JSONArray symListJson;
    public JSONArray funListJson;
    public JSONArray strListJson;
    public JSONArray stringsListJson;
    public TreeMap<String, String> symbolsMap = new TreeMap<>();

    //  public TreeMap<String, String> stringMap = new TreeMap<>();

    public ArrayList<Map.Entry<String, String>> funList = new ArrayList<>();
    public ArrayList<Map.Entry<String, String>> symList = new ArrayList<>();
    public ArrayList<Map.Entry<String, String>> stringList = new ArrayList<>();

    public Trie trie;
    public Trie.TrieBuilder trieBuilder;
    public static R2 current;
    public String preFun;
    //   JSONArray symList;
    LayoutR2editorviewBinding pseudoc;
    String file;


    public static native long r2pipeNew();

    public static native String r2pipeCmd(long core, String cmd);

    public static native void r2pipeFree(long core);

    private long core;

    public R2(LayoutR2editorviewBinding pseudoc) throws Exception {
        this.pseudoc = pseudoc;
        this.core = r2pipeNew();

        if (this.core == 0) {
            throw new Exception("Cannot initialize r2pipe-jni");
        }
      //  this.cmd("e scr.interactive=false");
        this.cmd("e scr.utf8=1");
        // Remove hex
        this.cmd("e asm.bytes=false");

        // Enble modify buffer

        // Important ! enable Html output
        this.cmd("e scr.html=true");
        this.cmd("e scr.color=2");
        this.cmd("e dir.projects="+ Init.projectPath);
        current = this;



    }

    public void setR2(String file) throws Exception {
        //this(pseudoc);
        this.file=file;

        this.cmd("o+ " + file);
     //   this.cmd("e io.cache=true");


        this.cmd("aaa");
        // Draw vertical line
        InitData();
    }

    public void InitData() throws Exception{
        symListJson = cmdj("fs symbols;fj");
        funListJson = cmdj("aflj");
        strListJson = cmdj("fs strings;fj");


        // GET String list
        stringsListJson = cmdj("izj");


        setSymbolsMap();
        setStrMap();
    }


    public synchronized   String  cmd(String str) throws Exception {


        Log.e("R2_EXECUTE", str);
        String res = r2pipeCmd(this.core, str);
        if (res != null && !res.isBlank()) Log.e("R2_RETURN", res);
        return res;
    }

    public JSONArray cmdj(String str) throws Exception {
        return new JSONArray(cmd(str));
    }


    public void setSymbolsMap() {
        trieBuilder = Trie.builder().ignoreOverlaps();

        addToTreeMap(symListJson, symList);
        addToTreeMap(funListJson, funList);
        addToTreeMap(strListJson, null);


        trie = trieBuilder.build();

    }

    public void addToTreeMap(JSONArray jsonArray, ArrayList<Map.Entry<String, String>> list) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);

                symbolsMap.put(jsonObject.getString("name"), jsonObject.getString("offset"));
                trieBuilder.addKeyword(jsonObject.getString("name"));
                if (list != null)
                    list.add(Map.entry(jsonObject.getString("name"), jsonObject.getString("offset")));


            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //       Log.e("Json", jsonObject.toString());
        }

    }


    @SuppressLint("SuspiciousIndentation")
    public void setStrMap() {
        for (int i = 0; i < stringsListJson.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = stringsListJson.getJSONObject(i);
                stringList.add(Map.entry(jsonObject.getString("string"), jsonObject.getString("vaddr")));

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            //     Log.e("Json", jsonObject.toString());
        }

    }



    public String disFunSym(String str) throws Exception {


        return disFunAddr(getFunAddrBySym(str));
    }


    public String getFunAddrBySym(String sym) {
        return symbolsMap.get(sym);
    }

    public String getAsmAtAddr(String addr, int len) throws Exception {
        JSONArray array = cmdj("pdj " + len + " @" + addr);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length(); i++) {
            builder.append(array.getJSONObject(i).getString("disasm")).append("\n");
        }
        return builder.toString().replaceAll("&nbsp;", " ").trim();
    }

    public String disFunAddr(String addr) throws Exception {
        preFun = addr;
        String r = cmd("pdf @" + addr);
        if (r == null || r.isEmpty()) {
            r = cmd("pd @" + addr);
        } else {
            ThreadUtil.thread(() -> {
                try {
                    String code = cmd("pdc @" + addr);
                    util.runOnUiThread(() -> pseudoc.editor.setTextH(code));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        }
        return r;
    }
    
    public void loadProject(File file) throws Exception {

       this.cmd("P " + file.getName());
        JSONObject json=new JSONObject(this.cmd("ij") ) ;
        this.file=json.getJSONObject("core").getString("file");
        Log.e(getClass().getName(),this.file);
       this.InitData();
    }

    public boolean saveProject(){
        try{
            this.cmd("Ps "+new File(file).getName() );
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean applyPatch(){
        try {
            this.cmd("wci");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean renameFun(String name, String new_name) {
        try {
            if (symbolsMap.containsKey(new_name)) return false;
            String addr = symbolsMap.get(name);
            cmd("afn " + new_name + " " + addr);
            symbolsMap.remove(name);
            symbolsMap.put(new_name, addr);
            trieBuilder.addKeyword(new_name);
            trie = trieBuilder.build();


            if (preFun.equals(name)) preFun = new_name;
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void patchAsmAt(String addr, String asm) {
        asm = asm.replaceAll("\n", ";");
        Log.e("dis", asm);
        try {
            cmd("s " + addr);
            cmd("\"wa " + asm + "\"");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void finalize() throws Throwable {
        quit();
    }

    public void quit() throws Exception {
        r2pipeFree(this.core);
        this.core = 0;
        this.pseudoc=null;
    }
}
