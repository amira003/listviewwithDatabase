package com.example.fondn.databasewithlistview;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    ListView listView;
    MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper = new MyDatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listviewID);


        loadData();

    }


    public void loadData() {
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data is available", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+"\t \t"+cursor.getString(1));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listitem,R.id.listviewTextViewID,listData);
        listView.setAdapter(adapter);

    }
}