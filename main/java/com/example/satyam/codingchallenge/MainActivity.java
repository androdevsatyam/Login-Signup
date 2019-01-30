package com.example.satyam.codingchallenge;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    String user = "user@gmail.com";
    String pass = "pass1to8,@";
    EditText userid, userpass;
    Button login, signup;

   private Boolean exit;

    InputMethodManager keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.log_in);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        userid = (EditText) findViewById(R.id.userid);
        userpass = (EditText) findViewById(R.id.userpass);
        login = (Button) findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.btn_signup);

        keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        View v= findViewById(android.R.id.content);
        exit=false;

    }

    @Override
    protected void onStart() {
        super.onStart();

        userid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    userpass.setBackground(null);
                    userid.setBackground(getDrawable(R.drawable.edit_text_bg));
                    userid.setTextSize(17);
                }
            }
        });

        userpass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    userid.setBackground(null);
                    if (userid.getText().toString().equals("")) {
                        userid.setError("This Field can't empty");
                    }
                    userpass.setBackground(getDrawable(R.drawable.edit_text_bg));
                    userpass.setTextSize(17);
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //***********---- Validate data--------*************//
                if (!isEmail() || !isPass()) {

                }
                //***********---- Checking Crediantials --------*************//
                else if (userid.getText().toString().equals(user) && userpass.getText().toString().equals(pass)) {
                    keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    userpass.setText("");
                    userid.setText("");
                    userid.setHint(getString(R.string.success));
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        userid.setBackground(getDrawable(R.drawable.error_bg));
                        userpass.setBackground(getDrawable(R.drawable.error_bg));
                        userid.setError(getString(R.string.unsuccess));
                        userpass.setError(getString(R.string.unsuccess));
                    }
                    Snackbar.make(v, "If new User, Click Sign UP", Snackbar.LENGTH_LONG).show();
                    signup.setVisibility(View.VISIBLE);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Sign_up.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error occur", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        userid.clearFocus();
        userpass.clearFocus();
        onStart();
    }

    public Boolean isEmail() {

        if (userid.getText().toString().contains("@") && userid.getText().toString().contains("."))
            return true;
        else if (userid.getText().toString().equals("")) {
            userid.requestFocus();
            userid.setError(getString(R.string.emptyError));
            return false;
        } else {
            userid.requestFocus();
            userid.setError(getString(R.string.invalid_email));
            return false;
        }
    }

    public Boolean isPass(){
        if (userpass.getText().length()>=8)
            return true;
        else if (userpass.getText().toString().equals("")) {
            userpass.requestFocus();
            userpass.setError(getString(R.string.emptyError));
            return false;
        } else {
            userpass.requestFocus();
            userpass.setError(getString(R.string.invalid_password));
            return false;
        }
    }
    public void hide(View view) {
        try {
            keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){
            Toast.makeText(this,getString(R.string.error_unexpected),Toast.LENGTH_SHORT).show();
        }
    }

//---------------- Exit Code -----------------------//
    @Override
    public void onBackPressed() {
        Timer timer=new Timer();
        if(exit){
            finish();
        }
        else {
            Toast.makeText(MainActivity.this,"Double tap for exit",Toast.LENGTH_SHORT).show();
        }
        exit=true;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
              exit=false;
            }
        },2000);
    }
}

