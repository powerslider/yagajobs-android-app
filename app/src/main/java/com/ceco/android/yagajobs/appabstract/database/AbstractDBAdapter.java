package com.ceco.android.yagajobs.appabstract.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ceco on 18-May-14.
 */
public class AbstractDBAdapter {

    private final Context context;

    private DBHelper dbHelper;

    protected AbstractDBAdapter(Context context) {
        this.context = context;
    }

    protected SQLiteDatabase openToRead(String databaseName) throws SQLException {
        dbHelper = new DBHelper(context, databaseName);
        return dbHelper.getReadableDatabase();
    }

    protected SQLiteDatabase openToWrite(String databaseName) throws SQLException {
        dbHelper = new DBHelper(context, databaseName);
        return dbHelper.getWritableDatabase();
    }

    protected static class DBHelper extends SQLiteOpenHelper {

        // Default Database Version
        private static final int DATABASE_VERSION = 1;

        private Context context;

        private String createScript;

        private String updateScript;

        public DBHelper(Context context, String databaseName) {
            super(context, databaseName, null, DATABASE_VERSION);
            this.context = context;
        }

        public DBHelper(Context context, String databaseName, int databaseVersion) {
            super(context, databaseName, null, databaseVersion);
            this.context = context;
        }

        public static DBHelper newInstance(Context context, String databaseName, int... databaseVersion) {
            if (databaseVersion.length != 0) {
                return new DBHelper(context, databaseName, databaseVersion[0]);
            } else if (databaseVersion.length > 1) {
                throw new IllegalArgumentException("Cannot write more than one version.");
            }

            return new DBHelper(context, databaseName);
        }

        public DBHelper withCreateScript(String createScript) {
            this.createScript = createScript;
            return this;
        }

        public DBHelper withUpdateScript(String updateScript) {
            this.updateScript = updateScript;
            return this;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            executeSQLScript(db, createScript);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            executeSQLScript(db, updateScript);
        }

        private void executeSQLScript(SQLiteDatabase database, String dbname) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1024];
            int len;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream;
            try{
                inputStream = assetManager.open(dbname);
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.close();
                inputStream.close();

                String[] createScript = outputStream.toString().split(";");
                for (int i = 0; i < createScript.length; i++) {
                    String sqlStatement = createScript[i].trim();
                    if (sqlStatement.length() > 0) {
                        database.execSQL(sqlStatement + ";");
                    }
                }
            } catch (IOException e){
                Log.e("IOError", "Cannot read sql scripts");
            }
        }
    }
}
