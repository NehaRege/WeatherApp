package com.test.myapplication.screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.myapplication.R;
import com.test.myapplication.data.model.Data;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<Data> mDataList;
    private static OnRecyclerViewItemClickListener onItemClickListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView hour;
        TextView temperature;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            hour = itemView.findViewById(R.id.hour);
            temperature = itemView.findViewById(R.id.temperature);

            itemView.setOnClickListener(view -> onItemClickListener.onItemClick(getLayoutPosition()));
        }
    }

    public WeatherAdapter(List<Data> dataList, OnRecyclerViewItemClickListener listener) {
        mDataList = dataList;
        onItemClickListener = listener;
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

        Glide.with(holder.itemView)
                .load(data.icon)
                .centerCrop()
                .into(holder.weatherIcon);

        //TODO: Format time and temperature
        holder.temperature.setText(data.temperature + "");
        holder.hour.setText(data.time + "");
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
