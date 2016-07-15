package com.test.weatherdemo.locationsetting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.District;
import com.test.weatherdemo.utils.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by turbo on 2016/7/15.
 */
public class LocationAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<District> districts;
    private AdapterView.OnItemClickListener onItemClickListener;

    public LocationAdapter(Context context, List<District> districts, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.districts = districts;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(android.R.layout.test_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).textView.setText(districts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return districts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }
}
