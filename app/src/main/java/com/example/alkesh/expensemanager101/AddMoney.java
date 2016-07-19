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
    EditText expText;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

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
        }

        if(view==Cancel){
            Intent intent =new Intent(AddMoney.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    protected void insertIntoDatabase(){
        String amount = expText.getText().toString().trim();

        if(amount.equals("")){
            Toast.makeText(this,"Please enter your expense or press Cancel to go back",Toast.LENGTH_LONG).show();
            return;
        }

        String query="INSERT INTO money (name) VALUES('"+amount+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Expense Saved",Toast.LENGTH_LONG).show();
        finish();

    }
}
