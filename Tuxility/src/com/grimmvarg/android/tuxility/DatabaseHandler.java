package com.grimmvarg.android.tuxility;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler {

	private static final int DB_VERSION = 1;
	private static final String DB_PATH = "/sdcard/.tuxility/.settings.db.temp";
	private SQLiteDatabase settingsDB;

	public DatabaseHandler() {
		settingsDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
	}
    
    public Cursor doQuery(String query){
    	Cursor resultCursor = settingsDB.rawQuery(query, null);
    	return resultCursor;
    }
    
    private boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
    	
    	try{
    		checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		Log.v("<--- DB-Handler --->", e.toString());
    	}
 
    	if(checkDB != null){
    		
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }

	public void close() {
		settingsDB.close();
		
	}

}
