package dev.amitb.productcomparisonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import productComparison.Item;
import productComparison.ItemCallBack;
import productComparison.ItemController;

public class AppActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppCompatButton BTN_apb;
    private Spinner prod_spinner;
    private String state;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<String> namesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        BTN_apb = findViewById(R.id.BTN_calculate);
        prod_spinner = findViewById(R.id.prod_spinner);
        loadSpinner();
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
                    Log.d("items", "Data: " + item.toString());
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

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}