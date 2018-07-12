package com.codepath.pringlesparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText usernameText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        submitButton = findViewById(R.id.submit_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                signup(username, password, new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Log.d("RegisterActivity", "Sign up successful");
                            Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Log.e("RegisterActivity", "Sign up failure");
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void signup(String username, String password, SignUpCallback callback) {
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(callback);
    }
}
