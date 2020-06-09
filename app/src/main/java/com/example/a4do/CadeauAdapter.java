package com.example.a4do;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class CadeauAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Cadeau> mDataSource;

    public CadeauAdapter(Context context, ArrayList<Cadeau> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //getItem() returns an item to be placed in a given position from the data source, specifically, Recipe objects obtained from mDataSource.
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //This implements the getItemId() method that defines a unique ID for each row in the list. For simplicity, you just use the position of the item as its ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Finally, getView() creates a view to be used as a row in the list. Here you define what information shows
    // and where it sits within the ListView. You also inflate a custom view from the XML layout
    // defined in res/layout/list_item_recipe.xml -- more on this in the next section.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item

        Log.d("", "MyClass.getView() — get item number " + position);

        View rowView = mInflater.inflate(R.layout.list_item_cadeau, parent, false);
// Get title element
        TextView titleTextView =
                rowView.findViewById(R.id.cadeau_list_title);

// Get subtitle element
        TextView subtitleTextView =
                rowView.findViewById(R.id.cadeau_list_subtitle);

// Get detail element
        TextView detailTextView =
                rowView.findViewById(R.id.cadeau_list_detail);

// Get thumbnail element
        ImageView thumbnailImageView =
                rowView.findViewById(R.id.cadeau_list_thumbnail);


        // 1
        Cadeau cadeau = (Cadeau) getItem(position);

        //link color to hasmap
        //detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(cadeau.prixCadeau)));

       /* text.setTextColor(Color.parseColor("#FFFFFF"));
        You can also insert plain HEX, like so:

        myTextView.setTextColor(0xAARRGGBB);*/
// 2
        titleTextView.setText(cadeau.nomCadeau);
        subtitleTextView.setText(cadeau.descriptionCadeau);
        detailTextView.setText(cadeau.prixCadeau+ " €");
        Picasso.with(mContext).load(cadeau.imageCadeau).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);



// 3

       //Picasso.with(mContext).load(cadeau.imageCadeau).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);
        String imageUri = cadeau.imageCadeau;
       // Picasso.with(mContext).load(imageUri).into(thumbnailImageView);
        // Styling fonts

       /* Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        //  Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Embossed Germanica.ttf");
        titleTextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace =
                Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);*/
        return rowView;
    }
    //Implement a ViewHolder Pattern
    /* These repeated calls can seriously harm the ListView's performance,
    especially if your app is running on limited resources and/or you have a
    very large list. You can avoid this problem by using the View Holder Pattern.

     */

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }

// see https://www.raywenderlich.com/124438/android-listview-tutorial  pour un viewholder


}
