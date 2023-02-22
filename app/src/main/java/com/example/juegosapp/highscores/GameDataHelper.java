package com.example.juegosapp.highscores;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GameDataHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;
    private static final String LODB_TABLE = "lo_data";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    private static final String DMCODB_TABLE = "dmco_data";
    public static final String KEY_SCORE = "score";
    private static final String DATABASE_NAME = "game_data";
    private static final String LODB_TABLE_CREATE = "CREATE TABLE " + LODB_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT, "+ KEY_SCORE + " TEXT );";
    private static final String DMCODB_TABLE_CREATE = "CREATE TABLE " + DMCODB_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT, "+ KEY_SCORE + " TEXT );";


    public GameDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LODB_TABLE_CREATE);
        db.execSQL(DMCODB_TABLE_CREATE);
    }

    public long newScore(String name, String score, String game) {
        long newId = 0;
        String table;
        if (game.equals("LO")) {
            table = LODB_TABLE;
        } else {
            table = DMCODB_TABLE;
        }
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_SCORE, score);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(table, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e);
        }
        return newId;
    }

    public Scores query(int position, String game) {
        String query;
        switch (game) {
            case "LO":
                query = "SELECT  * FROM " + LODB_TABLE +
                        " ORDER BY " + KEY_SCORE + " ASC " +
                        "LIMIT " + position + ",1";
                break;
            case "DMCO":
                query = "SELECT  * FROM " + DMCODB_TABLE +
                        " ORDER BY " + KEY_SCORE + " DESC " +
                        "LIMIT " + position + ",1";
                break;
            default:
                return null;
        }

        Cursor cursor = null;
        Scores entry = new Scores();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            entry.setScore(cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry;
        }
    }

    public long count(String game) {
        String table;
        if (game.equals("LO")) {
            table = LODB_TABLE;
        } else {
            table = DMCODB_TABLE;
        }
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(GameDataHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + LODB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DMCODB_TABLE);
        onCreate(db);
    }
}
