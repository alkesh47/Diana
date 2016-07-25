package com.example.alkesh.expensemanager101;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Distribution extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c1,c2,c3,c4;
    String mname="01";
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution);

        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);

        Toast.makeText(this,"Rotate your Device to get a better and more accurate view of the Charts",Toast.LENGTH_LONG).show();
        DisplayChart();
    }

    protected void DisplayChart(){
        ArrayList<BarEntry> entries=new ArrayList<>();
        String month;

        for(int x=1;x<13;x++){

            if(x<10)
                month=getMonthSum(String.valueOf("0"+x));

            else
                month=getMonthSum(String.valueOf(x));

            entries.add(new BarEntry(Float.parseFloat(month),c++));

        }

        BarDataSet dataset = new BarDataSet(entries, "Total Expenditure in Months");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("Mar");
        labels.add("Apr");
        labels.add("May");
        labels.add("Jun");
        labels.add("Jul");
        labels.add("Aug");
        labels.add("Sep");
        labels.add("Oct");
        labels.add("Nov");
        labels.add("Dec");

        BarChart chart = new BarChart(this);
        setContentView(chart);

        BarData data = new BarData(labels, dataset);            //adding the data we created for chart
        chart.setData(data);

        chart.setDescription("# of times Alice called Bob");       // Chart Description

        dataset.setColors(ColorTemplate.JOYFUL_COLORS);
        chart.animateY(5000);

    }

    protected String getMonthSum(String mname){
        String Mname=mname;
        c4=db.rawQuery("SELECT SUM(name) FROM money WHERE months='"+Mname+"'",null);
        if(c4.moveToFirst()){
            return String.valueOf(c4.getInt(0));
        }
        return "0";
    }
}
