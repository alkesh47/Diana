package com.example.alkesh.expensemanager101;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMoney extends AppCompatActivity implements View.OnClickListener{

    Button Save,Cancel;
    EditText expText,Date,Notes;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        OpenDatabase();


        Date=(EditText)findViewById(R.id.dateEditText);
        Notes=(EditText)findViewById(R.id.notesEditText);
        expText=(EditText)findViewById(R.id.expense);

        Cancel=(Button)findViewById(R.id.cancelbutton);
        Cancel.setOnClickListener(this);

        Save = (Button)findViewById(R.id.savebutton);
        Save.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if(view== Save){
            insertIntoDatabase();
            Intent intent= new Intent(AddMoney.this,Overview.class);
            startActivity(intent);
            finish();
        }

        if(view==Cancel){
            finish();
        }

    }


    protected void OpenDatabase(){
        db=openOrCreateDatabase("MoneyDB",Context.MODE_PRIVATE,null);
    }


    protected void insertIntoDatabase(){
        String amount = expText.getText().toString().trim();
        String date=Date.getText().toString().trim();
        String notes=Notes.getText().toString().trim();

        if(amount.equals("") || date.equals("")){
            Toast.makeText(this,"Please enter the necessary fields or press Cancel to go back",Toast.LENGTH_SHORT).show();
            return;
        }

        String query="INSERT INTO money (name,date,notes) VALUES('"+amount+"' , '"+date+"' , '"+notes+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Expense details Saved",Toast.LENGTH_LONG).show();

    }
}
