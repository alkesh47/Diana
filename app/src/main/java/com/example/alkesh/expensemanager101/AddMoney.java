package com.example.alkesh.expensemanager101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMoney extends AppCompatActivity {

    Button Save,Cancel;
    EditText expText;
    int[] expense=new int[100];
    int temp=900;
    int c=0;
    int sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        Cancel=(Button)findViewById(R.id.cancelbutton);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddMoney.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Save = (Button)findViewById(R.id.savebutton);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculate();
                send();
            }
        });
    }

    public void Calculate(){
        expText = (EditText)findViewById(R.id.expense);
        temp=Integer.parseInt(String.valueOf(expText.getText()));
        expense[c]=temp;
        sum+= temp;
        c++;
    }

    public void send(){
        Intent i=new Intent(AddMoney.this,Overview.class);
        String s=String.valueOf(sum);
        i.putExtra("text",s);
        startActivity(i);
        finish();
        Toast.makeText(AddMoney.this,"Your expense was saved "+sum,Toast.LENGTH_SHORT).show();
    }
}
