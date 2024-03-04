package dev.amitb.productcomparisonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class OpeningActivity extends AppCompatActivity {

    private AppCompatButton nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        initViews();
        nextBtn.setOnClickListener(v -> {
            startActivity(new Intent(OpeningActivity.this, AppActivity.class));
        });
    }

    public void initViews(){
        nextBtn = findViewById(R.id.BTN_continue);
    }


}