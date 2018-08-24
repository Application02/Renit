package com.sau.rentalclothsapp.Renter;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.colorpicker.ColorPickerDialog;
import com.android.colorpicker.ColorPickerSwatch;
import com.sau.rentalclothsapp.R;

import java.util.Calendar;


public class Dresses_Fragment extends Fragment {

    View view;

    at.markushi.ui.CircleButton imgcolor;
    android.app.FragmentManager fm;
    Spinner spin,spinerSize;
    RadioButton yes, no;
    AlertDialog alert;
    RadioGroup radio_grp;


    TextView txtDate;
    String[] days = {"1", "2", "3", "4", "5","other"};
    String[] size = {"S","XS","M","L","XL","XXL"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.dresses_fragment, container, false);

        getActivity().setTitle(" Home");
       // getActivity().setTitle(" ");
        imgcolor = view.findViewById(R.id.imgcolor);
        spin = view.findViewById(R.id.spinnerRentaldays);
        spinerSize = view.findViewById(R.id.spinnerSize);
        radio_grp = view.findViewById(R.id.shipment);
        yes = radio_grp.findViewById(R.id.yes);
        no = radio_grp.findViewById(R.id.no);

        //  btnDatePicker=(Button)view.findViewById(R.id.btn_date);
        txtDate = view.findViewById(R.id.in_date);


        selectsize();

        selectcolor();

        selectdays();

        shippingrequired();

        selectdate();


