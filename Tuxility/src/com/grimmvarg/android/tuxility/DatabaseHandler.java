package com.grimmvarg.android.tuxility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseHandler {

	private static final String DB_PATH = "/data/data/com.grimmvarg.android.tuxility/databases/settings.db.temp";
	private OpenHelper openHelper;
	
	public DatabaseHandler(Context context) {
		try{
			openHelper = new OpenHelper(context, DB_PATH, null, 1);
		} catch (Exception e) {
			Log.v("<--- DB-Helper --->", e.toString());
		}
	}

	public Cursor selectAll(String table) {
		Cursor resultCursor = openHelper.getReadableDatabase().query(table, null, null, null, null, null, null);
		return resultCursor;
	}

	public void doUpdate(String sql) {
		try{
			openHelper.getWritableDatabase().execSQL(sql);
			
		} catch (Exception e) {
			Log.v("<--- DB-Helper --->", e.toString());
		}
	}
	
	public void doInsert(String sql){
		
	}


	private static class OpenHelper extends SQLiteOpenHelper {

		public OpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

	}

}
