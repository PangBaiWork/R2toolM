package work.pangbai.r2toolm.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.r2.R2;

public class XrefListAdapter extends RecyclerView.Adapter<XrefHolder> {


    XrefItemAction xrefItemAction;
    public JSONArray xrefJsons;


    @SuppressLint("NotifyDataSetChanged")
    public void setData(String addr) throws Exception {

        Log.e(getClass().getName(),addr);

        xrefJsons = R2.current.cmdj("axtj @" + addr);


        for (int i = 0; i < xrefJsons.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = xrefJsons.getJSONObject(i);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            Log.e("Json", jsonObject.toString());
        }
        notifyDataSetChanged();

    }

    public XrefListAdapter(XrefItemAction symItemAction) {
        this.xrefItemAction = symItemAction;
    }

    @NonNull
    @Override
    public XrefHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                //LayoutInflater.from(parent.getContext()).inflate(R.layout.xref_list_item,parent,false);
              View.inflate(parent.getContext(), R.layout.xref_list_item, null);

        XrefHolder holder = new XrefHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull XrefHolder holder, int position) {
        String tmp;
        String addr = "";
        try {
            JSONObject jsonObject = xrefJsons.getJSONObject(position);
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String key = it.next();
                if (key.equals("from")) {
                    addr = jsonObject.getString(key);
                    holder.xrefFun.setText(addr);
                } else if (key.equals("opcode")) {
                    tmp = jsonObject.getString(key);
                    holder.xrefAsm.setText(tmp.replace("&nbsp;"," "));
                } else if (key.equals("fcn_addr")) {
                    addr = jsonObject.getString(key);
                } else if (key.equals("fcn_name")) {
                    tmp = jsonObject.getString(key);
                    holder.xrefFun.setText(tmp);
                }


            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        String finalAddr = addr;
        holder.item.setOnClickListener(v -> xrefItemAction.onItemClick(finalAddr));


        // R2.current.funMap.
        //    Map.Entry<String, Integer> entry = getEntryByIndex(map, position);
        // R2.current.funMap.entrySet().


        //.(position).toString());
    }

    @Override
    public int getItemCount() {
        return xrefJsons.length();
    }


    public interface XrefItemAction {
        public void onItemClick(String addr);
    }

}

class XrefHolder extends RecyclerView.ViewHolder {
    TextView xrefFun;
    TextView xrefAsm;
    View item;
    // LinearLayout mItemIcon;


    public XrefHolder(@NonNull View itemView) {
        super(itemView);
        xrefFun = itemView.findViewById(R.id.xref_fun);
        xrefAsm = itemView.findViewById(R.id.xref_asm);
        item = itemView.findViewById(R.id.xref_item);
        //       mItemIcon = itemView.findViewById(R.id.list_item_icon);
    }


}

