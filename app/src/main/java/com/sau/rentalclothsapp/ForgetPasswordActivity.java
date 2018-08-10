package com.sau.rentalclothsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        TextView backToLoginBtn = (TextView) findViewById(R.id.backToLoginBtn);
        TextView txtsubmit = (TextView) findViewById(R.id.txtsubmit);
       /* backToLoginBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.leftarrow, 0, 0, 0);
        txtsubmit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rightarrow, 0, 0, 0);*/

        backToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ForgetPasswordActivity.this, "Done!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
