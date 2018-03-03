package com.example.xox_ua.homeworks_06;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import static com.example.xox_ua.homeworks_06.ListData.capitalNames;
import static com.example.xox_ua.homeworks_06.ListData.countryNames;
import static com.example.xox_ua.homeworks_06.ListData.flags;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ImageView btnAdd;
    ImageView btnDel;
    ImageView btnSort;
    ImageView btnShake;
    ArrayAdapter<Country> ad;
    Country countryData[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // фиксируем экран (запрет поворота)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        lv = (ListView) findViewById(R.id.lv);
        btnAdd = (ImageView) findViewById(R.id.btnAdd);
        btnDel = (ImageView) findViewById(R.id.btnDel);
        btnSort = (ImageView) findViewById(R.id.btnSort);
        btnShake = (ImageView) findViewById(R.id.btnShake);

        countryData = ListData.initCountries();
        ad = new CountryAdapter(this, R.layout.list_item, countryData);
        lv.setAdapter(ad);

        // добавление нового item (из массива)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> data = new ArrayList<>();
                List<String> qqq = Arrays.asList(countryNames);
                // случайное название страны из нашего массива
                int idX = new Random().nextInt(countryNames.length);
                // название случайно выбранной страны
                String randomName = (countryNames[idX]);
                // столица выбранной страны
                String randomCapital = capitalNames[idX];
                // флаг соответствующий выбранной стране
                int randomFlg = flags[idX];
                Log.wtf("randomName", String.valueOf(randomName));
                Log.wtf("randomCapital", String.valueOf(randomCapital));
                Log.wtf("randomFlg", String.valueOf(randomFlg));

                data.add(randomName);
                // создаем новый Map
//                Map<String, Object> m = new HashMap<String, Object>();
//                m.put(Arrays.toString(countryNames), randomName + " - NEW!");
//                m.put(Arrays.toString(capitalNames), randomCapital);
//                m.put(Arrays.toString(flags), randomFlg);
                // добавляем его в коллекцию
                //ad.add(m);
                Log.wtf("mmmmm", String.valueOf(data));
                // уведомляем, что данные изменились
                ad.notifyDataSetChanged();
            }
        });

        // удаление случайного item
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("DELETE", "clicked");
            }
        });

        // сортировка
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("SORT", "clicked");
                //	работает только для android.R.layout.simple_list_item_1
                Comparator<String> c = new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareTo(rhs);
                        //return rhs.compareTo(lhs);
                    }
                };
            }
        });


        // перемешивание
        btnShake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("SHAKE", "clicked");
            }
        });



        // нажатие на строку в ListView (item)
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mess = "Выбрана позиция: " + position;
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
            }
        });

        // прокрутка до конца списка после обновления ListView
        lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }
}
