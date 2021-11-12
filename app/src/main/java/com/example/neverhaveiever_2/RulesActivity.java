package com.example.neverhaveiever_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class RulesActivity extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view ->{
            Intent intent = new Intent(RulesActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RulesActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }

}