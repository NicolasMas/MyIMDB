package com.somyco.myimdb;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    // Start of custom vars
    public TextView m_movie_field;
    Button m_search_movie_action;
    JSONAdapter mJSONAdapter;
    ListView mainListView;
    ProgressDialog m_Dialog;
    // end of custom vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start of custom code

        m_Dialog = new ProgressDialog(this);
        m_Dialog.setMessage("Searching movies");
        m_Dialog.setCancelable(false);

        // Search button linkage and listener allocation
        m_search_movie_action = (Button) findViewById(R.id.button_go);
        m_search_movie_action.setOnClickListener(this);

        // ListView linkage
        mainListView = (ListView) findViewById(R.id.main_listview);
        mainListView.setOnItemClickListener(this);

        // movie to search linkage with m_movie_field
        m_movie_field = (TextView)findViewById(R.id.input_movie_title);

        mJSONAdapter = new JSONAdapter(this, getLayoutInflater());

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mJSONAdapter);


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
        //Toast.makeText(this, "Button test", Toast.LENGTH_LONG).show();
        getMovie(m_movie_field.getText().toString());
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Let's get the id of the movie for details request
        JSONObject jsonObject = (JSONObject) mJSONAdapter.getItem(position);
        Toast.makeText(this, "detail requested: " + jsonObject.toString(), Toast.LENGTH_LONG).show();

        // Retrieving the details for the specific movie
        Toast.makeText(this, "detail IMDBID: " + jsonObject.optString("imdbID".toString()), Toast.LENGTH_LONG).show();
        this.getMovieDetailIMDB(jsonObject.optString("imdbID"));
    }


    // Start of custom methods

    private void getMovie(String search_string)
    {
        String m_url = "";
        String m_string;
        try{
            m_string = URLEncoder.encode(search_string,"UTF-8");
            m_url = "http://www.omdbapi.com/?s="+m_string+"&y=&plot=short&r=json";


        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        AsyncHttpClient m_asynclient = new AsyncHttpClient();
        // Let's display the progress bar right after we created the http call
        m_Dialog.show();
        m_asynclient.get(m_url, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(JSONObject jsonObject) {

                        //Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                        //Log.d("myimdb", jsonObject.toString());

                        // update the data in your custom method.
                        m_Dialog.dismiss();
                        mJSONAdapter.updateData(jsonObject.optJSONArray("Search"));
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {

                        Toast.makeText(getApplicationContext(), "failed with!"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("myimdb", statusCode + " " + throwable.getMessage());
                    }
                }

        );


    }

    private void getMovieDetailIMDB(String imdbID)
    {
        String m_url = "";
        String m_string;
        try{
            m_string = URLEncoder.encode(imdbID,"UTF-8");
            m_url = "http://www.omdbapi.com/?i="+imdbID+"&y=&plot=short&r=json";


        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        AsyncHttpClient m_asynclient = new AsyncHttpClient();
        // Let's display the progress bar right after we created the http call
        m_Dialog.show();
        m_asynclient.get(m_url, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(JSONObject jsonObject) {

                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                        Log.d("myimdb", jsonObject.toString());

                        // update the data in your custom method.
                        m_Dialog.dismiss();
                        // TODO Add code for the detailed view JSONAdapter
                        //mJSONAdapter.updateData(jsonObject.optJSONArray("Search"));
                        Toast.makeText(getApplicationContext(),"details is"+jsonObject.toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {

                        Toast.makeText(getApplicationContext(), "failed with!"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("myimdb", statusCode + " " + throwable.getMessage());
                    }
                }

        );


    }
}
