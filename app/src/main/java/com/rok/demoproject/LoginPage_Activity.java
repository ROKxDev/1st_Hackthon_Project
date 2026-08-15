package com.rok.demoproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage_Activity extends AppCompatActivity {

    EditText etMobileNumber, etPassword;
    CheckBox cbShowPassword;

    TextView tvLoginCreateAccount;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        etMobileNumber = findViewById(R.id.etMobileNumber);
        etPassword = findViewById(R.id.etPassword);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvLoginCreateAccount=findViewById(R.id.tvLoginCreateAccount);

        tvLoginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        LoginPage_Activity.this,RegistrationActivity.class);

                startActivity(i);
                finish();

            }
        });

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    etPassword.setTransformationMethod(
                            HideReturnsTransformationMethod.getInstance()
                    );

                } else {

                    etPassword.setTransformationMethod(
                            PasswordTransformationMethod.getInstance()
                    );
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {

                if (etMobileNumber.getText().toString().isEmpty()) {

                    etMobileNumber.setError("Please Enter Your Mobile No");

                } else if (etMobileNumber.getText().toString().length() < 10) {

                    etMobileNumber.setError("Mobile No Must Be 10 Digits");

                } else if (etPassword.getText().toString().isEmpty()) {

                    etPassword.setError("Please Enter Your Password");

                } else if (etPassword.getText().toString().length() < 8) {

                    etPassword.setError("Password Must Be At Least 8 Characters");

                } else {

                    Intent i = new Intent(
                            LoginPage_Activity.this,HomeActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        });
    }
}