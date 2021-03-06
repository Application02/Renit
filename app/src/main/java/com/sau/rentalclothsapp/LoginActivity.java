package com.sau.rentalclothsapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sau.rentalclothsapp.Owner.OwnerActivity;
import com.sau.rentalclothsapp.Renter.RenterActivity;

import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 007;
    SharedPreferences pref;
    String whichActivity, category1, category2, category3;
    Button btnLogin;
    EditText edtloginmail, edtloginpwd;

    SharedPreferences preferences;



    Spinner mSpinner;
    TextView txtSelectValue;

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //sharedpreference for check user already login or not
        preferences = getApplicationContext().getSharedPreferences("logindata", 0);
        // SharedPreferences.Editor editor = pref.edit();

        try{
            preferences.getString("value", null);

            String temp =  preferences.getString("value", null);

            if (temp.equals("1"))
            {
                Intent i = new Intent(getApplicationContext(),OwnerActivity.class);
                startActivity(i);
                finish();
            }
            else if (temp.equals("2"))
            {
                Intent intent = new Intent(getApplicationContext(),RenterActivity.class);
                startActivity(intent);
                finish();
            }


        }catch (Exception e){

        }

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        try {
            whichActivity = pref.getString("type", null);
            Log.d(TAG, "whichActivity: " + whichActivity);

            category1 = pref.getString("space", null);
            category2 = pref.getString("equipment", null);
            category3 = pref.getString("dresses", null);

            Log.d(TAG, "onCreate: " + category1 + category2 + category3);

            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }


        //login with G+
        btnLogin = findViewById(R.id.btnLogin);


        edtloginmail = findViewById(R.id.edtloginname);
        edtloginpwd = findViewById(R.id.edtloginpwd);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try {
                   if (whichActivity.isEmpty() || whichActivity.equals("null") || whichActivity.equals(" ") || whichActivity.equals(""))
                   {
                       Toasty.error(LoginActivity.this, "Register First", Toast.LENGTH_SHORT, true).show();
                   }
               }
               catch (NullPointerException e)
               {
                   e.printStackTrace();
                   Toasty.error(LoginActivity.this, "Register First", Toast.LENGTH_SHORT, true).show();
               }

/*
                if (validate(edtloginmail) && validate(edtloginpwd)) {


                    if (whichActivity.equals("Renter")) {
                        Intent intent = new Intent(getApplicationContext(), RenterActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), OwnerActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }
*/
                //this code for only testing time after texsting remove this and remove commite above code

                try {


                    switch (whichActivity) {
                        case "Renter": {
                            Intent intent = new Intent(getApplicationContext(), RenterActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        case "Owner": {
                            Intent intent = new Intent(getApplicationContext(), OwnerActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        }
                        default:
                            Toasty.error(LoginActivity.this, "Register First", Toast.LENGTH_SHORT, true).show();
                          //  Toast.makeText(LoginActivity.this, "Register first", Toast.LENGTH_SHORT).show();
                            break;
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }


        });
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>First time here? </font><font color='#079A49'>Register</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ForgetPassword();
    }

    private void ForgetPassword() {
        TextView txtforgetpwd = findViewById(R.id.txtforgetpwd);

        txtforgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
                finish();
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

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);


        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.

    }

    @Override
    public void onStart() {
        super.onStart();

    }


}