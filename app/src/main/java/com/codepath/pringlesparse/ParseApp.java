package com.codepath.pringlesparse;

import android.app.Application;

import com.codepath.pringlesparse.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("pringle")
                .clientKey("this-is-a-very-strong-password")
                .server("http://pringles-parse.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
