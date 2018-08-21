package com.sau.rentalclothsapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    EditText edtfirstname, edtsurname, edtmail, edtphoneno, edtpassword, edtconfirmpassword;
    String firstname = null, surname = null;
    String email = null;
    String item = "Renter";
    RadioButton renter, owner;
    RadioGroup radioGroup;
    CheckBox space, equipment, dresses;
    Button buttonRegister;
    LinearLayout checkboxlayout;
    View view1;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    // isValidEmailAddress: Check the email address is OK
    public static boolean isValidEmailAddress(String emailAddress) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(emailAddress);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);
        renter = radioGroup.findViewById(R.id.renter);
        renter.setChecked(true);
        owner = radioGroup.findViewById(R.id.owner);
        renter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = "Renter";


            }
        });
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = "Owner";
            }
        });
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        space = (CheckBox) findViewById(R.id.checkBox);
        equipment = (CheckBox) findViewById(R.id.checkBox2);
        dresses = (CheckBox) findViewById(R.id.checkBox3);
        checkboxlayout = findViewById(R.id.checkboxlayout);
        view1 = findViewById(R.id.view);

        edtfirstname = findViewById(R.id.edtfirstname);
        edtsurname = findViewById(R.id.edtsurname);
        edtmail = findViewById(R.id.edtmail);
        edtphoneno = findViewById(R.id.edtphoneno);
        edtpassword = findViewById(R.id.edtpassword);
        edtconfirmpassword = findViewById(R.id.edtconfirmpassword);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonRegister = (Button) findViewById(R.id.buttonRegister);


        intlaiton();
    }

    private void intlaiton() {


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate(edtfirstname) && validate(edtsurname) && validate(edtmail) && validate(edtphoneno) && validate(edtpassword) && validate(edtconfirmpassword)) {
                    firstname = edtfirstname.getText().toString();
                    surname = edtsurname.getText().toString();
                    email = edtmail.getText().toString();


                    if (isValidEmailAddress(email)) {
                        if (edtpassword.getText().toString().equals(edtconfirmpassword.getText().toString())) {
                            editor.putString("type", item); // Storing string
                            editor.putString("firstname", firstname);
                            editor.putString("surname", surname);


                            if (checkboxlayout.getVisibility() == View.GONE) {
                                editor.commit(); // commit changes

                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                if (space.isChecked() || equipment.isChecked() || dresses.isChecked()) {

                                    if (space.isChecked() && equipment.isChecked() && dresses.isChecked()) {
                                        editor.putString("space", "space");
                                        editor.putString("equipment", "equipment");
                                        editor.putString("dresses", "dresses");

                                    } else if (equipment.isChecked() && dresses.isChecked()) {
                                        editor.putString("equipment", "equipment");
                                        editor.putString("dresses", "dresses");
                                    } else if (space.isChecked() && dresses.isChecked()) {
                                        editor.putString("space", "space");
                                        editor.putString("dresses", "dresses");

                                    } else if (space.isChecked()) {

                                        editor.putString("space", "space");

                                    } else if (equipment.isChecked()) {

                                        editor.putString("equipment", "equipment");

                                    } else if (dresses.isChecked()) {

                                        editor.putString("dresses", "dresses");
                                    } else if (space.isChecked() && equipment.isChecked()) {
                                        editor.putString("space", "space");
                                        editor.putString("equipment", "equipment");
                                    }

                                    editor.commit(); // commit changes

                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(RegisterActivity.this, "Please Select Category  ", Toast.LENGTH_SHORT).show();

                                }

                            }


                        } else {
                            Toast.makeText(RegisterActivity.this, "password Not Match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(RegisterActivity.this, "please Select Type", Toast.LENGTH_SHORT).show();
                }


                // finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.renter) {
                    checkboxlayout.setVisibility(View.GONE);

                } else {
                    checkboxlayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    //check editText Null or Not
    private boolean validate(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {


            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }


}