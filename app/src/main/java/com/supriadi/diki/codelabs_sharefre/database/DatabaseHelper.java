package com.supriadi.diki.codelabs_sharefre.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.supriadi.diki.codelabs_sharefre.database.DatabaseContract.MahasiswaColumns.NAMA;
import static com.supriadi.diki.codelabs_sharefre.database.DatabaseContract.MahasiswaColumns.NIM;
import static com.supriadi.diki.codelabs_sharefre.database.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbmahasiswa";
    private static final int DATABASE_VERSION = 1;
    private static String CREATE_TABLE_MAHASIWA = "create_table" + TABLE_NAME +
            "(" + _ID + "integer primary key autoincrement," +
            NAMA + "text not null," +
            NIM + "text not null);";

    DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASIWA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
