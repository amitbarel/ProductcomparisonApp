package dev.amitb.productcomparisonapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<String> names;
    public ProductAdapter(Context context, ArrayList<String> names) {
        this.context = context;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names != null ? names.size():0;
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_name, viewGroup, false);
        TextView itemName = rootView.findViewById(R.id.itemName);
        itemName.setText(names.get(i));

        return rootView;
    }
}
