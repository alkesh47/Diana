package com.example.alkesh.expensemanager101;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TotalSum extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c,c2;
    TextView TotalSumDisplay,AprilDisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_sum);

        TotalSumDisplay=(TextView)findViewById(R.id.Total);
        AprilDisp=(TextView)findViewById(R.id.AprilTotal);

        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
        c=db.rawQuery("SELECT SUM(name) FROM money",null);

        int Mname=4;
        c2=db.rawQuery("SELECT SUM(name) FROM money WHERE months="+Mname,null);
        String i=getSum();
        String i2=getAprilSum();

        TotalSumDisplay.setText(i);
        AprilDisp.setText(i2);


    }

    protected String getSum(){
        if(c.moveToFirst()){
            return String.valueOf(c.getInt(0));
        }
        return "0";
    }

    protected String getAprilSum(){
        if(c2.moveToFirst()){
            return String.valueOf(c2.getInt(0));
        }
        return "0";
    }

}
