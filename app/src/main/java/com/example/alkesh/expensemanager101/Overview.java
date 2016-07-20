package com.example.alkesh.expensemanager101;

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

    TextView expense,amount;
    Button Next,Previous,Main;

    private SQLiteDatabase db;
    private static final String SELECT_SQL= "SELECT * FROM money";
    private Cursor c;
    static int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        OpenDatabase();

        expense = (TextView)findViewById(R.id.expensetext);
        amount = (TextView)findViewById(R.id.amounttext);

        Next = (Button)findViewById(R.id.nextbutton);
        Previous = (Button)findViewById(R.id.previousbutton);
        Main = (Button)findViewById(R.id.mainpagebutton);

        Next.setOnClickListener(this);
        Previous.setOnClickListener(this);
        Main.setOnClickListener(this);

        c=db.rawQuery(SELECT_SQL,null);
        c.moveToLast();
        ShowRecords();
        Calculate();
    }

    protected void OpenDatabase(){
        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
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

    }

    protected void ShowRecords(){
        String exp=c.getString(1);
        expense.setText(exp);
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
}
