package com.somyco.myimdb;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by nicolasmas on 23/2/15.
 */
public class DetailActivity extends ActionBarActivity{

    // Class Var

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.detail_movie);

        // Enable the "Up" button for more navigation options
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access the imageview from XML
        ImageView imageView = (ImageView) findViewById(R.id.detail_movie_poster);
        TextView  titleView = (TextView) findViewById(R.id.detail_text_title);
        TextView  yearView = (TextView) findViewById(R.id.detail_text_year);



    }

}
