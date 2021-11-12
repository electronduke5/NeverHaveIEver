package com.example.neverhaveiever_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private boolean isCheckedDone;
    private boolean isCheckedDone2;
    private boolean isCheckedDoneKum;

    LottieAnimationView lottieParty;
    //    LottieAnimationView checkedDone;
//    LottieAnimationView checkedDone2;
//    LottieAnimationView checkedDoneKum;
    LottieAnimationView lottieKiss;

    RelativeLayout firstLayout;
    CardView cardKiss;
    RelativeLayout layoutKazak;

    Button btnStart;

    TextView txtCountStarting;
    TextView txtCountAdult;
    TextView txtCountKum;

    RadioButton rbStarting;
    RadioButton rbAdult;
    RadioButton rbKum;

    TextView txtCards;
    TextView txtSets;

    ImageView btnRules;

    RelativeLayout viewCards;
    RelativeLayout viewSets;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtCountStarting = findViewById(R.id.txtCountStarting);
        txtCountStarting.setText(String.format("%s карт", getResources().getStringArray(R.array.Actions).length));

        txtCountAdult = findViewById(R.id.txtCountAdult);
        txtCountAdult.setText(String.format("%s карт", getResources().getStringArray(R.array.ActionsForAdult).length));

        txtCountKum = findViewById(R.id.txtCountKum);
        txtCountKum.setText(String.format("%s карт", getResources().getStringArray(R.array.ActionsForKum).length));


        rbStarting = findViewById(R.id.rbStarting);
        rbAdult = findViewById(R.id.rbAdult);
        rbKum = findViewById(R.id.rbKum);

        txtCards = findViewById(R.id.txtCards);
        txtSets = findViewById(R.id.txtSets);

        btnRules = findViewById(R.id.btnRules);

        viewCards = findViewById(R.id.viewCards);
        viewSets = findViewById(R.id.viewSet);


//        checkedDone = findViewById(R.id.btnChecked);
//         checkedDone2 = findViewById(R.id.btnChecked2);
//         checkedDoneKum = findViewById(R.id.btnCheckedKum);
        lottieParty = findViewById(R.id.lottieParty);
        lottieKiss = findViewById(R.id.lottieKiss);

        List<Boolean> listBool = new ArrayList<>();
        List<String> selectedCategories = new ArrayList<>();

        btnStart = findViewById(R.id.btnStart);

        cardKiss = findViewById(R.id.cardKiss);

        firstLayout = findViewById(R.id.firstLayout);
        layoutKazak = findViewById(R.id.layoutKazak);


        cardKiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbAdult.setChecked(!rbAdult.isChecked());
                txtSets.setText(CalcSets());
                txtCards.setText(CalcCards());

                if(rbAdult.isChecked()){
                    lottieKiss.setSpeed(1);
                    lottieKiss.playAnimation();
                }
                playAnimation_StartButton();

            }
        });

        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbStarting.setChecked(!rbStarting.isChecked());

                txtSets.setText(CalcSets());
                txtCards.setText(CalcCards());

                playAnimation_StartButton();

                if(rbStarting.isChecked()){
                    lottieParty.setSpeed(1);
                    lottieParty.playAnimation();
                }

            }
        });
        layoutKazak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbKum.setChecked(!rbKum.isChecked());
                txtCards.setText(CalcCards());
                txtSets.setText(CalcSets());
                playAnimation_StartButton();
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                Log.i("Result", String.valueOf(isCheckedDone));

                intent.putExtra("First", rbStarting.isChecked());
                intent.putExtra("Second", rbAdult.isChecked());
                intent.putExtra("Kum", rbKum.isChecked());
                startActivity(intent);
                finish();
            }
        });

        btnRules.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, RulesActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void playAnimation_StartButton() {
        if ((rbStarting.isChecked() ^ rbAdult.isChecked()) ||
                (rbAdult.isChecked() ^ rbKum.isChecked()) ||
                (rbStarting.isChecked() ^ rbKum.isChecked())) {

            btnStart.setEnabled(true);
            viewCards.setBackgroundColor(getResources().getColor(R.color.christmas_start));
            viewSets.setBackgroundColor(getResources().getColor(R.color.christmas_start));
//            viewCards.setBackground((Drawable) getResources().getDrawable(R.drawable.gradient));
//            viewSets.setBackground((Drawable) getResources().getDrawable(R.drawable.gradient));


        } else if (!rbStarting.isChecked() && !rbAdult.isChecked() && !rbKum.isChecked()) {


            btnStart.setEnabled(false);
            viewCards.setBackgroundColor(getResources().getColor(R.color.christmas_end));
            viewSets.setBackgroundColor(getResources().getColor(R.color.christmas_end));
//            viewCards.setBackground((Drawable) getResources().getDrawable(R.drawable.gradient_red));
//            viewSets.setBackground((Drawable) getResources().getDrawable(R.drawable.gradient_red));
        }

    }

    private String CalcCards() {
        int allCards = 0;

        if (rbStarting.isChecked())
            allCards += getResources().getStringArray(R.array.Actions).length;

        if (rbAdult.isChecked())
            allCards += getResources().getStringArray(R.array.ActionsForAdult).length;

        if (rbKum.isChecked())
            allCards += getResources().getStringArray(R.array.ActionsForKum).length;

        return String.valueOf(allCards);
    }

    private String CalcSets() {
        int allSets = 0;

        if (rbStarting.isChecked())
            allSets++;
        if (rbAdult.isChecked())
            allSets++;
        if (rbKum.isChecked())
            allSets++;

        return String.valueOf(allSets);

    }
}