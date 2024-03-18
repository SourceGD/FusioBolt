package com.example.fusionbolt;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ma_base_de_donnees.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE mes_donnees (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT," +
            "age INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // fonction pour mettre à jour la base de donnée, still don't know how to do
    }
}

