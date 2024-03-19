package com.example.fusionbolt;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "elementsDB";
    private static final int DATABASE_VERSION = 3;

    // Table Elements
    private static final String TABLE_ELEMENTS = "elements";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOGO = "logo";
    private static final String COLUMN_USED = "used"; // Colonne boolean pour indiquer si un élément a été utilisé

    // Table Relations
    private static final String TABLE_RELATIONS = "relations";
    private static final String COLUMN_RELATION_ID = "id";
    private static final String COLUMN_PARENT_NAME = "parent_name";
    private static final String COLUMN_CHILD_NAME = "child_name";
    private static final String COLUMN_RESULT_NAME = "result_name";

    private static final String CREATE_TABLE_ELEMENTS = "CREATE TABLE " + TABLE_ELEMENTS + "("
            + COLUMN_NAME + " TEXT PRIMARY KEY,"
            + COLUMN_LOGO + " TEXT,"
            + COLUMN_USED + " INTEGER DEFAULT 0" + ")"; // SQLite utilise 0 (faux) et 1 (vrai) pour boolean

    private static final String CREATE_TABLE_RELATIONS = "CREATE TABLE " + TABLE_RELATIONS + "("
            + COLUMN_PARENT_NAME + " TEXT,"
            + COLUMN_CHILD_NAME + " TEXT,"
            + COLUMN_RESULT_NAME + " TEXT,"
            + "PRIMARY KEY (" + COLUMN_PARENT_NAME + ", " + COLUMN_CHILD_NAME + "),"
            + "FOREIGN KEY (" + COLUMN_PARENT_NAME + ") REFERENCES " + TABLE_ELEMENTS + "(" + COLUMN_NAME + "),"
            + "FOREIGN KEY (" + COLUMN_CHILD_NAME + ") REFERENCES " + TABLE_ELEMENTS + "(" + COLUMN_NAME + "),"
            + "FOREIGN KEY (" + COLUMN_RESULT_NAME + ") REFERENCES " + TABLE_ELEMENTS + "(" + COLUMN_NAME + ")" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ELEMENTS);
        db.execSQL(CREATE_TABLE_RELATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELEMENTS);
        onCreate(db);
    }


    public void addElement(String name, String logo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_LOGO, logo);
        // Le champ "used" n'est pas spécifié ici, donc il utilisera la valeur par défaut
        db.insert(TABLE_ELEMENTS, null, values);
    }

    public void addRelation(String parentName, String childName, String resultName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PARENT_NAME, parentName);
        values.put(COLUMN_CHILD_NAME, childName);
        values.put(COLUMN_RESULT_NAME, resultName);
        db.insert(TABLE_RELATIONS, null, values);
    }

    public void setElementUsed(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USED, 1); // Marquer comme utilisé
        db.update(TABLE_ELEMENTS, values, COLUMN_NAME + " = ?", new String[]{name});
    }
    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ELEMENTS, null, null);
        db.delete(TABLE_RELATIONS, null, null);
    }
    public List<Element> getAllElements() {
        List<Element> elements = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ELEMENTS, new String[] { COLUMN_NAME, COLUMN_LOGO, COLUMN_USED }, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String logo = cursor.getString(cursor.getColumnIndex(COLUMN_LOGO));
                boolean used = cursor.getInt(cursor.getColumnIndex(COLUMN_USED)) > 0;
                elements.add(new Element(name, logo, used));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return elements;
    }
    public Map<String, String> getAllRelations() {
        Map<String, String> relations = new HashMap<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_PARENT_NAME + ", " + COLUMN_CHILD_NAME + ", " + COLUMN_RESULT_NAME + " FROM " + TABLE_RELATIONS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String parentName = cursor.getString(cursor.getColumnIndex(COLUMN_PARENT_NAME));
                String childName = cursor.getString(cursor.getColumnIndex(COLUMN_CHILD_NAME));
                String resultName = cursor.getString(cursor.getColumnIndex(COLUMN_RESULT_NAME));

                String key = parentName + "+" + childName;

                relations.put(key, resultName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return relations;
    }



}
