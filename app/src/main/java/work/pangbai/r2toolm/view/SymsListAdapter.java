package work.pangbai.r2toolm.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import work.pangbai.r2toolm.R;
import work.pangbai.r2toolm.databinding.SymListBinding;
import work.pangbai.r2toolm.r2.R2;

public class SymsListAdapter extends RecyclerView.Adapter<SymHolder> implements View.OnClickListener, TextWatcher {
    ArrayList<Map.Entry<String, String>> entryList = new ArrayList<>();

    //String[] name;
    ArrayList<Map.Entry<String, String>> filterList = new ArrayList<>();

    SymItemAction symItemAction;
    SymListBinding binding;


    enum Table {
        function,symbol, string
    }

    Table state = Table.function;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fun_list_tab) {
            setData(Table.function);
            notifyDataSetChanged();
        }else if (id == R.id.sym_list_tab) {
            setData(Table.symbol);
            notifyDataSetChanged();
        } else if (id == R.id.str_list_tab) {
            setData(Table.string);
            notifyDataSetChanged();
        }
        TextView tx=(TextView) v;
        binding.funListTab.setTextColor(tx==binding.funListTab?Color.BLUE:Color.BLACK);
        binding.symListTab.setTextColor(tx==binding.symListTab?Color.BLUE:Color.BLACK);
        binding.strListTab.setTextColor(tx==binding.strListTab?Color.BLUE:Color.BLACK);

    }


    void setData(Table state) {
        if (R2.current == null) return;
     //   TreeMap<String, String> map = null;
        if (state == Table.function) {
           entryList=R2.current.funList;
        } else if (state == Table.symbol) {
            entryList=R2.current.symList;
        } else if (state == Table.string) {
            entryList=R2.current.stringList;
        }
        this.state = state;

        filterList = new ArrayList<>(entryList);
        // entryArray =  R2.current.funMap.entrySet().toArray(new Map.Entry[0]);
     //   name = map.keySet().toArray(new String[0]);
    }

    public SymsListAdapter(SymItemAction symItemAction, SymListBinding binding) {
        this.symItemAction=symItemAction;
        this.binding=binding;


        setData(Table.function);
        binding.funListTab.setTextColor(Color.BLUE);


    }

    @NonNull
    @Override
    public SymHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.sym_list_item, null);

        SymHolder holder = new SymHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SymHolder holder, int position) {
        // R2.current.funMap.
        //    Map.Entry<String, Integer> entry = getEntryByIndex(map, position);
        // R2.current.funMap.entrySet().

        holder.mItemName.setText(filterList.get(position).getKey());
        //    holder.mItemName.setText(name[position]);
        holder.item.setOnClickListener(v -> symItemAction.onItemClick(filterList.get(position).getValue()));
     //   holder.addr = filterList.get(position).getValue();
        //.(position).toString());
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString();
        if (str.isBlank()) {
            filterList = new ArrayList<>(entryList);
            return;
        }
        filterList.clear();
        for (Map.Entry<String, String> item : entryList) {
            if (item.getKey().toLowerCase().contains(str.toLowerCase())) {
                filterList.add(item);
            }
        }
        notifyDataSetChanged();

    }

    public interface SymItemAction {
         void onItemClick(String addr);
    }

}

class SymHolder extends RecyclerView.ViewHolder {
    TextView mItemName;
    View item;
  //  String addr;
    // LinearLayout mItemIcon;


    public SymHolder(@NonNull View itemView) {
        super(itemView);
        mItemName = itemView.findViewById(R.id.sym_text);
        item = itemView.findViewById(R.id.sym_item);
        //       mItemIcon = itemView.findViewById(R.id.list_item_icon);
    }


   
}

