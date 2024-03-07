package dev.amitb.productcomparisonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import dev.amitb.productcomparison.Item;
import dev.amitb.productcomparison.ItemCallBack;
import dev.amitb.productcomparison.ItemController;

public class AppActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppCompatButton BTN_apb;
    private Spinner prod_spinner;
    private RecyclerView alternatives;
    private MaterialTextView betterBrand;
    private MaterialTextView betterPay;

    private String state;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<String> namesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        findViews();
        loadSpinner();
    }

    private void findViews() {
        BTN_apb = findViewById(R.id.BTN_calculate);
        prod_spinner = findViewById(R.id.prod_spinner);
        alternatives = (RecyclerView) findViewById(R.id.alternatives);
        betterBrand = findViewById(R.id.betterBrand);
        betterPay = findViewById(R.id.betterPrice);
    }

    private void loadSpinner() {
        new ItemController(new ItemCallBack() {
            @Override
            public void success(List<Item> items) {
                itemList.clear();
                itemList.addAll(items);
                namesList.clear();
                for (Item item : items
                ) {
                    namesList.add(item.getName());
                }
                setSpinner(namesList);
            }

            @Override
            public void success(Item item) {
                Log.d("item", "Data: " + item.toString());
            }

            @Override
            public void error(String error) {

            }
        }).getByAlt(1);
    }

    private void setSpinner(ArrayList<String> namesList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, namesList);
        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        prod_spinner.setAdapter(adapter);
        prod_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        state = parent.getItemAtPosition(position).toString();
        new ItemController(new ItemCallBack() {
            @Override
            public void success(List<Item> items) {
                List<Item> listByName = new ArrayList<>(items);
                setAdapters(listByName);
                BTN_apb.setOnClickListener(v->{
                    Item item = ItemController.whoIsBetter(listByName.get(0),listByName.get(1));
                    betterBrand.setText(item.getBrand() + "");
                    betterPay.setText(String.format("%.4f$ for g",item.getCost()/item.getWeight()));
                });
            }

            @Override
            public void success(Item item) {
            }

            @Override
            public void error(String error) {

            }
        }).getByName(state);
    }

    private void setAdapters(List<Item> items) {
        ItemAdapter itemAdapter = new ItemAdapter(items, this);
        alternatives.setLayoutManager(new GridLayoutManager(AppActivity.this, items.size()));
        alternatives.setAdapter(itemAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}