package com.example.alkesh.expensemanager101;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Add,Total,Dist,Main;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Add = (Button) findViewById(R.id.addbutton);
        Total = (Button) findViewById(R.id.totalbutton);
        Dist = (Button) findViewById(R.id.distributionbutton);
        Main = (Button) findViewById(R.id.overviewbutton);

        Total.setOnClickListener(this);
        Dist.setOnClickListener(this);
        Main.setOnClickListener(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMoney.class);
                startActivity(intent);
            }
        });

        CreateDatabase();

    }

    public void CreateDatabase(){
        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS money(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR ,date VARCHAR, notes VARCHAR, months VARCHAR);");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuhome,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.feedback:{}
            break;

            case R.id.exit: finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == Dist){
            Intent intent=new Intent(MainActivity.this,Distribution.class);
            startActivity(intent);
        }
        if (view == Total){
            Intent intent=new Intent(MainActivity.this,TotalSum.class);
            startActivity(intent);
        }
        if (view == Main){
            Intent intent=new Intent(MainActivity.this,Overview.class);
            startActivity(intent);
        }

    }
}
