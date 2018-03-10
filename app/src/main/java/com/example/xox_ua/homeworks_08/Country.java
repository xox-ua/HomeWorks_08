package com.example.xox_ua.homeworks_08;

// определение модели адаптера
// описываем как представлять объект внутри ListView
// класс обязательно должен быть public, т.к. к нему нужен доступ из ListActivity
public class Country {
    String countryName;     // сохраняем название страны
    String capitalName;     // сохраняем название столицы
    int flagId;             // сохраняем id флага
    int ratingBar;          // сохраняем цифровое значение рейтинга

    // конструктор, используемый для создания экземпляра объекта Country
    public Country(String countryName, String capitalName, int flagId, int ratingBar) {
        this.countryName = countryName;
        this.capitalName = capitalName;
        this.flagId = flagId;
        this.ratingBar = ratingBar;
    }
}
