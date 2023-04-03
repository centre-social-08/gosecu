package com.amonteiro.a23_01_wis;

import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.databinding.ActivityWeatherBinding;
import com.amonteiro.a23_01_wis.viewmodel.WeatherViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class WeatherActivity extends AppCompatActivity {

    //IHM
    ActivityWeatherBinding binding;
    //Donnée
    WeatherViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Créer l'ihm
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //créer le model
        model = new ViewModelProvider(this).get(WeatherViewModel.class);

        //Je connecte mes observateur aux données
        observe();

        //clic du bouton
        binding.btLoad.setOnClickListener(v -> {
            model.loadData("Nice");
        });
    }

    private void observe(){
        //Observation de la donnée sur le message d'erreur
        model.getErrorMessage().observe(this, newData->{
            //2me donnée erreur
            if (newData != null) {
                binding.tvError.setText(newData);
                binding.tvError.setVisibility(View.VISIBLE);
            }
            else {
                binding.tvError.setVisibility(View.GONE);
            }
        } );

        model.getData().observe(this, newData->{
            //1er donnée data
            if (newData != null) {
                binding.tv.setText("A " + newData.getName() + " il fait " + newData.getMain().getTemp() + "° avec un vent de " +
                        newData.getWind().getSpeed() + "km/h");
            }
            else {
                binding.tv.setText("-");
            }
        });

        model.getRunInProgress().observe(this, newData->{
            binding.progressBar.setVisibility(newData ? View.VISIBLE : View.GONE);
        });
    }
}