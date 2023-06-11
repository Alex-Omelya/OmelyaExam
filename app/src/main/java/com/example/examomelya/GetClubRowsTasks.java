// GetClubRowsTasks.java
package com.example.examomelya;

import android.database.Cursor;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GetClubRowsTasks extends AsyncTask<Object, Object, Cursor> {
    private WeakReference<ClubActivity> act;
    private DatabaseConnector databaseConnector;

    GetClubRowsTasks(ClubActivity activity) {
        act = new WeakReference<>(activity);
        databaseConnector = new DatabaseConnector(activity);
    }

    @Override
    protected Cursor doInBackground(Object... params) {
        databaseConnector.open();
        return databaseConnector.getClubAllRows();
    }

    protected void onPostExecute(Cursor cursor) {
        ArrayList<String> lst = new ArrayList<>(); // массив строк для вывода на экран в ListView
        int idIndex = cursor.getColumnIndex(DBHelper.CLUB_COLUMN_id);
        int nameIndex = cursor.getColumnIndex(DBHelper.CLUB_COLUMN_name);
        int addressIndex = cursor.getColumnIndex(DBHelper.CLUB_COLUMN_address);
        int quantity_membersIndex = cursor.getColumnIndex(DBHelper.CLUB_COLUMN_quantity_members);

        while (cursor.moveToNext()) {
            String id = cursor.getString(idIndex);
            String name = cursor.getString(nameIndex);
            String address = cursor.getString(addressIndex);
            int quantity_members = cursor.getInt(quantity_membersIndex);

            lst.add("id=" + id + ", name=" + name + ", address=" + address + ", quantity members=" + quantity_members);
        }

        cursor.close(); // закрыть курсор
        databaseConnector.close(); // закрыть подключение
        act.get().update_list(lst); // обновление ListView
    }
}