package com.example.xox_ua.homeworks_08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import butterknife.BindView;
import static com.example.xox_ua.homeworks_08.ListData.capitalNames;
import static com.example.xox_ua.homeworks_08.ListData.countryNames;
import static com.example.xox_ua.homeworks_08.ListData.flags;

public class ListActivity extends BaseActivity {
    @BindView(R.id.lv) ListView lv;
    @BindView(R.id.btnAdd) ImageView btnAdd;
    Country[] countryData;          // источник данных
    ArrayAdapter<Country> ad;       // адаптер
    String newD;
    String getD;

    //ArrayList<Country> data = new ArrayList<>();  // непонятная хуйня

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);

        // Строим источник данных
        // ListData.initCountries() - метод создания объекта с массивами в классе ListData
        countryData = ListData.initCountries();
        // Создаем адаптер для преобразования массива в представления (array to views)
        // 1: контекст, 2: идентификатор ресурса с разметкой для каждой строки, 3: массив данных
        ad = new CountryAdapter(this, R.layout.list_item, countryData);
        // устанавливаем адаптер для ListView
        lv.setAdapter(ad);



        // КНОПКА ДОБАВИТЬ - добавление новой строки (из полученных данных из AddActivity)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, 1975);
            }
        });

        // КОРОТКОЕ НАЖАТИЕ на строку в ListView (item)
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // определяем вьюхи в строке из которых надо взять данные
                ImageView imageView = (ImageView) view.findViewById(R.id.ivFlg);        // флаг
                TextView txtView1 = (TextView) view.findViewById(R.id.tvCountry);       // страна
                TextView txtView2 = (TextView) view.findViewById(R.id.tvCapital);       // столица
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);    // рейтинг

                // берём данные
                imageView.buildDrawingCache();                  // создаём кеш изображения imageView
                Bitmap getF = imageView.getDrawingCache();      // берём этот кэш
                String getCo = txtView1.getText().toString();   // берём текст из вьюхи страны
                String getCC = txtView2.getText().toString();   // берём текст из вьюхи столицы
                int getR = (int) ratingBar.getRating();         // берём цифру из вьюхи рейтинга

                if (newD == null) {
                    // если описание отсутствует показываем рыбу
                    getD = getResources().getString(R.string.lorem);
                } else if (getCo.contains("NEW!")){
                    // если это введённое пользователем описание (в AddActivity),
                    // то передаём описание из пришедшего интента
                    getD = newD;
                }
                // передаём данные с помощью бандла и интента
                Bundle extras = new Bundle();
                // окуда и куда передаём
                Intent intent = new Intent(ListActivity.this, DescriptionActivity.class);
                extras.putParcelable("getImage", getF);
                intent.putExtras(extras);
                intent.putExtra("getCountry", getCo);
                intent.putExtra("getCapital", getCC);
                intent.putExtra("getRating", getR);
                intent.putExtra("getDescr", getD);
                intent.putExtra("Notification", true);
                startActivity(intent);
            }
        });

        // ПРОДОЛЖИТЕЛЬНОЕ НАЖАТИЕ на строку в ListView (item) - удаление строки
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("LONG CLICK", "is clicked");
        // ОК - нажатие происходит
//!!!! ковырять

                Log.wtf("parent", String.valueOf(parent));
                Log.wtf("view", String.valueOf(view));
                Log.wtf("position", String.valueOf(position));
                Log.wtf("id", String.valueOf(id));


                // удаляем выбранную позицию
                //data.remove(position);
                // уведомляем, что данные изменились
                ad.notifyDataSetChanged();

                return true;
            }
        });
    }

    // получение интента из AddActivity и добавление новой строки в ListView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 1975:
                if (resultCode == RESULT_OK) {
                    // получаем интент из AddActivity
                    String newCo = intent.getStringExtra("AddCountry");
                    String newCi = intent.getStringExtra("AddCity");
                    int newR = intent.getIntExtra("AddRating", 0);
                    newD = intent.getStringExtra("AddDescr");
                    // задаём общую картинку для всех новодобавленных строк
                    int newF = R.drawable.zz_flg_eu;
        // ОК - интент приходит

                    // добавляем его в коллекцию
//!!!! ковырять
                    // всю мешпуху в один обект и в список с которым работает адаптер
                    // у меня ArrayAdapter
                    //data.add(new Country(newCo, newCi, newF, newR));

                    // создаём новый массив данных
                    ArrayList<List<Country>> data = new ArrayList<>();
                    // трансформируем наш массив данных в список
                    List<Country> qqq = new ArrayList<>(Arrays.asList(countryData));
                    qqq.add(new Country(String.valueOf(newCo), String.valueOf(newCi), newF, newR));
                    data.add(qqq);


                    //data.add(new ArrayList<Country>(newCo, newCi, newF, newR));       // не работает => cannot resolve constructor
                    //data.add(new ArrayAdapter<Country>(newCo, newCi, newF, newR));    // не работает => cannot resolve constructor

                    // уведомляем, что данные изменились
                    ad.notifyDataSetChanged();
                    // после добавления нового пункта проматываем ListView в самый конец
                    lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.toast3, Toast.LENGTH_SHORT).show();
                }
        }
    }
}
