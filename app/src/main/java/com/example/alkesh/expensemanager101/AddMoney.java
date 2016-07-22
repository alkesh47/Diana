package com.example.alkesh.expensemanager101;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

@TargetApi(Build.VERSION_CODES.N)
public class AddMoney extends AppCompatActivity implements View.OnClickListener{

    Button Save,Cancel;
    EditText expText,Notes;
    TextView Date;

    private SQLiteDatabase db;

    Calendar myCalendar = Calendar.getInstance();

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        Date=(TextView)findViewById(R.id.dateEditText);

        Notes=(EditText)findViewById(R.id.notesEditText);
        expText=(EditText)findViewById(R.id.expense);



         final DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR,i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();

            }
        };

        Date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    new DatePickerDialog(AddMoney.this, datePickerDialog,myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return false;
            }
        });


        OpenDatabase();


        Cancel=(Button)findViewById(R.id.cancelbutton);
        Cancel.setOnClickListener(this);

        Save = (Button)findViewById(R.id.savebutton);
        Save.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void updateLabel(){
        String myFormat = "dd-MM-YYYY";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(myFormat,Locale.US);
        Date.setText(String.valueOf(simpleDateFormat.format(myCalendar.getTime())));

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
        String tmp= Date.getText().toString().trim();
        String month = tmp.substring(3,5).toString();


        if(amount.equals("") || date.equals("")){
            Toast.makeText(this,"Please enter the necessary fields or press Cancel to go back",Toast.LENGTH_SHORT).show();
            return;
        }

        String query="INSERT INTO money (name,date,notes,months) VALUES('"+amount+"' , '"+date+"' , '"+notes+"' , '"+month+"');";
        db.execSQL(query);
        //Toast.makeText(getApplicationContext(),"Expense details Saved",Toast.LENGTH_LONG).show();

    }

}



