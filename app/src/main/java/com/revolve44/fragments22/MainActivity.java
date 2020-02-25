package com.revolve44.fragments22;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //api.openweathermap.org/data/2.5/weather?q=moscow&appid=1b87fee17221ed7893aa488cff08bfa2

    public static String BaseUrl = "https://api.openweathermap.org/";
    public static String CITY;
    public static String AppId = "1b87fee17221ed7893aa488cff08bfa2";
    public static String MC = "&units=metric&appid=";

    public static String lat = "80.75";
    public static String lon = "35.61";
    public static String metric = "metric";


    //Variables
    public float NominalPower = 100;//????????????????????????????????
    public float CurrentPower;
    public float cloud;
    float temp;



    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final float TEXT2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove action bar, if i add .hide() before setContentView() its immediately works! Wonders!!!
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //CurrentOutput = findViewById(R.id.current_output);
//        Power = findViewById(R.id.power);
//        City = findViewById(R.id.cIty);
//        CurrentTemperature = findViewById(R.id.current_temperature);
//
//        //Button
//        MainGet = findViewById(R.id.MainGet);
//        Save_data = findViewById(R.id.save_data);
//        ToSecondActivity = findViewById(R.id.to_second_activity);
//
//        //EditText
//        enterCity = findViewById(R.id.your_city);
//        enterNominal = findViewById(R.id.nominal_output);

        //ASD = findViewById(R.id.asd);

//        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
//        FontUtils fontUtils = new FontUtils();
//        fontUtils.applyFontToView(CurrentOutput, typeface);

//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getCurrentData();
//
//            }
//        });
        //loadData();
        //fromFragment();


        if (NominalPower>0){
            getCurrentData();
        }
    }

    void getCurrentData() {
        CITY = "Moscow";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        //Call<WeatherResponse> call = service.getCurrentWeatherData(lat, lon, metric, AppId);
        Call<WeatherResponse> call = service.getCurrentWeatherData(CITY, metric, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;

                    //String temperature = ""+weatherResponse.main.temp;
                    cloud = weatherResponse.clouds.all;
                    temp = weatherResponse.main.temp;
//                            "Country: " +
//                            weatherResponse.sys.country +
//                            "\n" +
//                            "Temperature: " +
//                            weatherResponse.main.temp +
//                            "\n" +
//                            "Temperature(Min): " +
//                            weatherResponse.main.temp_min +
//                            "\n" +
//                            "Temperature(Max): " +
//                            weatherResponse.main.temp_max +
//                            "\n" +
//                            "Humidity: " +
//                            weatherResponse.main.humidity +
//                            "\n" +
//                            "Pressure: " +
//                            weatherResponse.main.pressure;

//                    weatherData.setText(temperature);
//                    //check2.setText(""+ cloud);
//                    //weatherData.setText(rain);
//                    a = weatherResponse.main.temp_max;
//                    b = weatherResponse.main.temp_min;
//                    c = weatherResponse.main.temp;
//
//                    spec = weatherResponse.sys.country;
                    //Print();//поместил ниже после обявления spec  и теперь спец заново меняется и он теперь не null


                    if (cloud >-1 ){
                        CurrentPower = NominalPower - NominalPower*(cloud/100);
                    }else{
                        CurrentPower = 404;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Fail in Response"+t.getMessage();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!"+cloud+ "and"+temp;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }


    public Float getMyData() {
        return CurrentPower;
    }
    public String getMyData2() {
        return CITY;
    }
    public Float getMyData3() {
        return temp;
    }

    public void runforecast() {
        getCurrentData();



//        try {
//            //ASD.setText("777");
//            if (NominalPower==0){
//
////                CITY = enterCity.getText().toString(); //gets you the contents of edit text
////                //CurrentPower = NominalPower;
////                NominalPower = Float.parseFloat(enterNominal.getText().toString());}
//                CITY = "Osh";
//                NominalPower = 1000;
//                getCurrentData();}
//            else{getCurrentData();}
//        }catch (Exception e){
//            NominalPower = 777;
//        }
    }



}
