package com.codepath.pringlesparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CaptureActivity extends AppCompatActivity {

    private EditText descriptionInput;
    private Button createButton;
    private Button logoutButton;
    private ImageView imageView;
    private Button captureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        descriptionInput = findViewById(R.id.description_et);
        createButton = findViewById(R.id.create_btn);
        logoutButton = findViewById(R.id.logout_btn);
//        imageView = findViewById(R.id.photo);
//        captureButton = findViewById(R.id.captureBtn);

//        createButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final String description = descriptionInput.getText().toString();
//                final ParseUser user = ParseUser.getCurrentUser();
//
//                if(file == null || description == null){
//                    Toast.makeText(CaptureActivity.super.getBaseContext(), "Must include an image and caption in order to post", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                final ParseFile parseFile = new ParseFile(file);
//                parseFile.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        createPost(description, parseFile, user);
//                    }
//                });
//            }
//
//        });

    }
}
