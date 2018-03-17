package com.example.fondn.databasewithlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Sagor";
    public static final String TABLE_NAME = "user_detrails";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT(255));";
    public static final int Verstion = 1;
    private static final String DROP_TABLE = "drop table if exists "+TABLE_NAME;
    Context c;
    MyDatabaseHelper myDatabaseHelper;
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, Verstion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
            //Toast.makeText(c, "Oncreate called", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //Toast.makeText(c, "Oncreate not failed", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public long saveData(String id, String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues c  = new ContentValues();
        c.put(ID,id);
        c.put(NAME,name);
        long l = database.insert(TABLE_NAME,null,c);
        return l;
    }

    public Cursor showAllData(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Boolean updateData(String id,String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues c  = new ContentValues();
        c.put(ID,id);
        c.put(NAME,name);
        database.update(TABLE_NAME,c,ID+" = ?",new String[] {id});
        return true;
    }

    public int deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        int value = database.delete(TABLE_NAME,ID+" = ?",new String[] {id});
        return value;
    }

}
