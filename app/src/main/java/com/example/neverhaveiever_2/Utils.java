package com.example.neverhaveiever_2;


import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utils {

    private static final String TAG = "Utils";

    public static List<CardItem> getActionList(Context context, List<Boolean> listCategory) {
        List<CardItem> list = new ArrayList<>();
        List<String> actions = new ArrayList<>();

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

//        List<String> actions = new ArrayList<>(Arrays.asList(context.getResources().getStringArray(R.array.Actions)));
        Collections.shuffle(actions);
        actions.add(0, "нажимал на кнопку 'Дальше', \nчтобы начать игру!");

        for(int i = 0; i < actions.size(); i ++){
            list.add(new CardItem(actions.get(i),
                    getTxtNumberAction(i, actions.size())));
        }

//        for (int i = 0; i < context.getResources().getStringArray(R.array.Actions).length; i++) {
//            list.add((new CardItem(
//                    actions.get(i),
//                    getTxtNumberAction(i, context.getResources().getStringArray(R.array.Actions).length)
//            )));
//        }
        return list;
    }

    private static String getTxtNumberAction(int numberAction, int countAction){
        return (String.format("%d/%d", numberAction, countAction));
    }
}
