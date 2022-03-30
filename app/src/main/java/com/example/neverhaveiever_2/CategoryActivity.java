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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.neverhaveiever_2.data.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    Button btnStart;
    Button btnEditCustom;

    //Общее количество выбранных карточек и наборов
    TextView txtCards;
    TextView txtSets;

    ImageView btnRules;

    RelativeLayout viewCards;
    RelativeLayout viewSets;

    //Начальный сет
    LottieAnimationView lottieParty;
    RelativeLayout firstLayout;
    TextView txtCountStarting;
    RadioButton rbStarting;

    //Крутой сет
    LottieAnimationView lottieKiss;
    RadioButton rbAdult;
    CardView cardKiss;
    TextView txtCountAdult;

    //Кумылженский сет
    RelativeLayout layoutKazak;
    RadioButton rbKum;
    TextView txtCountKum;

    //Новогодний сет
    LottieAnimationView lottieSnowman;
    RelativeLayout layoutWinter;
    RadioButton rbWinter;
    TextView txtCountWinter;

    //Кастомный сет
    LottieAnimationView lottieCustom;
    RelativeLayout layoutCustom;
    RadioButton rbCustom;
    TextView txtCountCustom;

    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        DeclensionCountCard declensionCountCard = new DeclensionCountCard();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtCountStarting = findViewById(R.id.txtCountStarting);
        txtCountStarting.setText(declensionCountCard.GetCase(getResources().getStringArray(R.array.Actions).length));

        txtCountAdult = findViewById(R.id.txtCountAdult);
        txtCountAdult.setText(declensionCountCard.GetCase(getResources().getStringArray(R.array.ActionsForAdult).length));

        txtCountKum = findViewById(R.id.txtCountKum);
        txtCountKum.setText(declensionCountCard.GetCase(getResources().getStringArray(R.array.ActionsForKum).length));

        txtCountWinter = findViewById(R.id.txtCountWinter);
        txtCountWinter.setText(declensionCountCard.GetCase(getResources().getStringArray(R.array.ActionsForNewYear).length));

        txtCountCustom = findViewById(R.id.txtCountCustom);
        txtCountCustom.setText(declensionCountCard.GetCase((int)dbHelper.size()));


        rbStarting = findViewById(R.id.rbStarting);
        rbAdult = findViewById(R.id.rbAdult);
        rbKum = findViewById(R.id.rbKum);
        rbWinter = findViewById(R.id.rbWinter);
        rbCustom = findViewById(R.id.rbCustom);

        //Текст с количеством выбранных карт
        txtCards = findViewById(R.id.txtCards);
        //Текст с  количеством выбранных наборов
        txtSets = findViewById(R.id.txtSets);

        btnRules = findViewById(R.id.btnRules);
        btnEditCustom = findViewById(R.id.btnEditCustom);
        btnEditCustom.setOnClickListener(view ->{
            Intent intent = new Intent(CategoryActivity.this, CustomQuestionsActivity.class);
            startActivity(intent);
            finish();
        });

        viewCards = findViewById(R.id.viewCards);
        viewSets = findViewById(R.id.viewSet);

        lottieParty = findViewById(R.id.lottieParty);
        lottieKiss = findViewById(R.id.lottieKiss);
        lottieSnowman = findViewById(R.id.lottieSnowman);
        lottieCustom = findViewById(R.id.lottieCustom);

        List<Boolean> listBool = new ArrayList<>();
        List<String> selectedCategories = new ArrayList<>();

        btnStart = findViewById(R.id.btnStart);

        cardKiss = findViewById(R.id.cardKiss);
        firstLayout = findViewById(R.id.firstLayout);
        layoutKazak = findViewById(R.id.layoutKazak);
        layoutWinter = findViewById(R.id.layoutNewYear);
        layoutCustom = findViewById(R.id.layoutCustom);


        cardKiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rbAdult.setChecked(!rbAdult.isChecked());
                txtSets.setText(CalcSets());
                txtCards.setText(CalcCards());

                if(rbAdult.isChecked() && !lottieKiss.isAnimating()){
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

                if(rbStarting.isChecked() && !lottieParty.isAnimating()){
                    lottieParty.setSpeed(1);
                    lottieParty.playAnimation();
                }
            }
        });
        layoutKazak.setOnClickListener(view -> {

            rbKum.setChecked(!rbKum.isChecked());
            txtCards.setText(CalcCards());
            txtSets.setText(CalcSets());
            playAnimation_StartButton();
        });

        layoutWinter.setOnClickListener( view -> {
                rbWinter.setChecked(!rbWinter.isChecked());
                txtCards.setText(CalcCards());
                txtSets.setText(CalcSets());
                playAnimation_StartButton();

                if (rbWinter.isChecked() && !lottieSnowman.isAnimating()){
                    lottieSnowman.setSpeed(1);
                    lottieSnowman.playAnimation();
                }
        });

        layoutCustom.setOnClickListener(view -> {
            rbCustom.setChecked(!rbCustom.isChecked());
            txtCards.setText(CalcCards());
            txtSets.setText(CalcSets());
            playAnimation_StartButton();

            if(rbCustom.isChecked() && !lottieCustom.isAnimating()){
                lottieCustom.setSpeed(1);
                lottieCustom.playAnimation();
            }
        });


        btnStart.setOnClickListener(view -> {

            Intent intent = new Intent(CategoryActivity.this, MainActivity.class);

            intent.putExtra("First", rbStarting.isChecked());
            intent.putExtra("Second", rbAdult.isChecked());
            intent.putExtra("Kum", rbKum.isChecked());
            intent.putExtra("Winter", rbWinter.isChecked());
            intent.putExtra("Custom", rbCustom.isChecked());
            startActivity(intent);
            finish();
        });

        btnRules.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, RulesActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void playAnimation_StartButton() {
        if ((rbStarting.isChecked() ||
                rbAdult.isChecked() ||
                rbKum.isChecked() ||
                rbWinter.isChecked() ||
                rbCustom.isChecked()) &&
                !txtCards.getText().equals("0")) {

            btnStart.setEnabled(true);
            viewCards.setBackgroundColor(getResources().getColor(R.color.christmas_start));
            viewSets.setBackgroundColor(getResources().getColor(R.color.christmas_start));

        } else if (!rbStarting.isChecked() &&
                !rbAdult.isChecked() &&
                !rbKum.isChecked() &&
                !rbWinter.isChecked() ||
                !rbCustom.isChecked() ||
                txtCards.getText().equals("0")) {

            btnStart.setEnabled(false);
            viewCards.setBackgroundColor(getResources().getColor(R.color.christmas_end));
            viewSets.setBackgroundColor(getResources().getColor(R.color.christmas_end));
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

        if(rbWinter.isChecked())
            allCards += getResources().getStringArray(R.array.ActionsForNewYear).length;

        if(rbCustom.isChecked())
            allCards += dbHelper.size();

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
        if(rbWinter.isChecked())
            allSets++;
        if(rbCustom.isChecked())
            allSets++;

        return String.valueOf(allSets);
    }
}