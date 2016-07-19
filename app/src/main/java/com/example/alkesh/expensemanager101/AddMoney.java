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

    /*int[] expense=new int[100];
    int temp=900;
    int c=0;
    int sum=0;
    String DBOnce;*/

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


        CreateDatabase();
            /*Calculate();*/
            /*send();*/
        }


    /*public void Calculate(){
        temp=Integer.parseInt(String.valueOf(expText.getText()));
    }*/

   /* public void send(){
        Intent i=new Intent(AddMoney.this,Overview.class);
        String s=String.valueOf(temp);
        i.putExtra("text",s);
        startActivity(i);
        finish();
        Toast.makeText(AddMoney.this,"Your expense was saved "+temp,Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onClick(View view) {

        if(view== Save){

            insertIntoDatabase();
            /*Calculate();*/
            /*send();*/
        }

        if(view==Cancel){
            Intent intent =new Intent(AddMoney.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    protected void CreateDatabase(){
        db=openOrCreateDatabase("MoneyDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS money(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR);");
    }

    /*protected void createDatabase(){
        db=openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,address VARCHAR);");
    }*/

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
        /*send();*/

    }

   /* protected void insertIntoDB(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAdd.getText().toString().trim();
        if(name.equals("") || add.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        String query = "INSERT INTO persons (name,address) VALUES('"+name+"', '"+add+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }*/

}
