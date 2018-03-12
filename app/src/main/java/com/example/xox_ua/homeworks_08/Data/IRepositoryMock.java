package com.example.xox_ua.homeworks_08.Data;

import java.util.List;

public interface IRepositoryMock<T> {
    List<T> fetchMocks();
}