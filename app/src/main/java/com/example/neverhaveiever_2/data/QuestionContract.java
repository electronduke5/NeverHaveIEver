package com.example.neverhaveiever_2.data;

import android.provider.BaseColumns;

public final class QuestionContract {

    private QuestionContract(){};

    public static final class CustomQuestion implements BaseColumns {

        public final static String TABLE_NAME = "Questions";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_QUESTION = "questionText";
    }
}


