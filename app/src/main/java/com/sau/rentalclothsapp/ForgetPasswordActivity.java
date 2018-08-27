package com.sau.rentalclothsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ForgetPasswordActivity extends AppCompatActivity {


    EditText edtemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        edtemail = findViewById(R.id.edtemail);

        Button backToLoginBtn = findViewById(R.id.backToLoginBtn);
        final Button txtsubmit = findViewById(R.id.txtsubmit);
       /* backToLoginBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.leftarrow, 0, 0, 0);
        txtsubmit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rightarrow, 0, 0, 0);*/

        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtemail.getText().toString().isEmpty())
                {
                    Toasty.success(ForgetPasswordActivity.this, "Done!", Toast.LENGTH_SHORT, true).show();
                }
                else
                {
                    Toasty.info(ForgetPasswordActivity.this, "Enter Your Email ", Toast.LENGTH_SHORT, true).show();
                }

               // Toast.makeText(ForgetPasswordActivity.this, "Done!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
