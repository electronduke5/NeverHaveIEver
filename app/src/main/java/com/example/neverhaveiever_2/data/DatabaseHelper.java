package com.example.neverhaveiever_2.data;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.neverhaveiever_2.data.QuestionContract.CustomQuestion;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    /**
     * Имя файла базы данных
     */
    private static final String DATABASE_NAME = "questions.db";

    /**
     * Версия базы данных. При изменении схемы увеличить на единицу
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Конструктор {@link DatabaseHelper}.
     *
     * @param context Контекст приложения
     */
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Вызывается при создании базы данных
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

       // db.execSQL("DROP TABLE " + CustomQuestion.TABLE_NAME + ";");
        //Строка для создания БД
        String SQL_CREATE_QUESTIONS_TABLE =
                "CREATE TABLE " + CustomQuestion.TABLE_NAME + "("
                        + CustomQuestion._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + CustomQuestion.COLUMN_QUESTION + " TEXT NOT NULL);";
        //Запускаем создание таблицы
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    /**
     * Вызывается при обовлении схемы БД
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + CustomQuestion.TABLE_NAME);
        // Создаём новую таблицу
        onCreate(db);
    }

    public long size(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,CustomQuestion.TABLE_NAME);
        db.close();
        return count;
    }
}