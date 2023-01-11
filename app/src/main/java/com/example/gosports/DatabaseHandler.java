package com.example.gosports;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "GO Sports";

    private static final String TABLE_USER = "users";
    private static final String TABLE_BOOKING = "bookings";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";

    private static final String KEY_ARENA = "arena";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                    + KEY_USERNAME + " TEXT,"
                                    + KEY_EMAIL + " TEXT,"
                                    + KEY_PHONE + " TEXT,"
                                    + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_BOOKING + "("
                + KEY_ID + " INTEGER,"
                + KEY_ARENA + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + "FOREIGN KEY (" + KEY_ID + ") REFERENCES " + TABLE_USER + " (" + KEY_ID + ")" + ")";
        db.execSQL(CREATE_BOOKING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(user user){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE, user.getPhone());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean authUser(user user){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_USER +
                        " WHERE " + KEY_USERNAME + " = ? AND " + KEY_PASSWORD +" = ?";
        String args[] = {user.getUsername(), user.getPassword()};
        Cursor cursor = db.rawQuery(query, args);
        if(cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    public int getID(user user){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT " + KEY_ID + " FROM " + TABLE_USER +
                " WHERE " + KEY_USERNAME + " = ?";
        String args[] = {user.getUsername()};
        Cursor cursor = db.rawQuery(query, args);
        if(cursor.moveToNext()){
            int ID = cursor.getInt(0);
            cursor.close();
            db.close();
            return ID;
        }
        else{
            db.close();
            return 0;
        }
    }

    public user getUserInfo(int userID){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        user user;
        user = new user();

        String query = "SELECT * FROM " + TABLE_USER +
                        " WHERE " + KEY_ID + " = ?";
        String args[] = {Integer.toString(userID)};
        Cursor cursor = db.rawQuery(query, args);
        cursor.moveToNext();
        user.setId(cursor.getInt(0));
        user.setUsername(cursor.getString(1));
        user.setEmail(cursor.getString(2));
        user.setPhone(cursor.getString(3));

        cursor.close();
        db.close();
        return user;
    }

    public void createBooking(reservedarena arena){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, arena.getBookerID());
        values.put(KEY_ARENA, arena.getArenaname());
        values.put(KEY_DATE, arena.getDate());
        values.put(KEY_TIME, arena.getTime());

        db.insert(TABLE_BOOKING, null, values);
        db.close();
    }

    public boolean checkAvailable(reservedarena arena){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_BOOKING +
                        " WHERE " + KEY_ARENA + " = ? AND " + KEY_DATE + "= ? AND " + KEY_TIME + " = ?";
        String args[] = {arena.getArenaname(), arena.getDate(), arena.getTime()};
        Cursor cursor = db.rawQuery(query, args);

        if(cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    public String[][] getData(int userID, int count){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        String[][] data = new String[count][3];

        String query = "SELECT * FROM " + TABLE_BOOKING +
                        " WHERE " + KEY_ID + " = ?";
        String args[] = {Integer.toString(userID)};
        Cursor cursor = db.rawQuery(query, args);

        if(cursor.moveToFirst()){
            for (int i = 0; i<count; i++){
                data[i][0] = cursor.getString(1);
                data[i][1] = cursor.getString(2);
                data[i][2] = cursor.getString(3);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return data;
    }

    public int getCount(int userID){
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();

        String query1 = "SELECT * FROM " + TABLE_BOOKING +
                " WHERE " + KEY_ID + " = ?";
        String args1[] = {Integer.toString(userID)};
        Cursor cursor1 = db.rawQuery(query1, args1);

        int x = cursor1.getCount();
        cursor1.close();
        db.close();
        return x;
    }
}
