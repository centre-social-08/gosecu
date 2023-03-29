package com.amonteiro.a23_01_wis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amonteiro.a23_01_wis.beans.WindBean;
import com.amonteiro.a23_01_wis.databinding.ActivityMainBinding;
import com.amonteiro.a23_01_wis.databinding.ActivityWeatherAroundBinding;

import java.util.ArrayList;

public class WeatherAroundActivity extends AppCompatActivity {

    //IHM
    ActivityWeatherAroundBinding binding;

    //Données
    ArrayList<WindBean> data = new ArrayList<>();

    double number = 1.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Créer l'interface
        binding = ActivityWeatherAroundBinding.inflate(getLayoutInflater());
        //Affichage
        setContentView(binding.getRoot());

        binding.btAdd.setOnClickListener(v -> {
            //Je crée une fake donnée dans ma liste
            data.add(new WindBean(number++));

            //Affichage
            refreshScreen();
        });

        binding.btDelete.setOnClickListener(v -> {
            //Je crée une fake donnée dans ma liste
            if(!data.isEmpty()) {
                data.remove(0);
            }
            //Affichage
            refreshScreen();
        });

    }

    private void refreshScreen(){
        String aAfficher = "";
        for (WindBean datum : data) {
            aAfficher += "-" + datum.getSpeed() + "\n";
        }
        binding.tv.setText(aAfficher);
    }
}