package com.example.application1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView etUsername, etPassword;
    TextView tvRegister, tvForgot;
    Button btnLogin;
    SaveShared saveShared;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgot = findViewById(R.id.tvForgot);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgot.setOnClickListener(this);

        saveShared = new SaveShared(this);

        checkLogin();
    }



    private void checkLogin(){
        userModel = saveShared.getUser();
        boolean statusLogin = userModel.isStatusLogin();
        if (statusLogin) {
            startActivity(new Intent(LoginActivity.this, ScreenActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String Username = etUsername.getText().toString();
                String Password = etPassword.getText().toString();
                if (TextUtils.isEmpty(Username)) {
                    etUsername.setError("Username Tidak Boleh Kosong");
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    etPassword.setError("Password Tidak Boleh Kosong");
                    return;
                }
                if (!isValidEmail(Username)) {
                    Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                validateLogin(Username, Password);
                break;
            case R.id.tvRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tvForgot:
                validateForgot();
                break;
        }
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void validateLogin(String email, String password) {
        userModel = saveShared.getUser();
        String saveEmail = userModel.getEmail();
        String savepassword = userModel.getPassword();
        if (email.equals(saveEmail) && password.equals(savepassword)) {
            startActivity(new Intent(LoginActivity.this, ScreenActivity.class));
            userModel.setStatusLogin(true);
            saveShared.setUser(userModel);
            finish();
        } else {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.accountNotRegist));
            alert.setTitle(getString(R.string.pleaseRegist));
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                }
            });
            alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();
        }
    }

    private void validateForgot() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        userModel = saveShared.getUser();
        String showPassword = userModel.getPassword();
        String halo = String.valueOf(userModel.isStatusLogin());
        alert.setTitle("Password Kamu Adalah : " + showPassword);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}