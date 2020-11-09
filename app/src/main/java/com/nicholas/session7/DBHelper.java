package com.nicholas.session7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // 1. Nama DB
    private static final String DB_NAME = "mydb.db";
    // 2. Nama tabel
    private static final String NAMA_TABEL = "users";
    // 3. Versi sqlite
    private static final int VERSI_DB = 1;
    // 4. Column(s) / Attribute(s)
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_PWD = "user_pwd";

    SQLiteDatabase dbw = this.getWritableDatabase();

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSI_DB);
        SQLiteDatabase dbr = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + NAMA_TABEL + " (" +
                USER_ID + " INT PRIMARY KEY, " +
                USER_NAME + " VARCHAR(50), " +
                USER_PWD + " VARCHAR(50)" +
                ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + NAMA_TABEL;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    // CRUD Operations
    // C Create / INSERT
    public boolean InsertData(String username, String password) {
        Integer count = 1;
        ContentValues cv = new ContentValues();
        cv.put(USER_ID, count);
        cv.put(USER_NAME, username);
        cv.put(USER_PWD, password);
        count++;

        long result = dbw.insert(NAMA_TABEL, null, cv);
        if (result == -1) {
            return false;
        }
        return true;
    }

    // R Read / SELECT
    public Cursor getAll() {
        Cursor result = dbw.rawQuery("SELECT * " +
                "FROM users", null);
        return result;
    }

    // U Update / UPDATE
    public boolean UpdateData(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put(USER_PWD, password);

        long result = dbw.update(NAMA_TABEL, cv, "username = ?", new String[] {username});
        // SQL Injection -> Bind param
        // Prepared statement
        if (result == -1) {
            return false;
        }
        return true;
    }

    // D Delete / DELETE
    public boolean DeleteData(String username) {
        ContentValues cv = new ContentValues();

        long result = dbw.delete(NAMA_TABEL, "username = ?", new String[] {username});
        // SQL Injection -> Bind param
        // Prepared statement
        if (result == -1) {
            return false;
        }
        return true;
    }
}
