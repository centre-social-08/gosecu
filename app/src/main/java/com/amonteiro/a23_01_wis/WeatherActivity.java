package com.amonteiro.a23_01_wis;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.databinding.ActivityWeatherBinding;
import com.amonteiro.a23_01_wis.viewmodel.WeatherViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
            //Est ce que j'ai la permission ?
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                model.loadDataWithPosition(this);
            }
            else {
                //demande de permission avec une popup -> déclanche onRequestPermissionResult
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        });
    }

    //Callback de la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Est ce que j'ai la permission ?
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            model.loadDataWithPosition(this);
        }
        else {
            model.getErrorMessage().postValue("Il faut la permission");
        }
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