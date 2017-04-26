package com.example.android.coys;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by swlaforest on 4/24/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Result> {

    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Result> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Result story =  getItem(position);
        TextView dateView = (TextView) listItemView.findViewById(R.id.date_view);
        dateView.setText(story.getDateOnly());

        TextView timeView = (TextView) listItemView.findViewById(R.id.time_view);
        timeView.setText(story.getTimeOnly());

        TextView headlineView = (TextView) listItemView.findViewById(R.id.headline_view);
        headlineView.setText(story.getWebTitle());

        return listItemView;
    }
}
