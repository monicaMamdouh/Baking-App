package com.example.monica.finalprojectthree.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by monica on 6/12/2017.
 */

public class IngredientDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ingredient.db";

    private static final int DATABASE_VERSION = 16;

    public IngredientDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + IngredientContract.IngredientEntry.TABLE_NAME + " (" +
                IngredientContract.IngredientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_ID + " INTEGER NOT NULL, " +
                IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_RECIPE_NAME+ "  TEXT UNIQUE , " +
                IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_MEASURE+ " TEXT , " +
                IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_INGREDIENT+ " TEXT , " +
                IngredientContract.IngredientEntry.COLUMN_INGREDIENTS_QUANTITY+ " TEXT )";

        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IngredientContract.IngredientEntry.TABLE_NAME);
        onCreate(db);
    }
}

