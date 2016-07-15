package com.test.weatherdemo.main;

/**
 * Created by turbo on 2016/7/15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.weatherdemo.R;
import com.test.weatherdemo.beans.WeatherDataEntity;

/**
 * Created by turbo on 2016/7/15.
 */
public class WeatherAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<WeatherDataEntity> weatherDataEntityList;
    private AdapterView.OnItemClickListener onItemClickListener;

    public WeatherAdapter(Context context, List<WeatherDataEntity> weatherDataEntityList, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.weatherDataEntityList = weatherDataEntityList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather_today, parent, false));
        } else {
            return new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        WeatherDataEntity weatherDataEntity = weatherDataEntityList.get(position);
        ((WeatherViewHolder) holder).tvDate.setText(weatherDataEntity.getDate());
        ((WeatherViewHolder) holder).tvWeahter.setText(weatherDataEntity.getWeather());
        ((WeatherViewHolder) holder).tvTemperatur.setText(weatherDataEntity.getTemperature());
        Glide.with(context)
                .load(weatherDataEntity.getDayPictureUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((WeatherViewHolder) holder).ivDay);
        Glide.with(context)
                .load(weatherDataEntity.getNightPictureUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((WeatherViewHolder) holder).ivNight);
        ((WeatherViewHolder) holder).tvDate.setText(weatherDataEntity.getDate());
        ((WeatherViewHolder) holder).tvDate.setText(weatherDataEntity.getDate());
        if (holder instanceof TodayWeatherViewHolder) {
            ((TodayWeatherViewHolder) holder).tvWind.setText(weatherDataEntity.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return weatherDataEntityList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvDate;
        public TextView tvWeahter;
        public TextView tvTemperatur;
        public ImageView ivDay;
        public ImageView ivNight;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvWeahter = (TextView) itemView.findViewById(R.id.tv_weather);
            tvTemperatur = (TextView) itemView.findViewById(R.id.tv_temperature);
            ivDay = (ImageView) itemView.findViewById(R.id.iv_day);
            ivNight = (ImageView) itemView.findViewById(R.id.iv_night);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }

    class TodayWeatherViewHolder extends WeatherViewHolder {

        public TextView tvWind;

        public TodayWeatherViewHolder(View itemView) {
            super(itemView);
            tvWind = (TextView) itemView.findViewById(R.id.tv_wind);
        }
    }
}

