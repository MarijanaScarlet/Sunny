package com.android.mh.sunny;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.mh.sunny.database.Database;
import com.android.mh.sunny.database.dao.TimestampDao;
import com.android.mh.sunny.database.entitets.Timestamp;
import com.android.mh.sunny.weather.WeatherResponse;
import com.android.mh.sunny.weather.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "b75bc8f0fad37f7f959618798f42eedd";
    public static String name = "London";
    public static String id = "524901";
    private TextView weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i("Marijana ", "Weather Activity");

        long currentTimestamp = System.currentTimeMillis();
        Database database = Database.getInstance(getApplicationContext());
        Timestamp td = null;
        if(database.timestampDao().getAll() != null && database.timestampDao().getAll().size() !=0 )
             td = database.timestampDao().getAll().get(0);

        long dbTimestamp = 0;
        if(td != null)
             dbTimestamp = td.dateAndTime;

        if(dbTimestamp != 0 && currentTimestamp < dbTimestamp){
            Log.i("Marijana c -", Integer.toString((int)(dbTimestamp)));
            Log.i("Marijana d -", Integer.toString((int)(currentTimestamp)));
        }
        else{
            Log.i("Marijana else c -", Integer.toString((int)(dbTimestamp)));
            Log.i("Marijana else  d -", Integer.toString((int)(currentTimestamp)));
        }

    }

    void getCurrentData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(name, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;


                    Log.i("Marijana today", response.body().toString());
                    Log.i("Marijana today", call.request().toString());

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });


    }
}