        return view;

    }

    private void selectsize() {


        spinerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
              //  Toast.makeText(getContext(),size[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(), R.layout.spinner_row, size);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinerSize.setAdapter(aa);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dresses_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    private void selectdate() {

        txtDate.setOnClickListener(new View.OnClickListener() {
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

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });

    }

    private void shippingrequired() {

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void selectdays() {


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(getContext(),days[position] , Toast.LENGTH_LONG).show();
                if (days[position].equals("other"))
                {
                    // get prompts.xml view
                    LayoutInflater li = LayoutInflater.from(getContext());
                    View promptsView = li.inflate(R.layout.dresses_spinner_dailog, null);
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setView(promptsView);
                    // alertDialog.setTitle("Update Profile");
                    alertDialog.setMessage("Update Profile Image");

                    Button btnsubmit = (Button) promptsView.findViewById(R.id.btnsubmitdailog);
                   /* TextView txtgallery = promptsView.findViewById(R.id.txtgallery);
                    TextView txtcancel = promptsView.findViewById(R.id.txtcancel);*/
                    btnsubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alert.dismiss();

                        }
                    });
                   /* txtgallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Toast.makeText(getActivity(), "Gallery", Toast.LENGTH_SHORT).show();
                        }
                    });
                    txtcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alert.dismiss();
                        }
                    });*/

                    alert = alertDialog.create();
                    alert.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(),  R.layout.spinner_row, days);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private void selectcolor() {
        final ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.initialize(R.string.color_picker_default_title,
                new int[]{
                        getResources().getColor(R.color.Black),
                      //also use this  ContextCompat.getColor(Objects.requireNonNull(getContext()),R.color.Black),
                        /* getResources().getColor(R.color.Navy),*/
                        getResources().getColor(R.color.DarkBlue),
                        /* getResources().getColor(R.color.MediumBlue),*/
                        getResources().getColor(R.color.Blue),
                        /* getResources().getColor(R.color.DarkGreen),*/
                        getResources().getColor(R.color.Green),
                        /* getResources().getColor(R.color.Teal),*/
                        /* getResources().getColor(R.color.DarkCyan),*/
                        getResources().getColor(R.color.DeepSkyBlue),
                        getResources().getColor(R.color.DarkTurquoise),
                        getResources().getColor(R.color.MediumSpringGreen),
                        getResources().getColor(R.color.Lime),
                        /* getResources().getColor(R.color.SpringGreen),*/
                        /*  getResources().getColor(R.color.Cyan),*/
                        getResources().getColor(R.color.Aqua),
                        getResources().getColor(R.color.MidnightBlue),
                        /* getResources().getColor(R.color.DodgerBlue),*/
                        getResources().getColor(R.color.LightSeaGreen),
                        /*getResources().getColor(R.color.ForestGreen),*/
                        getResources().getColor(R.color.SeaGreen),
                        getResources().getColor(R.color.DarkSlateGray),
                        /*getResources().getColor(R.color.LimeGreen),*/
                        getResources().getColor(R.color.MediumSeaGreen),
                        getResources().getColor(R.color.Turquoise),
                        getResources().getColor(R.color.RoyalBlue),
                        getResources().getColor(R.color.SteelBlue),
                        getResources().getColor(R.color.DarkSlateBlue),
                        getResources().getColor(R.color.MediumTurquoise),
                        getResources().getColor(R.color.Indigo),
                        getResources().getColor(R.color.DarkOliveGreen),
                        getResources().getColor(R.color.CadetBlue),
                        getResources().getColor(R.color.CornflowerBlue),
                        getResources().getColor(R.color.MediumAquamarine),
                        getResources().getColor(R.color.DimGray),
                        getResources().getColor(R.color.SlateBlue),
                        getResources().getColor(R.color.OliveDrab),
                        getResources().getColor(R.color.SlateGray),
                        /* getResources().getColor(R.color.LightSlateGray),*/
                        getResources().getColor(R.color.MediumSlateBlue),
                        getResources().getColor(R.color.LawnGreen),
                        /*    getResources().getColor(R.color.Chartreuse),*/
                        getResources().getColor(R.color.Aquamarine),
                        getResources().getColor(R.color.Maroon),
                        getResources().getColor(R.color.Purple),
                        getResources().getColor(R.color.Olive),
                        getResources().getColor(R.color.Gray),
                        getResources().getColor(R.color.SkyBlue),
                        /*getResources().getColor(R.color.LightSkyBlue),*/
                        getResources().getColor(R.color.BlueViolet),
                        /* getResources().getColor(R.color.DarkRed),*/
                        getResources().getColor(R.color.DarkMagenta),
                        getResources().getColor(R.color.SaddleBrown),
                        getResources().getColor(R.color.DarkSeaGreen),
                        getResources().getColor(R.color.LightGreen),
                        getResources().getColor(R.color.MediumPurple),
                        getResources().getColor(R.color.DarkViolet),
                        /*getResources().getColor(R.color.PaleGreen),*/
                        /*getResources().getColor(R.color.DarkOrchid),*/
                        getResources().getColor(R.color.YellowGreen),
                        getResources().getColor(R.color.Sienna),
                        /*  getResources().getColor(R.color.Brown),*/
                        getResources().getColor(R.color.DarkGray),
                        getResources().getColor(R.color.LightBlue),
                        getResources().getColor(R.color.GreenYellow),
                        getResources().getColor(R.color.PaleTurquoise),
                        getResources().getColor(R.color.LightSteelBlue),
                        /*getResources().getColor(R.color.PowderBlue),*/
                        /* getResources().getColor(R.color.FireBrick),*/
                        getResources().getColor(R.color.DarkGoldenrod),
                        getResources().getColor(R.color.MediumOrchid),
                        getResources().getColor(R.color.RosyBrown),

                        getResources().getColor(R.color.DarkKhaki),
                        getResources().getColor(R.color.Silver),
                        getResources().getColor(R.color.MediumVioletRed),
                        getResources().getColor(R.color.IndianRed),
                        getResources().getColor(R.color.Peru),
                        getResources().getColor(R.color.Chocolate),
                        getResources().getColor(R.color.Tan),
                        getResources().getColor(R.color.LightGrey),
                        getResources().getColor(R.color.Thistle),
                        /* getResources().getColor(R.color.Orchid),*/
                        getResources().getColor(R.color.Goldenrod),
                        getResources().getColor(R.color.PaleVioletRed),
                        getResources().getColor(R.color.Crimson),
                        getResources().getColor(R.color.Gainsboro),
                        getResources().getColor(R.color.Plum),
                        getResources().getColor(R.color.BurlyWood),
                        getResources().getColor(R.color.LightCyan),
                        getResources().getColor(R.color.Lavender),
                        getResources().getColor(R.color.DarkSalmon),
                        /* getResources().getColor(R.color.Violet),*/
                        getResources().getColor(R.color.PaleGoldenrod),
                        getResources().getColor(R.color.LightCoral),
                        getResources().getColor(R.color.Khaki),
                        /* getResources().getColor(R.color.AliceBlue),*/
                        getResources().getColor(R.color.Honeydew),
                        /*  getResources().getColor(R.color.Azure),*/
                        getResources().getColor(R.color.SandyBrown),
                        getResources().getColor(R.color.Wheat),
                        /* getResources().getColor(R.color.Beige),
                         getResources().getColor(R.color.WhiteSmoke),*/
                        getResources().getColor(R.color.MintCream),
                        getResources().getColor(R.color.GhostWhite),
                        /*  getResources().getColor(R.color.Salmon),*/
                        getResources().getColor(R.color.AntiqueWhite),
                        /* getResources().getColor(R.color.Linen),*/
                        getResources().getColor(R.color.LightGoldenrodYellow),
                        getResources().getColor(R.color.OldLace),
                        getResources().getColor(R.color.Red),
                        getResources().getColor(R.color.Magenta),
                        /*getResources().getColor(R.color.Fuchsia),*/
                        getResources().getColor(R.color.DeepPink),
                        getResources().getColor(R.color.OrangeRed),
                        /* getResources().getColor(R.color.Tomato),*/
                        getResources().getColor(R.color.HotPink),
                        /*  getResources().getColor(R.color.Coral),*/
                        getResources().getColor(R.color.DarkOrange),
                        getResources().getColor(R.color.LightSalmon),
                        getResources().getColor(R.color.Orange),
                        /*  getResources().getColor(R.color.LightPink),*/
                        getResources().getColor(R.color.Pink),
                        getResources().getColor(R.color.Gold),
                        getResources().getColor(R.color.PeachPuff),
                        /* getResources().getColor(R.color.NavajoWhite),*/
                        getResources().getColor(R.color.Moccasin),
                        getResources().getColor(R.color.Bisque),
                        /*getResources().getColor(R.color.MistyRose),*/
                        /* getResources().getColor(R.color.BlanchedAlmond),*/
                        getResources().getColor(R.color.PapayaWhip),
                        getResources().getColor(R.color.LavenderBlush),
                        getResources().getColor(R.color.Seashell),
                        /* getResources().getColor(R.color.Cornsilk),*/
                        getResources().getColor(R.color.LemonChiffon),
                        /*   getResources().getColor(R.color.FloralWhite),*/
                        getResources().getColor(R.color.Snow),
                }, getResources().getColor(R.color.Black), 4, 4);

        colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
            @Override
            public void onColorSelected(int colour) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imgcolor.setColor(colour);
                }
            }
        });
        fm = getActivity().getFragmentManager();


        imgcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                colorPickerDialog.show(fm, "colorpicker");
            }
        });


    }

}