package com.coretechies.ispoof;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHandler extends SQLiteOpenHelper {


    private Context context;
    private static final String DB_NAME = "callLog.db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "callLog";

    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String Number = "number";

    private static final String Date = "date";


    public DBHandler(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + Number + " TEXT,"
                + Date +" TEXT)";


        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewCourse(String callName, String callnumber, long calldate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, callName);
        values.put(Number, callnumber);
        values.put(Date,calldate);
        // after adding all values we are passing
        // content values to our table.
        long result=db.insert(TABLE_NAME, null, values);
       /*if (result ==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "succcesfully", Toast.LENGTH_SHORT).show();
        }*/

        // at last we are closing our
        // database after adding database.
        db.close();
    }


    Cursor readAllData(){
        String query ="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =null;
        if (db!= null){
            cursor=db.rawQuery(query,null);
        }

        return cursor;
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

