package com.example.neverhaveiever_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.neverhaveiever_2.CardActivity;
import com.example.neverhaveiever_2.CardItem;
import com.example.neverhaveiever_2.Utils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;

    private String startCategory;
    private String coolCategory;

    //List<String> listCategory;
    List<Boolean> listCategory;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        listCategory = new ArrayList<>();
        btnBack = findViewById(R.id.btnBack1);


        //Добавляем в лист значения выбранных категорий
        Bundle argument = getIntent().getExtras();
        listCategory.add((Boolean) argument.get("First"));
        listCategory.add((Boolean) argument.get("Second"));
        listCategory.add((Boolean) argument.get("Kum"));
        listCategory.add((Boolean) argument.get("Winter"));



    btnBack.setOnClickListener(view ->{
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    });



        //btNext = findViewById(R.id.btnNext);
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();
//        btNext.setOnClickListener(view ->{
//            mSwipeView.doSwipe(true);
//        });

        mSwipeView.getBuilder()
                .setDisplayViewCount(4)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(25)
                        .setRelativeScale(0.01f)
                );

        for(CardItem cardItem :
                Utils.getActionList(mContext, listCategory)){
            mSwipeView.addView(new CardActivity(mContext,cardItem, mSwipeView));
        }

//        for (int i = 0; i< getResources().getStringArray(R.array.Actions).length; i++){
//
//            mSwipeView.addView(new CardActivity(mContext,itemList.get(i),mSwipeView),
//                    getTxtNumberAction(i,getResources().getStringArray(R.array.Actions).length)));
//        }
    }

    public void onCLick(View view){
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