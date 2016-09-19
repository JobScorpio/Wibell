package com.wibell.scorpio.wibell.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wibell.scorpio.wibell.bean.PersonBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteDatabase {
    private final DatabaseHelper dbHelper;

    public NoteDatabase(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);
    }

    public void addPerson(PersonBean person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + dbHelper.TABLE_NAME + "(" + dbHelper.TABLE_FIELD_IP + ", " + dbHelper.TABLE_FIELD_ALIAS + ", " + dbHelper.TABLE_FIELD_TOKEN + ") VALUES (?, ?, ?);", new Object[]{person.getIp(), person.getAlias(), person.getToken()});
    }

    public List<HashMap<String, String>> findAll() {
        List<HashMap<String, String>> list = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + dbHelper.TABLE_NAME, null);
        if (cursor.getCount() > 0) {
            list = new ArrayList<HashMap<String, String>>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(dbHelper.TABLE_FIELD_ID));
                String ip = cursor.getString(cursor.getColumnIndex(dbHelper.TABLE_FIELD_IP));
                String alias = cursor.getString(cursor.getColumnIndex(dbHelper.TABLE_FIELD_ALIAS));
                String token = cursor.getString(cursor.getColumnIndex(dbHelper.TABLE_FIELD_TOKEN));

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", id);
                map.put("ip", ip);
                map.put("alias", alias);
                map.put("token", token);
                list.add(map);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

}
