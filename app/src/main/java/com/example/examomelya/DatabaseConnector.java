// DatabaseConnector.java
package com.example.examomelya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnector {
    private static final String DATABASE_NAME = "FitnessClub";
    private SQLiteDatabase database;
    private DBHelper databaseOpenHelper;

    public DatabaseConnector(Context context) {
        databaseOpenHelper = new DBHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            database.close();
        }
    }

    public void insertClubRow(String name_club, String address, String quantity_members) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.CLUB_COLUMN_name, name_club);
        row.put(DBHelper.CLUB_COLUMN_address, address);
        row.put(DBHelper.CLUB_COLUMN_quantity_members, quantity_members);
        open();
        database.insert(DBHelper.TABLE_CLUB, null, row);
        close();
    }

    public void insertParticipantsRow(String name_participant, String club, String type) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.PARTICIPANTS_COLUMN_name, name_participant);
        row.put(DBHelper.PARTICIPANTS_COLUMN_club, club);
        row.put(DBHelper.PARTICIPANTS_COLUMN_type, type);
        open();
        database.insert(DBHelper.TABLE_PARTICIPANTS, null, row);
        close();
    }

    public Cursor getClubAllRows() {
        return database.query(DBHelper.TABLE_CLUB, new String[]{
                        DBHelper.CLUB_COLUMN_id,
                        DBHelper.CLUB_COLUMN_name,
                        DBHelper.CLUB_COLUMN_address,
                        DBHelper.CLUB_COLUMN_quantity_members},
                null, null, null, null, null);
    }

    public Cursor getParticipantsAllRows() {
        return database.query(DBHelper.TABLE_PARTICIPANTS, new String[]{
                        DBHelper.PARTICIPANTS_COLUMN_id,
                        DBHelper.PARTICIPANTS_COLUMN_name,
                        DBHelper.PARTICIPANTS_COLUMN_club,
                        DBHelper.PARTICIPANTS_COLUMN_type},
                null, null, null, null, null);
    }

    public void deleteClubRow(long id) {
        open();
        database.delete(DBHelper.TABLE_CLUB, DBHelper.CLUB_COLUMN_id + "=" + id, null);
        close();
    }

    public void deleteParticipantsRow(long id) {
        open();
        database.delete(DBHelper.TABLE_PARTICIPANTS, DBHelper.PARTICIPANTS_COLUMN_id + "=" + id, null);
        close();
    }

    public void editClubRow(long id, String name_club, String address, String quantity_members) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.CLUB_COLUMN_name, name_club);
        row.put(DBHelper.CLUB_COLUMN_address, address);
        row.put(DBHelper.CLUB_COLUMN_quantity_members, quantity_members);
        open();
        database.update(DBHelper.TABLE_CLUB, row, DBHelper.CLUB_COLUMN_id + "=" + id, null);
        close();
    }

    public void editParticipantsRow(long id, String name_participant, String club, String type) {
        ContentValues row = new ContentValues();
        row.put(DBHelper.PARTICIPANTS_COLUMN_name, name_participant);
        row.put(DBHelper.PARTICIPANTS_COLUMN_club, club);
        row.put(DBHelper.PARTICIPANTS_COLUMN_type, type);
        open();
        database.update(DBHelper.TABLE_PARTICIPANTS, row, DBHelper.PARTICIPANTS_COLUMN_id + "=" + id, null);
        close();
    }
}