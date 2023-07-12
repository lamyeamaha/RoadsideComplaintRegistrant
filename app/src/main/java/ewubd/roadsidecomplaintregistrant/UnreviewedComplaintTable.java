package ewubd.roadsidecomplaintregistrant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UnreviewedComplaintTable extends SQLiteOpenHelper {

    // TABLE INFORMATTION
    static final String DB_NAME = "KEY_VALUE.DB";
    public final String TABLE_UNREVIEWED_COMPLAINT_VALUE = "unreviewed_complaint_value_pairs";
    public final String COMPLAINT_KEY = "complaintname";
    public final String COMPLAINT_VALUE = "complaintvalue";
    //
    public UnreviewedComplaintTable(Context context) {
        super(context, DB_NAME, null, 1);   // this is database version. if changed, onUpgrade class is called
        // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {    /// called only for the 1st time when the app is installed
        System.out.println("DB@OnCreate");
        try {
            db.execSQL("DROP TABLE " + TABLE_UNREVIEWED_COMPLAINT_VALUE);
        } catch (Exception e) {
        }
        createUnreviewedComplaintTable(db);
    }

    private void createUnreviewedComplaintTable(SQLiteDatabase db){
        try {
            db.execSQL("create table " + TABLE_UNREVIEWED_COMPLAINT_VALUE + " (" + COMPLAINT_KEY
                    + " TEXT, " + COMPLAINT_VALUE + " TEXT)");
        } catch (Exception e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createUnreviewedComplaintTable(db);
    }

    private void handleError(SQLiteDatabase db, Exception e){
        String errorMsg = e.getMessage().toString();
        if (errorMsg.contains("no such table")){
            if (errorMsg.contains(TABLE_UNREVIEWED_COMPLAINT_VALUE)){
                createUnreviewedComplaintTable(db);
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



    public Boolean insertKeyValue(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMPLAINT_KEY, key);
        cv.put(COMPLAINT_VALUE, value);
        long result;
        try{
            result = db.insert(TABLE_UNREVIEWED_COMPLAINT_VALUE, null, cv);
        }catch (Exception e){
            handleError(db, e);
            result = db.insert(TABLE_UNREVIEWED_COMPLAINT_VALUE, null, cv);
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

    public boolean updateValueByComplaintKey(String key, String value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COMPLAINT_KEY, key);
        cv.put(COMPLAINT_VALUE, value);
        int nr = 0;
        try{
            nr = db.update(TABLE_UNREVIEWED_COMPLAINT_VALUE, cv, COMPLAINT_KEY + "=?",
                    new String[] { key });
        }catch (Exception e){
            handleError(db, e);
            try {
                nr = db.update(TABLE_UNREVIEWED_COMPLAINT_VALUE, cv, COMPLAINT_KEY + "=?",
                        new String[]{key});
            }catch (Exception ex){}
        }
        if (nr == 0) {
            insertKeyValue(key, value);
        }
        return true;
    }

    public Integer deleteDataByComplaintKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDeleted = 0;
        try{
            isDeleted = db.delete(TABLE_UNREVIEWED_COMPLAINT_VALUE, COMPLAINT_KEY + " = ?", new String[] { key });
        }catch (Exception e){
            handleError(db, e);
            try {
                isDeleted = db.delete(TABLE_UNREVIEWED_COMPLAINT_VALUE, COMPLAINT_KEY + " = ?", new String[]{key});
            }catch (Exception ex){}
        }
        return isDeleted;
    }

    public String getValueByComplaintKey(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        try{
            res = db.rawQuery("SELECT * FROM " + TABLE_UNREVIEWED_COMPLAINT_VALUE + " WHERE "
                    + COMPLAINT_KEY + "='" + key + "'", null);
        }catch (Exception e){
            handleError(db, e);
            res = db.rawQuery("SELECT * FROM " + TABLE_UNREVIEWED_COMPLAINT_VALUE + " WHERE "
                    + COMPLAINT_KEY + "='" + key + "'", null);
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