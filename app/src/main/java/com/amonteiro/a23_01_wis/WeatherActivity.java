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

            binding.progressBar.setVisibility(View.VISIBLE);

            //Création d'une tache asynchrone (execution en parallel.)
            new Thread(() -> {

                try {
                    //Requête
                    WeatherBean data =  RequestUtils.loadWeather("Nice");

                    //Affichage des données
                    //Il garantie son execution sur le Thread Graphique/ UIThread/ MainThread
                    runOnUiThread(() -> {
                        binding.textView.setText("A " + data.getName() + " il fait " + data.getMain().getTemp() + "° avec un vent de " +
                                data.getWind().getSpeed() + "km/h");
                        binding.progressBar.setVisibility(View.GONE);
                    });
                }
                catch (Exception e) {
                    e.printStackTrace();
                    //Mettre à jour les composants graphique
                    runOnUiThread(() -> {
                        binding.textView.setText("Une erreur est survenue : " + e.getMessage());
                        binding.progressBar.setVisibility(View.GONE);
                    });

                }
            }).start();

        });
    }
}