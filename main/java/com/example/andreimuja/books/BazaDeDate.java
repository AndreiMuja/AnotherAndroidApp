package com.example.andreimuja.books;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BazaDeDate extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "carti.db";
    private static final String TABLE_CARTE = "carte";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AUTOR = "autor";
    private static final String COLUMN_TITLU = "titlu";
    private static final String COLUMN_GEN = "gen";
    private static final String COLUMN_PRET = "pret";
    private static final String COLUMN_ASPECT = "aspect";
    private static final String COLUMN_DATA = "data";

    public BazaDeDate(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CARTE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_AUTOR + " TEXT, "
                + COLUMN_TITLU + " TEXT, "
                + COLUMN_GEN + " TEXT, "
                + COLUMN_PRET + " DOUBLE, "
                + COLUMN_ASPECT + " TEXT, "
                + COLUMN_DATA + " TEXT"
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARTE);
        onCreate(db);
    }

    public boolean adaugaCarte(Carte carte) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_AUTOR, carte.getAutor());
            values.put(COLUMN_TITLU, carte.getTitlu());
            values.put(COLUMN_GEN, carte.getGen());
            values.put(COLUMN_PRET, carte.getPret());
            values.put(COLUMN_ASPECT, carte.getAspect().toString());
            values.put(COLUMN_DATA, carte.getDataPublicarii().toString());
            db.insert(TABLE_CARTE, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
