package com.somyco.myimdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by nicolasmas on 15/2/15.
 */



public class JSONAdapter extends BaseAdapter{


    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;


    public JSONAdapter(Context context, LayoutInflater inflater) {

        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();

    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        // check if the view already exists
        // if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.row_movies, null);

            // create a new "Holder" with subviews
            holder = new ViewHolder();
            holder.titleTextView = (TextView) convertView.findViewById(R.id.text_title);
            holder.yearTextView = (TextView) convertView.findViewById(R.id.text_Year);

            // hang onto this holder for future recyclage
            convertView.setTag(holder);
        } else {

            // skip all the expensive inflation/findViewById
            // and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        // More code after this
        JSONObject jsonObject = (JSONObject) getItem(position);

        // Grab the title and author from the JSON
        String movieTitle = "";
        String movieYear = "";

        if (jsonObject.has("Title")) {
            movieTitle = jsonObject.optString("Title");
        }

        if (jsonObject.has("Year")) {
            movieYear = jsonObject.optString("Year");
        }

        // Send these Strings to the TextViews for display
        holder.titleTextView.setText(movieTitle);
        holder.yearTextView.setText(movieYear);

        return convertView;

    }

    // Custom Methods
    // this is used so you only ever have to do
// inflation and finding by ID once ever per View
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView yearTextView;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
}
