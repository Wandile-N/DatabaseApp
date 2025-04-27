package com.example.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.database.R;


public class MainActivity extends Activity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB=new DatabaseHelper(this, "app");
        setContentView(R.layout.activity_main);

        n = findViewById(R.id.nameInput);
        a = findViewById(R.id.ageInput);
    }
    EditText n;
    EditText a;

    public void doInsert(View view){
        String name = n.getText().toString();
        String age = a.getText().toString();
        String[] vals = {name, age};
        myDB.doUpdate("Insert into students(name, age) values (?,?);", vals);
        n.setText(""); a.setText("");
    }

    public void doList(View v){
        LinearLayout l = (LinearLayout)findViewById(R.id.mainView);
        Cursor c = myDB.doQuery("SELECT name, age FROM students");
        TextView t = null;
        while (c.moveToNext()){
            @SuppressLint("Range") String n = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String a = c.getString(c.getColumnIndex("age"));
            t = new TextView(this);
            t.setText(n+","+a);
            l.addView(t);
        }
    }

    @SuppressLint("Range")
    public void doQuery(View v){
        Cursor c = myDB.doQuery("SELECT name, age from students");
        while (c.moveToNext()){
            System.out.println(c.getString(c.getColumnIndex("name"))+","+c.getLong(c.getColumnIndex("age")));
        }
        c.close();
    }
}
