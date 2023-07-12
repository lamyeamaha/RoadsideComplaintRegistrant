package ewubd.roadsidecomplaintregistrant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserFeedbackTable extends SQLiteOpenHelper {

	// TABLE INFORMATION
	static final String DB_NAME = "UserFeedbackTable.DB";
	public final String TABLE_KEY_VALUE = "key_value_pairs";
	public final String KEY = "keyname";
	public final String VALUE = "itemvalue";
	//
	public UserFeedbackTable(Context c) {
		super(c, DB_NAME, null, 1);
		// SQLiteDatabase db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DB@OnCreate");
		try {
			db.execSQL("DROP TABLE " + TABLE_KEY_VALUE);
		} catch (Exception e) {
		}
		createUserFeedbackTable(db);
	}

	private void createUserFeedbackTable(SQLiteDatabase db){
		try {
			db.execSQL("create table " + TABLE_KEY_VALUE + " (" + KEY
					+ " TEXT, " + VALUE + " TEXT)");
		} catch (Exception e) {
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		createUserFeedbackTable(db);
	}

	private void handleError(SQLiteDatabase db, Exception e){
		String errorMsg = e.getMessage().toString();
		if (errorMsg.contains("no such table")){
			if (errorMsg.contains(TABLE_KEY_VALUE)){
				createUserFeedbackTable(db);
			}
		}
	}

	public Cursor execute(String query) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res;
		try {
			res = db.rawQuery(query, null);
		}catch (Exception e){
			//e.printStackTrace();
			handleError(db, e);
			res = db.rawQuery(query, null);
		}
		return res;
	}



	public Boolean insertKeyValue(String k, String v) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(KEY, k);
		cv.put(VALUE, v);
		long result;
		try{
			result = db.insert(TABLE_KEY_VALUE, null, cv);
		}catch (Exception e){
			handleError(db, e);
			result = db.insert(TABLE_KEY_VALUE, null, cv);
		}
		if (result == -1) {
			return false;
		}
		else {
			return true;
		}
	}

//	public Cursor getAllKeyValues() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor res = db.rawQuery("select * from " + TABLE_KEY_VALUE, null);
//		return res;
//	}

	public boolean updateValueByKey(String k, String v) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(KEY, k);
		cv.put(VALUE, v);
		int nr = 0;
		try{
			nr = db.update(TABLE_KEY_VALUE, cv, KEY + "=?",
					new String[] { k });
		}catch (Exception e){
			handleError(db, e);
			try {
				nr = db.update(TABLE_KEY_VALUE, cv, KEY + "=?",
						new String[]{k});
			}catch (Exception ex){}
		}
		if (nr == 0) {
			insertKeyValue(k, v);
		}
		return true;
	}

	public Integer deleteDataByKey(String key) {
		SQLiteDatabase db = this.getWritableDatabase();
		int isDeleted = 0;
		try{
			isDeleted = db.delete(TABLE_KEY_VALUE, KEY + " = ?", new String[] { key });
		}catch (Exception e){
			handleError(db, e);
			try {
				isDeleted = db.delete(TABLE_KEY_VALUE, KEY + " = ?", new String[]{key});
			}catch (Exception ex){}
		}
		return isDeleted;
	}

	public String getValueByKey(String k) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res;
		try{
			res = db.rawQuery("SELECT * FROM " + TABLE_KEY_VALUE + " WHERE "
					+ KEY + "='" + k + "'", null);
		}catch (Exception e){
			handleError(db, e);
			res = db.rawQuery("SELECT * FROM " + TABLE_KEY_VALUE + " WHERE "
					+ KEY + "='" + k + "'", null);
		}
		if(res.getCount()>0){
			res.moveToNext();
			return res.getString(1);
		}
		return null;
	}
	//
	public void deleteQuery(String query) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res;
		try {
			db.execSQL(query);
		}catch (Exception e){
			// e.printStackTrace();
			handleError(db, e);
			db.execSQL(query);
		}
	}
}