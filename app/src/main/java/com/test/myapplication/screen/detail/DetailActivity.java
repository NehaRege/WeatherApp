package com.test.myapplication.screen.detail;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.test.myapplication.R;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }



}
