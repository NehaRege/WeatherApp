package com.test.myapplication.screen.detail;

import com.test.myapplication.data.model.Data;

import java.util.List;

public interface DetailView {

    void displayHourlyForecast(List<Data> dataList);

}
