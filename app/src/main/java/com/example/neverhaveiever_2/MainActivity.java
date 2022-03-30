package com.example.neverhaveiever_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    List<Boolean> listCategory;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listCategory = new ArrayList<>();
        btnBack = findViewById(R.id.btnBack1);

        //Добавляем в лист значения выбранных категорий
        Bundle argument = getIntent().getExtras();
        listCategory.add((Boolean) argument.get("First"));
        listCategory.add((Boolean) argument.get("Second"));
        listCategory.add((Boolean) argument.get("Kum"));
        listCategory.add((Boolean) argument.get("Winter"));
        listCategory.add((Boolean) argument.get("Custom"));

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        });

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(4)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(25)
                        .setRelativeScale(0.01f)
                );

        for (CardItem cardItem :
                Utils.getActionList(mContext, listCategory)) {
            mSwipeView.addView(new CardActivity(mContext, cardItem, mSwipeView));
        }
    }

    public void onCLick(View view) {
        mSwipeView.doSwipe(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }
}