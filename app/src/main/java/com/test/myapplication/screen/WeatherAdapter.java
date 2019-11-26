package com.test.myapplication.screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.R;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.util.TimeUtils;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private static String TAG = "WeatherAdapter";

    private List<Data> mDataList;
    private Context mContext;

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView hour;
        TextView temperature;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            hour = itemView.findViewById(R.id.hour);
            temperature = itemView.findViewById(R.id.temperature);
        }
    }

    public WeatherAdapter(List<Data> dataList, Context context) {
        mDataList = dataList;
        mContext = context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItemLayout = inflater.inflate(R.layout.rv_item, parent, false);
        return new WeatherViewHolder(listItemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        Data data = mDataList.get(position);

        double currentTime = System.currentTimeMillis() / 1000;

        if (currentTime > data.time) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.darkBlue));
        } else {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.lightBlue));
        }

        setIcon(data.icon != null ? data.icon : "", holder.weatherIcon);
        holder.temperature.setText(mContext.getString(R.string.temperatureWithFahrenheit, String.valueOf(data.temperature)));
        holder.hour.setText(TimeUtils.timestampToHour(data.time));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    private void setIcon(String icon, ImageView imageView) {
        switch (icon) {
            case "clear-day":
                imageView.setImageResource(R.drawable.sun);
                break;
            case "clear-night":
                imageView.setImageResource(R.drawable.clear_night);
                break;
            case "rain":
                imageView.setImageResource(R.drawable.rain);
                break;
            case "snow":
            case "hail":
                imageView.setImageResource(R.drawable.snow);
                break;
            case "sleet":
            case "fog":
            case "wind":
            case "thunderstorm":
            case "tornado":
            case "cloudy":
                imageView.setImageResource(R.drawable.storm);
                break;
            case "partly-cloudy-day":
                imageView.setImageResource(R.drawable.cloudy_day);
                break;
            case "partly-cloudy-night":
                imageView.setImageResource(R.drawable.cloudy_night);
                break;
            default:
                imageView.setImageResource(R.drawable.cloud);
        }
    }


}
