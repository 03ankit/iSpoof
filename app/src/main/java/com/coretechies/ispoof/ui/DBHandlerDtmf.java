package com.coretechies.ispoof.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandlerDtmf extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "DTMF.db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "DtmfList";

    private static final String ID = "id";

    private static final String Number = "number";

    private static final String Date = "date";


    public DBHandlerDtmf(android.content.Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Number + " TEXT,"
                + Date + " TEXT)";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewDtmf(String callnumber, long calldate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Number, callnumber);
        values.put(Date, calldate);
        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "succcesfully", Toast.LENGTH_SHORT).show();
        }

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    public Cursor DtmfreadAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    /*public void deleteDtmf() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,  "Number=?",
                new String[] {Number});
        db.close();
    }*/

    public void deleteDtmf(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}

