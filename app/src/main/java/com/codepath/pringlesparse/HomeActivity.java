package com.codepath.pringlesparse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.pringlesparse.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

// Home Activity gives us the homepage/feed page of instagram:
public class HomeActivity extends AppCompatActivity {

    private static final String imagePath = "/storage/emulated/0/DCIM/Camera/IMG_20180712_090451.jpg";
    static final String TAG = HomeActivity.class.getSimpleName();
    private EditText descriptionInput;
    Button createButton;
    Button refreshButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo();

        descriptionInput = findViewById(R.id.description_et);
        refreshButton = findViewById(R.id.refresh_btn);
        createButton = findViewById(R.id.create_btn);
        // TODO - use this in profile page's activity
        logoutButton = findViewById(R.id.logout_btn);

//        if(isStoragePermissionGranted()){
////            Bitmap bm = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20151102_193132.jpg");
////            image.setImageBitmap(bm);
//
//        }

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description = descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();
                if(isStoragePermissionGranted()) {
                    final File file = new File(imagePath);
                    final ParseFile parseFile = new ParseFile(file);

                    createPost(description, parseFile, user);
                }
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });

        // TODO - implement logout button click listener with logout method
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logout();
//            }
//        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        loadTopPosts();
    }

    private void createPost(String description, ParseFile imageFile, ParseUser user) {
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "Create post success");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); ++i) {
                        Log.d("HomeActivity", "Post[" + i + "] = "
                                + objects.get(i).getDescription()
                                + "\nusername = " + objects.get(i).getUser().getUsername());
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
// TODO - implement logout button in profile page
//    private void logout() {
//        ParseUser.logOut();
//        ParseUser currentUser = ParseUser.getCurrentUser();
//    }

}
