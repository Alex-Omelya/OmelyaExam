// DBHelper.java
package com.example.examomelya;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_CLUB = "Club";
    public static final String CLUB_COLUMN_id = "_id";
    public static final String CLUB_COLUMN_name = "name_club";
    public static final String CLUB_COLUMN_address = "address";
    public static final String CLUB_COLUMN_quantity_members = "quantity_members";

    public static final String TABLE_PARTICIPANTS = "Participants";
    public static final String PARTICIPANTS_COLUMN_id = "_id";
    public static final String PARTICIPANTS_COLUMN_name = "name_participant";
    public static final String PARTICIPANTS_COLUMN_club = "club";
    public static final String PARTICIPANTS_COLUMN_type = "type";


    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, databaseName, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLUB + "( "
                + CLUB_COLUMN_id + " integer primary key autoincrement, "
                + CLUB_COLUMN_name + " TEXT, "
                + CLUB_COLUMN_address + " TEXT, "
                + CLUB_COLUMN_quantity_members + " INTEGER "
                + " )"
        );

        db.execSQL("CREATE TABLE " + TABLE_PARTICIPANTS + "( "
                + PARTICIPANTS_COLUMN_id + " integer primary key autoincrement, "
                + PARTICIPANTS_COLUMN_name + " TEXT, "
                + PARTICIPANTS_COLUMN_club + " TEXT, "
                + PARTICIPANTS_COLUMN_type + " TEXT "
                + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
