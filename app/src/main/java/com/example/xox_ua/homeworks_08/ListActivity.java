package com.example.xox_ua.homeworks_08;

import android.content.Intent;
import android.graphics.Bitmap;
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
    ArrayAdapter<Country> ad;
    Country countryData[];
    ArrayList<List<Country>> data;
    List<Country> qqq;
    String newD;
    String getD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list);
        super.onCreate(savedInstanceState);

        countryData = ListData.initCountries();
        //countryData = Arrays.asList(ListData.initCountries()).toArray(new Country[0]);
        // создаем адаптер --- 1: context, 2: кастомный вид, 3: массив данных
        ad = new CountryAdapter(this, R.layout.list_item, countryData);
        // устанавливаем адаптер
        lv.setAdapter(ad);

        // КНОПКА ДОБАВИТЬ - добавление нового item (из массива)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // создаём новый массив данных
                data = new ArrayList<>();
                // трансформируем наш массив данных в список
                qqq = new ArrayList<Country>(Arrays.asList(countryData));
                Log.wtf("qqq", String.valueOf(qqq));
                // случайное название страны из нашего массива
                int idX = new Random().nextInt(countryNames.length);
                // название случайно выбранной страны
                String randomName = (countryNames[idX]);
                // столица выбранной страны
                String randomCapital = capitalNames[idX];
                // флаг соответствующий выбранной стране
                int randomFlg = flags[idX];
                //int randomRate = rate[idX]

                Log.wtf("randomName", String.valueOf(randomName));
                Log.wtf("randomCapital", String.valueOf(randomCapital));


                //qqq.add(new Country(String.valueOf(randomName), String.valueOf(randomCapital), randomFlg, randomRate));
                // добавляем его в коллекцию
                data.add(qqq);
                Log.wtf("DATA ======", String.valueOf(data));
                // уведомляем, что данные изменились
                ad.notifyDataSetChanged();
            }
        });


        // КОРОТКОЕ НАЖАТИЕ на строку в ListView (item)
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // берем данные из ячеек строки
                ImageView imageView = (ImageView) view.findViewById(R.id.ivFlg);
                imageView.buildDrawingCache();
                Bitmap getF = imageView.getDrawingCache();
                TextView txtView1 = (TextView) view.findViewById(R.id.tvCountry);
                String getCo = txtView1.getText().toString();
                TextView txtView2 = (TextView) view.findViewById(R.id.tvCapital);
                String getCC = txtView2.getText().toString();
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
                int getR = (int) ratingBar.getRating();

                if (newD == null) {
                    // если описание отсутствует показываем рыбу
                    getD = getResources().getString(R.string.lorem);
                } else if (getCo.contains("NEW!")){
                    // если это добавленная строка, то передаём описание из интента
                    getD = newD;
                }
                Bundle extras = new Bundle();
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

        // ДЛИННОЕ НАЖАТИЕ на строку в ListView (item)
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("LONG CLICK", "is clicked");
//                // удаляем выбранную позицию
//                dataAL.remove(position);
//                // уведомляем, что данные изменились
//                sAdapter.notifyDataSetChanged();

                return false;
            }
        });



        // прокрутка до конца списка после обновления ListView
        //lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case 1975:
                if (resultCode == RESULT_OK) {
                    int newF = R.drawable.zz_flg_eu;
                    // получаем из intent
                    String newCo = intent.getStringExtra("AddCountry");
                    String newCi = intent.getStringExtra("AddCity");
                    int newR = intent.getIntExtra("AddRating", 0);
                    newD = intent.getStringExtra("AddDescr");

                    //data.add(new ArrayList<Country>(newCo, newCi, newF, newR));
                    // создаем новый Map
//                    m = new HashMap<String, Object>();
//                    m.put(bIMAGE, R.drawable.zz_flg_eu);
//                    m.put(bTITLE, newCo + " - NEW!");
//                    m.put(bAUTHOR, newCi);
//                    m.put(bRATING, newR);
//                    m.put(bDESCR, newD);
//                    // добавляем его в коллекцию
//                    dataAL.add(m);
//                    // уведомляем, что данные изменились
//                    sAdapter.notifyDataSetChanged();
                    // после добавления нового пункта проматываем в самый конец ListView
                    lv.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                }else {
                    Toast.makeText(getApplicationContext(), R.string.toast3, Toast.LENGTH_SHORT).show();
                }

        }
    }
}
