package com.example.neverhaveiever_2;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.neverhaveiever_2.CustomQuestionModel.CustomQModel;
import com.example.neverhaveiever_2.data.DatabaseHelper;
import com.example.neverhaveiever_2.data.QuestionContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";
    private static final List<String> actions = new ArrayList<>();

    public static List<CardItem> getActionList(Context context, List<Boolean> listCategory) {
        List<CardItem> list = new ArrayList<>();
        actions.clear();

        if (listCategory.get(0)) {
            actions.addAll(Arrays.asList(context.getResources().getStringArray(R.array.Actions)));
        }
        if(listCategory.get(1)){
            actions.addAll(Arrays.asList(context.getResources().getStringArray(R.array.ActionsForAdult)));
        }
        if(listCategory.get(2)){
            actions.addAll(Arrays.asList(context.getResources().getStringArray(R.array.ActionsForKum)));
        }
        if(listCategory.get(3)){
            actions.addAll((Arrays.asList(context.getResources().getStringArray(R.array.ActionsForNewYear))));
        }
        if(listCategory.get(4)){
            actions.addAll(getCardsFromDB(context));
        }

        Collections.shuffle(actions);
        actions.add(0, "нажимал на кнопку 'Дальше', \nчтобы начать игру!");

        for(int i = 0; i < actions.size(); i++){
            list.add(new CardItem(actions.get(i),
                    getTxtNumberAction(i, actions.size()-1)));
        }
        return list;
    }

    private static String getTxtNumberAction(int numberAction, int countAction){
        return (String.format("%d/%d", numberAction, countAction));
    }

    private static List<String> getCardsFromDB(Context context){
        List<String> data = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        String[] projection = {QuestionContract.CustomQuestion._ID, QuestionContract.CustomQuestion.COLUMN_QUESTION};

        Cursor cursor = db.query(
                QuestionContract.CustomQuestion.TABLE_NAME,   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        try {
            int questionColumnIndex = cursor.getColumnIndex(QuestionContract.CustomQuestion.COLUMN_QUESTION);

            while (cursor.moveToNext()) {
                String currentQuestion = cursor.getString(questionColumnIndex);
                data.add(currentQuestion);
            }
        } finally {
            //Закрываем курсор
            cursor.close();
        }
        return data;
    }
}
