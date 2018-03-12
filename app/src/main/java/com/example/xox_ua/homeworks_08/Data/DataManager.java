package com.example.xox_ua.homeworks_08.Data;

import com.example.xox_ua.homeworks_08.Country;

import java.util.List;

public class DataManager {
    private RepositoryMockCountries mRepositoryMockCountries;

    public DataManager() {
        mRepositoryMockCountries = new RepositoryMockCountries();
    }

    public List<Country> fetchMocks() {
        return mRepositoryMockCountries.fetchMocks();
    }
}