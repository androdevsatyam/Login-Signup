package com.example.satyam.codingchallenge;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.satyam.codingchallenge.R.id.basic;
import static com.example.satyam.codingchallenge.R.id.cancel_action;
import static com.example.satyam.codingchallenge.R.id.label_username;
import static com.example.satyam.codingchallenge.R.id.navigation_header_container;
import static com.example.satyam.codingchallenge.R.id.userpass;

public class Sign_up extends AppCompatActivity {

    TextView labid, labname, labpass, labrepass;
    EditText name, id, pass, repass,editText;
    Button signup, login;
    int i;

    InputMethodManager keyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle(R.string.sign_up);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

//***************--------- Initialize Components -------------**********//
        name = (EditText) findViewById(R.id.username);
        id = (EditText) findViewById(R.id.userid);
        pass = (EditText) findViewById(R.id.userpass);
        repass = (EditText) findViewById(R.id.repass);

        labname = (TextView) findViewById(label_username);
        labid = (TextView) findViewById(R.id.label_userid);
        labpass = (TextView) findViewById(R.id.label_userpass);
        labrepass = (TextView) findViewById(R.id.label_repass);

        signup = (Button) findViewById(R.id.btn_signup);
        login = (Button) findViewById(R.id.btn_login);

        keyboard= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onStart() {
        super.onStart();

        id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                id.setBackground(getDrawable(R.drawable.edit_text_bg));
                name.setBackground(null);
                pass.setBackground(null);
                repass.setBackground(null);
            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                id.setBackground(null);
                name.setBackground(getDrawable(R.drawable.edit_text_bg));
                pass.setBackground(null);
                repass.setBackground(null);
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                id.setBackground(null);
                name.setBackground(null);
                pass.setBackground(getDrawable(R.drawable.edit_text_bg));
                repass.setBackground(null);
            }
        });
        repass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                id.setBackground(null);
                name.setBackground(null);
                pass.setBackground(null);
                repass.setBackground(getDrawable(R.drawable.edit_text_bg));
            }
        });

    }

/**********---- SignUP task--------************/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void signup(View view) {
        i = check();
        if (i == 0) {
            name.clearFocus();
            id.clearFocus();
            pass.clearFocus();
            repass.clearFocus();
            name.setText("");
            id.setText("");
            pass.setText("");
            repass.setText("");
            name.setHint(getString(R.string.success_signup));
            Snackbar.make(view, "Wanna Login, Touch Login!!", Snackbar.LENGTH_SHORT).show();
            login.setVisibility(View.VISIBLE);
        } else {
            switch (i) {
                case 1:
                    id.requestFocus();
                    id.setError(getString(R.string.emptyError));
                    custom(R.id.userid);
                    break;
                case 2:
                    id.requestFocus();
                    id.setError(getString(R.string.invalid_email));
                    custom(R.id.userid);
                    break;
                case 3:
                    name.requestFocus();
                    name.setError(getString(R.string.emptyError));
                    custom(R.id.username);
                    break;
                case 4:
                    pass.requestFocus();
                    pass.setError(getString(R.string.emptyError));
                    custom(R.id.userpass);
                    break;
                case 5:
                    pass.requestFocus();
                    pass.setError(getString(R.string.invalid_password));
                    custom(R.id.userpass);
                    break;
                case 6:
                    repass.requestFocus();
                    repass.setError(getString(R.string.emptyError));
                    custom(R.id.repass);
                    break;
                case 7:
                    repass.requestFocus();
                    repass.setError(getString(R.string.passnotmatch));
                    custom(R.id.repass);
            }
        }
    }



/**********---- set custom view on input boxes --------
* @param view is shows which view will be edit
*
 ************/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void custom(int view) {
        switch (view){
            case R.id.userid: id.setBackground(getDrawable(R.drawable.edit_text_bg));
                name.setBackground(null);
                pass.setBackground(null);
                repass.setBackground(null);
                break;
            case R.id.username: name.setBackground(getDrawable(R.drawable.edit_text_bg));
                id.setBackground(null);
                pass.setBackground(null);
                repass.setBackground(null);
                break;
            case R.id.userpass: pass.setBackground(getDrawable(R.drawable.edit_text_bg));
                name.setBackground(null);
                id.setBackground(null);
                repass.setBackground(null);
                break;
            case R.id.repass: repass.setBackground(getDrawable(R.drawable.edit_text_bg));
                name.setBackground(null);
                pass.setBackground(null);
                id.setBackground(null);
        }
    }

/**********---- Validate data & return Error code.--------************/
    public void login(View v){
        startActivity(new Intent(Sign_up.this, MainActivity.class));
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

/**********---- Validate data & return Error code.--------************/
    private int check() {
        if(id.getText().length()==0)
            return 1; // if id null;
        else if(!(id.getText().toString().contains("@") && id.getText().toString().contains(".")))
            return 2; // if id invalid;
        else if (name.getText().length()==0)
            return 3; // if name null;
        else if (pass.getText().length()==0)
            return 4; // if pass null;
        else if(pass.getText().length()<8)
            return 5; // if pass is sort;
        else if(repass.getText().length()==0)
            return 6; // if repass null;
        else if (!pass.getText().toString().equalsIgnoreCase(repass.getText().toString()))
            return 7; // if repass doesn't match;
        else
        return 0;
    }


/**********---- input box focus when label clicked --------************/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void focus(View view) {
        switch (view.getId()){
            case R.id.label_username:
                name.requestFocus();
                name.setBackground(getDrawable(R.drawable.edit_text_bg));
            break;
            case R.id.label_userid:
                id.requestFocus();
                    id.setBackground(getDrawable(R.drawable.edit_text_bg));
                break;
            case R.id.label_userpass:
                pass.requestFocus();
                    pass.setBackground(getDrawable(R.drawable.edit_text_bg));
                break;
            case R.id.label_repass:
                repass.requestFocus();
                    repass.setBackground(getDrawable(R.drawable.edit_text_bg));
        }
    }

/**********---- Hide Keyboard when touch outside of form --------************/
    public void hide(View view) {
        try {
            keyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){
            Toast.makeText(this,getString(R.string.error_unexpected),Toast.LENGTH_SHORT).show();
        }
    }

}
