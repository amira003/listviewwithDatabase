package com.example.fondn.databasewithlistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper databaseHelper;
    EditText idET, nameET;
    Button save, show, update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idET = (EditText) findViewById(R.id.idedittextID);
        nameET = (EditText) findViewById(R.id.nameeditTextID);
        save = (Button) findViewById(R.id.saveID);
        show = (Button) findViewById(R.id.showID);
        update = (Button) findViewById(R.id.updateID);
        delete = (Button) findViewById(R.id.deleteID);

        save.setOnClickListener(this);
        show.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

        databaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        String idString = idET.getText().toString();
        String name = nameET.getText().toString();

        if (v.getId() == R.id.saveID) {
            if (idString.equals("") && name.equals("")) {
                Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show();
            } else {
                long rownumber = databaseHelper.saveData(idString, name);
                if (rownumber > -1) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Not inserted", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (v.getId() == R.id.showID) {
            Intent i = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(i);

        } else if (v.getId() == R.id.updateID) {
           Boolean isUpdated = databaseHelper.updateData(idString,name);
           if(isUpdated == true){
               Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(this, "not Updated", Toast.LENGTH_SHORT).show();
           }


        } else if (v.getId() == R.id.deleteID) {
            int value = databaseHelper.deleteData(idString);
            if(value<0){
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
