package com.example.alkesh.expensemanager101;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Distribution extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);

        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
        //Cursor cursor=getCursor();
        getSum();
        /*c2=db.rawQuery("SELECT * FROM monthtotal where id="+2,null);
        c3=db.rawQuery("SELECT * FROM monthtotal where id="+3,null);
        c4=db.rawQuery("SELECT * FROM monthtotal where id="+4,null);
        c5=db.rawQuery("SELECT * FROM monthtotal where id="+5,null);
        c6=db.rawQuery("SELECT * FROM monthtotal where id="+6,null);
*/

        //DisplayChart();
    }

    protected void getSum(){
        int tmp = 0;
        int amount=0;
        Cursor cursor = db.rawQuery("SELECT SUM(name) FROM money WHERE months="+2, null);

        if(cursor!=null && cursor.moveToFirst()){
            try{
                amount = Integer.parseInt(cursor.getString(0));
            }catch (Exception e){};

            }

        Toast.makeText(this, amount,Toast.LENGTH_SHORT);

        /*if (cursor.moveToFirst())
        {
            tmp += Integer.parseInt(cursor.getString(0));
        } while (cursor.moveToNext());*/
    }

    protected Cursor getCursor(){

        String query="SELECT * FROM money WHERE months=" +5;
        c1=db.rawQuery(query,null);

        if(c1!=null){
            c1.moveToFirst();
            c1=db.rawQuery(query,null);
        }
        return c1;
    }

    /*protected void DisplayChart(){
        ArrayList<BarEntry> entries=new ArrayList<>();
        String tmp="";

        if(c1!=null && c1.moveToFirst()){
            tmp=c1.getString(1);
            Toast.makeText(this,tmp,Toast.LENGTH_SHORT).show();
            c1.close();
        }
        entries.add(new BarEntry(Float.parseFloat(tmp),0));
        entries.add(new BarEntry(Float.parseFloat(tmp), 1));
        entries.add(new BarEntry(Float.parseFloat(tmp), 2));
        entries.add(new BarEntry(Float.parseFloat(tmp), 3));
        entries.add(new BarEntry(Float.parseFloat(tmp), 4));
        entries.add(new BarEntry(Float.parseFloat(tmp), 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        BarChart chart = new BarChart(this);
        setContentView(chart);

        BarData data = new BarData(labels, dataset);            //adding the data we created for chart
        chart.setData(data);

        chart.setDescription("# of times Alice called Bob");       // Chart Description
    }*/
}
