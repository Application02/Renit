package com.sau.rentalclothsapp.Renter;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sau.rentalclothsapp.R;

import java.util.Calendar;

public class Equipment_Fragment extends Fragment {

    View view;

    RadioButton yes,no;
    RadioGroup radio_grp;
    TextView txtStartDate,txtEndDate,txtStartTime,txtEndTime;
    private int mYear, mMonth, mDay, mYear1, mMonth1, mDay1,mHour, mMinute,mHour1, mMinute1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.equipment_fragment, container, false);

        radio_grp= view.findViewById(R.id.transportation);
        yes =  radio_grp.findViewById(R.id.yes);
        no =  radio_grp.findViewById(R.id.no);

        txtStartDate= view.findViewById(R.id.in_startdate);
        txtEndDate= view.findViewById(R.id.in_enddate);

        txtStartTime= view.findViewById(R.id.in_starttime);
        txtEndTime= view.findViewById(R.id.in_endtime);


        transportationrequired();
        selectdate();

        selecttime();

        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.equipment_fragment_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    private void selecttime() {

        txtStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtStartTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });

        txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour1 = c.get(Calendar.HOUR_OF_DAY);
                mMinute1 = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtEndTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour1, mMinute1, false);
                timePickerDialog.show();

            }
        });


    }


    private void transportationrequired() {

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void selectdate() {

        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtStartDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });


        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear1 = c.get(Calendar.YEAR);
                mMonth1 = c.get(Calendar.MONTH);
                mDay1 = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear1, mMonth1, mDay1);
                datePickerDialog.show();

            }
        });

    }

}
