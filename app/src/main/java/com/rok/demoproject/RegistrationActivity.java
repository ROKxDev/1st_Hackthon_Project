package com.rok.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.rok.demoproject.LoginPage_Activity;

public class RegistrationActivity extends AppCompatActivity {

     EditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
     Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Sign Up Button Click Listener
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                validateInputs();
            }
        });
    }

    private void validateInputs()
    {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty())
        {
            etFullName.setError("Please Enter Your Name");
            etFullName.requestFocus();
        }
        else if (phone.isEmpty())
        {
            etPhone.setError("Please Enter your Mobile No");
            etPhone.requestFocus();
        }
        else if (phone.length() != 10)
        {
            etPhone.setError("Mobile Number Length Must be 10");
            etPhone.requestFocus();
        }
        else if (email.isEmpty())
        {
            etEmail.setError("Please Enter Your Email ID");
            etEmail.requestFocus();
        }
        else if (!email.contains("@") || !email.contains(".com"))
        {
            etEmail.setError("Please Enter Valid Email ID");
            etEmail.requestFocus();
        }
        else if (password.isEmpty())
        {
            etPassword.setError("Please Enter Your Password");
            etPassword.requestFocus();
        }
        else if (password.length() < 8)
        {
            etPassword.setError("Password Must be more than 8");
            etPassword.requestFocus();
        }
        else if (!password.matches(".*[A-Z].*"))
        {
            etPassword.setError("Password Must contain 1 Upper Case");
            etPassword.requestFocus();
        }
        else if (!password.matches(".*[a-z].*"))
        {
            etPassword.setError("Password Must contain 1 Lower Case");
            etPassword.requestFocus();
        }
        else if (!password.matches(".*[0-9].*"))
        {
            etPassword.setError("Password Must contain 1 Number");
            etPassword.requestFocus();
        }
        else if (!password.matches(".*[@,$,%,&,!].*"))
        {
            etPassword.setError("Password Must contain 1 Special Symbol");
            etPassword.requestFocus();
        }
        else if (!password.equals(confirmPassword))
        {
            etConfirmPassword.setError("Password and Confirm Password not match");
            etConfirmPassword.requestFocus();
        }
        else
        {
            Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistrationActivity.this, LoginPage_Activity.class);
            startActivity(intent);
            finish();
        }
    }
}