// GetParticipantsRowsTask.java
package com.example.examomelya;

import android.database.Cursor;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class GetParticipantsRowsTasks extends AsyncTask<Object, Object, Cursor> {
    private WeakReference<ParticipantActivity> act;
    private DatabaseConnector databaseConnector;

    GetParticipantsRowsTasks(ParticipantActivity activity) {
        act = new WeakReference<>(activity);
        databaseConnector = new DatabaseConnector(activity);
    }

    @Override
    protected Cursor doInBackground(Object... params) {
        databaseConnector.open();
        return databaseConnector.getParticipantsAllRows();
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        ArrayList<String> lst = new ArrayList<>(); // массив строк для вывода на экран в ListView
        int idIndex = cursor.getColumnIndex(DBHelper.PARTICIPANTS_COLUMN_id);
        int nameIndex = cursor.getColumnIndex(DBHelper.PARTICIPANTS_COLUMN_name);
        int clubIndex = cursor.getColumnIndex(DBHelper.PARTICIPANTS_COLUMN_club);
        int typeIndex = cursor.getColumnIndex(DBHelper.PARTICIPANTS_COLUMN_type);

        while (cursor.moveToNext()) {
            String id = cursor.getString(idIndex);
            String name = cursor.getString(nameIndex);
            String club = cursor.getString(clubIndex);
            String type = cursor.getString(typeIndex);

            lst.add("id=" + id + ", name=" + name + ", club=" + club + ", type=" + type);
        }
        cursor.close(); // закрыть курсор
        databaseConnector.close(); // закрыть подключение
        act.get().update_list(lst); // обновление ListView
    }
}