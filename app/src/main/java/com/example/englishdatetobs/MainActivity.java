package com.example.englishdatetobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
   DatePicker datePicker;
   TextView tVDate;
   Button convertBtn;
   int nep_year[]={2072,2073,2074,2075,2076,2077};
   int dates_nepali[][]={{31,32,31,32,31,30,30,29,30,29,29,30},
                         {31,32,31,32,31,30,30,30,29,29,30,31},
                         {31,31,31,32,31,31,30,29,30,29,30,30},
                         {31,31,32,31,31,31,30,29,30,29,30,30},
                         {31,32,31,32,31,30,30,30,29,29,30,30},
                         {31,32,31,32,31,30,30,30,29,30,29,31}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker=findViewById(R.id.datePicker);
        tVDate=findViewById(R.id.date);
        convertBtn=findViewById(R.id.convertBtn);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year,month,date;
                year=datePicker.getYear();
                month=datePicker.getMonth();
                date=datePicker.getDayOfMonth();
                try {
                    convertToNepali(year,month,date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    private void convertToNepali(int year, int month, int date) throws ParseException {
        int curr_nepali_year,curr_nepali_month,curr_nepali_date;
        int i,j,k;
        int index=0;

        curr_nepali_year=2072;
        curr_nepali_month=1;
        curr_nepali_date=1;

        Log.d("index", "convertToNepali: "+dates_nepali[0].length);

        String inp_date=year+"/"+month+"/"+date;



        String intial_date=2015+"/"+3+"/"+14;

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");

        java.util.Date date1= dateFormat.parse(inp_date);
        Date date2= dateFormat.parse(intial_date);

        long diff=(date1.getTime()-date2.getTime())/(1000*60*60*24);

        int diff_days= (int) TimeUnit.DAYS.toDays(diff);
        Log.d("index", "convertToNepali: "+diff_days);




       for(i=0;i<nep_year.length;i++)
       {
           for(j=0;j<dates_nepali[i].length;j++)
           {
               for(k=0;k<=dates_nepali[i][j];k++)
               {
//                   Log.d("tell", "convertToNepali: ");
                   if(diff_days<=index)
                   {
                       break;
                   }

                   index++;

                   //31


                   curr_nepali_date++;


                   if(curr_nepali_date>dates_nepali[i][j])
                   {
                       curr_nepali_month+=1;
                       curr_nepali_date=1;
                   }

                   if(curr_nepali_month>12)
                   {
                       curr_nepali_month=1;
                       curr_nepali_year+=1;
                   }

//                   if(curr_nepali_date)






               }

           }
       }

       tVDate.setText(curr_nepali_date+"/"+curr_nepali_month+"/"+curr_nepali_year);


    }
}