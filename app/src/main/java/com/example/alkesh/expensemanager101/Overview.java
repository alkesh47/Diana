package com.example.alkesh.expensemanager101;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Overview extends AppCompatActivity {

    String total;
    TextView expense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        expense=(TextView)findViewById(R.id.expenseTV);

        Intent i=getIntent();
        total=i.getStringExtra("text");

        expense.setText(total);

    }
}
