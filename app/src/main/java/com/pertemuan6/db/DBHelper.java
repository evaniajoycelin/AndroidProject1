package com.pertemuan6.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_INVENTORY =
            "CREATE TABLE IF NOT EXISTS inventory ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "qty INTEGER" +
                    ")";
    private static final String SQL_DROP_INVENTORY =
            "DROP TABLE IF EXISTS inventory";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_INVENTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_INVENTORY);
    }
}
