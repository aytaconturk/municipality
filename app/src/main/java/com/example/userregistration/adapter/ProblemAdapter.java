package com.example.userregistration.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.userregistration.Model;
import com.example.userregistration.R;

import java.util.ArrayList;

public class ProblemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> models;

    public ProblemAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = View.inflate(context, R.layout.list_item, null);
        }

        TextView title = convertView.findViewById(R.id.list_item_tv_title);
        TextView category = convertView.findViewById(R.id.list_item_category);
        TextView time = convertView.findViewById(R.id.list_item_time);
        TextView location = convertView.findViewById(R.id.list_item_location);
        TextView vote = convertView.findViewById(R.id.list_item_vote_number);

        Model model = models.get(position);

        title.setText(model.getTitle());
        category.setText(model.getCategory());
        time.setText(model.getTime());

        String address = model.getTown() + " - " + model.getDistrict();
        location.setText(address);
        String voteStr = String.valueOf(model.getVoteNumber());

        System.out.println("problem adaptor: " + voteStr);

        vote.setText(voteStr);

        return convertView;
    }
}
