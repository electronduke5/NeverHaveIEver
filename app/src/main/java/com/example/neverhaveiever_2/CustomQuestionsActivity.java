package com.example.neverhaveiever_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.neverhaveiever_2.CustomQuestionModel.CustomQModel;
import com.example.neverhaveiever_2.data.DatabaseHelper;
import com.example.neverhaveiever_2.data.QuestionContract;

import java.util.ArrayList;

public class CustomQuestionsActivity extends AppCompatActivity {

    EditText edtCustomQuestion;
    Button btnAddQuestion;
    ImageView btnBack;
    Button btnDeleteAll;

    RecyclerView recyclerView;
    ArrayList<CustomQModel> models = new ArrayList<>();

    private DatabaseHelper mDbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_questions);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.recyclerView);
        edtCustomQuestion = findViewById(R.id.edtQuestion);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);

        mDbHelper = new DatabaseHelper(this);
        updateRecyclerView();

        btnAddQuestion = findViewById(R.id.btn_add_custom);
        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCustomQuestion.getText().toString().equals("")) {
                    Toast.makeText(v.getContext(), "У вас поле не заполнено!", Toast.LENGTH_LONG).show();
                } else {
                    insertQuestion();
                    updateRecyclerView();
                }
            }
        });

        btnBack = findViewById(R.id.btnBackAction);
        btnBack.setOnClickListener(view ->{
            onBackPressed();
        });

        btnDeleteAll.setOnClickListener(view ->{
            deleteAll();
        });
    }

    private void updateRecyclerView() {

        models = readDataFromDB();
        for (CustomQModel model : models) {
            Log.d("Data", model.getID() + " - " + model.getText());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, models);
        recyclerView.setAdapter(adapter);
    }

    private void insertQuestion() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о госте является значениями ключей
        ContentValues values = new ContentValues();
        if (!edtCustomQuestion.getText().toString().equals("")) {
            values.put(QuestionContract.CustomQuestion.COLUMN_QUESTION, edtCustomQuestion.getText().toString());
            db.insert(QuestionContract.CustomQuestion.TABLE_NAME, null, values);
        } else {
            Toast.makeText(this, "У вас поле не заполнено!", Toast.LENGTH_LONG).show();
        }
    }


    private ArrayList<CustomQModel> readDataFromDB() {

        ArrayList<CustomQModel> data = new ArrayList<>();
        //Создаем и открываем для чтения БД
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Зададим условие для выборки - список столбцов
        String[] projection = {QuestionContract.CustomQuestion._ID, QuestionContract.CustomQuestion.COLUMN_QUESTION};

        // Делаем запрос
        Cursor cursor = db.query(
                QuestionContract.CustomQuestion.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки
        Log.d("SQL", "Таблица содержит " + cursor.getCount() + "записей.\n\n");
        try {
            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex(QuestionContract.CustomQuestion._ID);
            int questionColumnIndex = cursor.getColumnIndex(QuestionContract.CustomQuestion.COLUMN_QUESTION);

            //Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentID = cursor.getInt(idColumnIndex);
                String currentQuestion = cursor.getString(questionColumnIndex);
                data.add(new CustomQModel(currentID, currentQuestion));
            }
        } finally {
            cursor.close();
        }
        return data;
    }

    private void deleteAll(){
        models.clear();
        recyclerView.removeAllViews();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + QuestionContract.CustomQuestion.TABLE_NAME + ";");
        db.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CustomQuestionsActivity.this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }
}