package dev.amitb.productcomparisonapp;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import productComparison.Item;
import productComparison.ItemCallBack;
import productComparison.ItemController;

public class AppActivity extends AppCompatActivity {

    private AppCompatButton BTN_apb;
    private Spinner prod_spinner;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<String> namesList = new ArrayList<>();

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        BTN_apb = findViewById(R.id.BTN_calculate);
        prod_spinner = findViewById(R.id.prod_spinner);


        BTN_apb.setOnClickListener(v -> {
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
                    showNamesClick(namesList);
                }

                @Override
                public void success(Item item) {
                    Log.d("item", "Data: " + item.toString());
                }

                @Override
                public void error(String error) {

                }
            }).getByAlt(1);
        });

    }


    private void showNamesClick(ArrayList<String> namesList) {
        productAdapter = new ProductAdapter(this, namesList);
        prod_spinner.setAdapter(productAdapter);
    }

}