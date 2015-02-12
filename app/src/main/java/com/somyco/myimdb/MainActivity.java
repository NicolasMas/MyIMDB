package com.somyco.myimdb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    // Start of custom vars
    TextView m_movie_field;
    Button m_search_movie_action;
    // end of custom vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start of custom code

        // Search button linkage and listener allocation
        m_search_movie_action = (Button) findViewById(R.id.button_go);
        m_search_movie_action.setOnClickListener(this);
        // End of custom code
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Reacts to m_search_movie_action.setOnClickListener(this);
    @Override
    public void onClick(View v) {
        // Let's just pop an animation for now..
        Toast.makeText(this, "Button test", Toast.LENGTH_LONG).show();
    }


    // Start of custom methods
}
