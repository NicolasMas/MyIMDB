package com.somyco.myimdb;

import android.widget.Adapter;

import org.json.JSONObject;

/**
 * Created by nicolasmas on 23/2/15.
 */
public class JSONAdapterDetailActivity {

    public JSONObject m_detail;

    public JSONAdapterDetailActivity(){

        m_detail = new JSONObject();
    }

    public void updateObject( JSONObject obj)
    {
        m_detail = obj;
    }
}
