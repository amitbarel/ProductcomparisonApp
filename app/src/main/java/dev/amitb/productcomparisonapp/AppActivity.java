package dev.amitb.productcomparisonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import productComparison.Item;
import productComparison.ItemCallBack;
import productComparison.ItemController;

public class AppActivity extends AppCompatActivity {

    private AppCompatButton BTN_apb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        BTN_apb = findViewById(R.id.BTN_calculate);
        BTN_apb.setOnClickListener(v ->{
            new ItemController(new ItemCallBack() {
                @Override
                public void success(List<Item> items) {
                    for (Item item:items
                    ) {
                        Log.d("items", "Data: " + item.toString());
                    }
                }

                @Override
                public void success(Item item) {
                    Log.d("item", "Data: " + item.toString());
                }

                @Override
                public void error(String error) {

                }
            }).getAllItems();
        });
    }
}