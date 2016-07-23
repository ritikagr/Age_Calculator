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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends Activity{

    //For Date Picker

    private TextView pEdit_Day ,pEdit_Month ,pEdit_Year ,pBirth_Day ,pBirth_Month ,pBirth_Year ,years,months,days,next_birth_months,next_birth_days;
    private int pDay ,pMonth ,pYear;
    private boolean flag=false;

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

        years = (TextView) findViewById(R.id.years);
        months = (TextView) findViewById(R.id.months);
        days = (TextView) findViewById(R.id.days);

        next_birth_months = (TextView) findViewById(R.id.next_birthday_months);
        next_birth_days = (TextView) findViewById(R.id.next_birthday_days);

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

        flag = true;
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
        if(flag==false)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Please Select Date of Birth!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            //for today date
            int d=Integer.parseInt(pEdit_Day.getText().toString()),m=Integer.parseInt(pEdit_Month.getText().toString()),y=Integer.parseInt(pEdit_Year.getText().toString());
            //for birth date
            int D=Integer.parseInt(pBirth_Day.getText().toString()),M=Integer.parseInt(pBirth_Month.getText().toString()),Y=Integer.parseInt(pBirth_Year.getText().toString());
            int year=0,month=0,day=0,day1=0,month1=0;
            int[] days_in_month ={31,28,31,30,31,30,31,31,30,31,30,31};

            if(Y>=y && M>=m && D>d)
            {
                Toast toast = Toast.makeText(getApplicationContext(),"Please select proper 'Date of Birth'!", Toast.LENGTH_SHORT);
                toast.show();
            }
            else
            {
                if(M<m)
                    year=y-Y;
                else
                    year=y-Y-1;

                if(D<=d)
                    month=m-M;
                else
                    month=m-M-1;

                if(D<=d)
                    day=d-D;
                else
                {
                    if((m-1)==2)
                    {
                        if((y%4==0 && y%100!=0) || (y%100==0 && y%400==0))
                            day = d + 29-D;
                        else
                            day = d + 28-D;
                    }
                    else
                    {
                        day = d + days_in_month[m-2]-D;
                    }
                }

                years.setText(String.valueOf(year));
                months.setText(String.valueOf(month));
                days.setText(String.valueOf(day));
            }

            if(d<=D)
                month1=M-m;
            else
                month1=M-m-1;

            if(d<=D)
                day1=D-d;
            else
            {
                if((M-1)==2)
                {
                    if((Y%4==0 && Y%100!=0) || (Y%100==0 && Y%400==0))
                        day1 = D + 29-d;
                    else
                        day1 = D + 28-d;
                }
                else
                {
                    day1 = D + days_in_month[M-2]-d;
                }
            }

            next_birth_months.setText(String.valueOf(month1));
            next_birth_days.setText(String.valueOf(day1));

        }
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
