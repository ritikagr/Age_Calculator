package com.iit.ritik.agecalculator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends Activity{

    //For Date Picker

    private TextView pEdit_Day ,pEdit_Month ,pEdit_Year ,pBirth_Day ,pBirth_Month ,pBirth_Year;
    private int pDay ,pMonth ,pYear;

    static final int DATE_DIALOG_ID = 0;
    private int d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //For Date Picker

        pEdit_Day = (TextView) findViewById(R.id.day);
        pEdit_Month = (TextView) findViewById(R.id.month);
        pEdit_Year = (TextView) findViewById(R.id.year);

        pBirth_Day = (TextView) findViewById(R.id.birth_day);
        pBirth_Month = (TextView) findViewById(R.id.birth_month);
        pBirth_Year = (TextView) findViewById(R.id.birth_year);
        final Calendar cal = Calendar.getInstance();
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        pMonth = cal.get(Calendar.MONTH);
        pYear = cal.get(Calendar.YEAR);

        updateDisplay();
    }


    //Start Date Picker
    public void updateDisplay()
    {
            pEdit_Day.setText((pDay < 10) ? ("0" + String.valueOf(pDay)) : (String.valueOf(pDay)));
            pEdit_Month.setText((pMonth < 9) ? ("0" + String.valueOf(pMonth + 1)) : (String.valueOf(pMonth + 1)));
            pEdit_Year.setText(String.valueOf(pYear));
    }

    public void updateBirthDisplay()
    {
        pBirth_Day.setText((pDay < 10) ? ("0" + String.valueOf(pDay)) : (String.valueOf(pDay)));
        pBirth_Month.setText((pMonth < 9) ? ("0" + String.valueOf(pMonth + 1)) : (String.valueOf(pMonth + 1)));
        pBirth_Year.setText(String.valueOf(pYear));
    }

    public void pickDate(View view)
    {
        d1=1;
        showDialog(DATE_DIALOG_ID);
    }

    public void pickBirthDate(View view)
    {
        d2=1;
        d1=2;
        showDialog(DATE_DIALOG_ID);
    }
    private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            pYear = year;
            pMonth = month;
            pDay = day;
            if(d1==1)
            updateDisplay();
            else if(d2==1)
                updateBirthDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id)
        {
            case DATE_DIALOG_ID:
            {
                return new DatePickerDialog(this ,pDateSetListener ,pYear ,pMonth ,pDay);
            }
        }
        return null;
    }

    //End Date Picker


    public void calculate(View view)
    {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
