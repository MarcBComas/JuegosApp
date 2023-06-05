package com.example.juegosapp.data;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GameDataHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;
    private static final String LODB_TABLE = "lo_data";
    private static final String DMCODB_TABLE = "dmco_data";
    private static final String USER_TABLE = "user_data";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TIME = "time";
    private static final String KEY_PASSWORD = "password";
    private static final String DATABASE_NAME = "game_data";
    private static final String LODB_TABLE_CREATE = "CREATE TABLE " + LODB_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT, "+ KEY_TIME + " INTEGER, "+ KEY_SCORE + " INTEGER );";
    private static final String DMCODB_TABLE_CREATE = "CREATE TABLE " + DMCODB_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT, "+ KEY_TIME + " INTEGER, "+ KEY_SCORE +" INTEGER );";

    private static final String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " +
            KEY_NAME + " TEXT, "+ KEY_PASSWORD + " TEXT );";


    public GameDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LODB_TABLE_CREATE);
        db.execSQL(DMCODB_TABLE_CREATE);
        db.execSQL(USER_TABLE_CREATE);
    }

    public boolean signIn(String name, String password) {
        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            c = mReadableDB.rawQuery("select * from "+USER_TABLE, null, null);
            if (c.getCount() != 0) {
                c.moveToFirst();
                while (c.moveToNext()) {
                    String username = c.getString(c.getColumnIndex("name"));
                    String userpassword = c.getString(c.getColumnIndex("password"));
                    if (name.equals(username) && password.equals(userpassword)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            c.close();
        }
        return false;
    }

    public boolean signUp(String name, String password) {
        Cursor c = null;
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            c = mReadableDB.rawQuery("select * from "+USER_TABLE, null, null);
            if (c.getCount() != 0) {
                c.moveToFirst();
                while (c.moveToNext()) {
                    String username = c.getString(c.getColumnIndex("name"));
                    if (name.equals(username)) {
                        return false;
                    }
                }
            }
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_PASSWORD, password);
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.insert(USER_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e);
        } finally {
            c.close();
        }
        return true;
    }

    public void newScore(String name, String time, int other, String game) {
        String table;
        ContentValues values = new ContentValues();
        if (game.equals("LO")) {
            table = LODB_TABLE;
            values.put(KEY_NAME, name);
            values.put(KEY_TIME, time);
            values.put(KEY_SCORE, other);
        } else {
            table = DMCODB_TABLE;
            values.put(KEY_NAME, name);
            values.put(KEY_TIME, time);
            values.put(KEY_SCORE, other);
        }

        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            mWritableDB.insert(table, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e);
        }
    }

    public Scores gameQuery(int position, String game, String order) {
        String query = "";
        switch (order) {
            case "Tiempo":
                if (game.equals("LO")) {
                    query = "SELECT  * FROM " + LODB_TABLE +
                            " ORDER BY " + KEY_TIME + " ASC " +
                            "LIMIT " + position + ",1";
                } else if (game.equals("DMCO")) {
                    query = "SELECT  * FROM " + DMCODB_TABLE +
                            " ORDER BY " + KEY_TIME + " ASC " +
                            "LIMIT " + position + ",1";
                }
                break;
            case "Nombre":
                if (game.equals("LO")) {
                    query = "SELECT  * FROM " + LODB_TABLE +
                            " ORDER BY " + KEY_NAME + " ASC " +
                            "LIMIT " + position + ",1";
                } else if (game.equals("DMCO")) {
                    query = "SELECT  * FROM " + DMCODB_TABLE +
                            " ORDER BY " + KEY_NAME + " ASC " +
                            "LIMIT " + position + ",1";
                }
                break;
            case "Puntuacion":
                query = "SELECT  * FROM " + DMCODB_TABLE +
                        " ORDER BY " + KEY_SCORE + " ASC " +
                        "LIMIT " + position + ",1";
                break;
            case "Pulsaciones":
                query = "SELECT  * FROM " + LODB_TABLE +
                        " ORDER BY " + KEY_SCORE + " ASC " +
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
            entry.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            entry.setOther(cursor.getString(cursor.getColumnIndex(KEY_SCORE)));
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
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
