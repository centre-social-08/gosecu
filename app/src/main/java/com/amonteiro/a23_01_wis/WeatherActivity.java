package com.amonteiro.a23_01_wis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.beans.WeatherBean;
import com.amonteiro.a23_01_wis.databinding.ActivityWeatherBinding;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //clic du bouton
        binding.btLoad.setOnClickListener(v -> {

            //Création d'une tache asynchrone (execution en parallel.)
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //Requête
                        WeatherBean data =  RequestUtils.loadWeather("Nice");

                        //Affichage des données
                        //Il garantie son execution sur le Thread Graphique/ UIThread/ MainThread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView.setText("A " + data.getName() + " il fait " + data.getMain().getTemp() + "° avec un vent de " +
                                        data.getWind().getSpeed() + "km/h");
                            }
                        });
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        });
    }
}