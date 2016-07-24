package com.example.alkesh.expensemanager101;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Overview extends AppCompatActivity implements View.OnClickListener {

    TextView expense,amount,Notes,Date,Month;
    Button Next,Previous,Main, ViewChart;

    private SQLiteDatabase db,db2;
    private static final String SELECT_SQL= "SELECT * FROM money";
    private static final String SELECT_SQL2= "SELECT * FROM monthtotal";
    private Cursor c,c2;
    static int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        OpenDatabase();
        OpenDatabase2();

        Month=(TextView)findViewById(R.id.monthsetTextView);
        expense = (TextView)findViewById(R.id.expensetext);
        amount = (TextView)findViewById(R.id.amounttext);
        Notes = (TextView)findViewById(R.id.notesTextView);
        Date = (TextView)findViewById(R.id.dateTextView);

        Next = (Button)findViewById(R.id.nextbutton);
        Previous = (Button)findViewById(R.id.previousbutton);
        Main = (Button)findViewById(R.id.mainpagebutton);
        ViewChart = (Button)findViewById(R.id.viewChartbutton);

        Next.setOnClickListener(this);
        Previous.setOnClickListener(this);
        Main.setOnClickListener(this);
        ViewChart.setOnClickListener(this);

        c=db.rawQuery(SELECT_SQL,null);
        c2=db2.rawQuery(SELECT_SQL2,null);

        c.moveToLast();
        ShowRecords();
        Calculate();
    }

    protected void OpenDatabase(){
        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
    }
    protected void OpenDatabase2(){
        db2=openOrCreateDatabase("MonthsTotalDB", Context.MODE_PRIVATE,null);
    }


    @Override
    public void onClick(View view) {

        if(view==Next){
            Next();
        }

        if (view == Previous){
            Previous();
        }

        if (view == Main){
            finish();
        }

        if (view == ViewChart){
            Intent intent = new Intent(Overview.this,TotalSum.class);
            startActivity(intent);
        }

    }

    protected void ShowRecords() {
        String exp = c.getString(1);
        String mon = c.getString(4);

        String tmp = "";
        switch (mon) {
            case "01":
                tmp = "January";
                break;
            case "02":
                tmp = "February";
                break;
            case "03":
                tmp = "March";
                break;
            case "04":
                tmp = "April";
                break;
            case "05":
                tmp = "May";
                break;
            case "06":
                tmp = "June";
                break;
            case "07":
                tmp = "July";
                break;
            case "08":
                tmp = "August";
                break;
            case "09":
                tmp = "September";
                break;
            case "10":
                tmp = "October";
                break;
            case "11":
                tmp = "November";
                break;
            case "12":
                tmp = "December";
                break;
        }

            String strFilter = "id=" + mon;
            ContentValues contentValues=new ContentValues();
            contentValues.put("total",c.getString(1));
            db2.update("monthtotal",contentValues,strFilter,null);
            Toast.makeText(this,"Second DB updated",Toast.LENGTH_SHORT).show();

            Month.setText(tmp);
            expense.setText(exp);
            SetDateAndNotes();
    }

    protected void Next(){
        if(!c.isLast()){
            c.moveToNext();
        }

        ShowRecords();
    }

    protected void Previous(){
        if(!c.isFirst()){
            c.moveToPrevious();
        }

        ShowRecords();
    }

    protected void Calculate(){
        sum+=Integer.parseInt(c.getString(1));
        amount.setText(String.valueOf(sum));
    }

    protected  void SetDateAndNotes(){
        Date.setText(String.valueOf(c.getString(2)));
        Notes.setText(c.getString(3));
    }

}
