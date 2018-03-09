package com.example.xox_ua.homeworks_08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

// обязательно abstract - чтобы нельзя было создавать объекты этого класса
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // вьюшка подключена через ButterKnife
        ButterKnife.bind(this);
    }
}
