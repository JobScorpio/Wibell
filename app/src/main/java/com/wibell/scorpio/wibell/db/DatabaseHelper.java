package com.wibell.scorpio.wibell.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Scorpio on 2016/9/7.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "wibell.db";
    public static final String TABLE_NAME = "wibell_data";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_FIELD_ID = "_id";
    public static final String TABLE_FIELD_IP = "ip";
    public static final String TABLE_FIELD_ALIAS = "alias";
    public static final String TABLE_FIELD_TOKEN = "token";

    public static final String CREATE_NOTE_TABLE = "create table "
            + TABLE_NAME
            + "(" + TABLE_FIELD_ID + " INTEGER PRIMARY KEY,"
            + TABLE_FIELD_IP + " VARCHAR(20),"
            + TABLE_FIELD_ALIAS + " VARCHAR(50),"
            + TABLE_FIELD_TOKEN + " VARCHAR(50));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
        // db.execSQL(CREATE_NEWS_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
