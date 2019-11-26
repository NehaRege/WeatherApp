package com.test.myapplication.screen;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.myapplication.R;
import com.test.myapplication.data.model.Data;
import com.test.myapplication.util.TimeUtils;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<Data> mDataList;

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

    public WeatherAdapter(List<Data> dataList) {
        mDataList = dataList;
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

        //TODO: Add images
//        Glide.with(holder.itemView)
//                .load(data.icon)
//                .centerCrop()
//                .into(holder.weatherIcon);

        //TODO: Format time and temperature

        if (data.icon != null) {
            setIcon(data.icon, holder.weatherIcon);
        }


        holder.temperature.setText(data.temperature + " \u2109");
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
            case "partly-cloudy-day":
                imageView.setImageResource(R.drawable.cloudy_day);
                break;
            case "partly-cloudy-night":
                imageView.setImageResource(R.drawable.cloudy_night);
                break;
            case "rain":
                imageView.setImageResource(R.drawable.rain);
                break;
            case "sleet":
            case "thunderstorm":
            case "cloudy":
            case "fog":
            case "wind":
            case "tornado":
                imageView.setImageResource(R.drawable.storm);
                break;
            case "snow":
            case "hail":
                imageView.setImageResource(R.drawable.snow);
                break;
            default:
                imageView.setImageResource(R.drawable.cloud);
        }
    }


}
