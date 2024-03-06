package dev.amitb.productcomparisonapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import dev.amitb.productcomparison.Item;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ObjectViewHolder> {

    private final List<Item> items;
    private final Context context;

    public ItemAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_view,parent,false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, int position) {
        Item item = getItem(position);
        holder.bind(item);
    }

    private Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items == null? 0: items.size();
    }

    public static class ObjectViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView TXT_name, TXT_brand, TXT_weight, TXT_price;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            TXT_name = itemView.findViewById(R.id.RV_Name);
            TXT_brand = itemView.findViewById(R.id.RV_Brand);
            TXT_weight = itemView.findViewById(R.id.RV_amount);
            TXT_price = itemView.findViewById(R.id.RV_Price);
        }

        public void bind(Item item) {
            TXT_name.setText(""+item.getName());
            TXT_brand.setText(""+item.getBrand());
            TXT_weight.setText(item.getWeight()+"g");
            TXT_price.setText(item.getCost()+"$");
        }
    }
}
