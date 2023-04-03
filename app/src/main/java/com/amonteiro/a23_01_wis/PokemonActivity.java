package com.amonteiro.a23_01_wis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.beans.PokemonBean;
import com.amonteiro.a23_01_wis.beans.PokemonUnitBean;
import com.amonteiro.a23_01_wis.beans.WeatherBean;
import com.amonteiro.a23_01_wis.databinding.ActivityPokemonBinding;
import com.amonteiro.a23_01_wis.databinding.ActivityWeatherBinding;
import com.amonteiro.a23_01_wis.viewmodel.PokemonViewModel;

import java.util.List;

public class PokemonActivity extends AppCompatActivity {

    ActivityPokemonBinding binding;
    PokemonViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPokemonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PokemonViewModel.class);

        binding.btLoad.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.tvError.setVisibility(View.GONE);

            //Création d'une tache asynchrone (execution en parallel.)
            new Thread(() -> {

                model.loadData();
                runOnUiThread(() -> {
                    refreshScreen();
                    binding.progressBar.setVisibility(View.GONE);
                });
            }).start();
        });

        refreshScreen();
    }

    private void refreshScreen(){
        //1er donnée data
        if(model.getData() != null) {

            String texte = "";
            for (PokemonUnitBean datum : model.getData()) {
                texte += "-" + datum.getName() + " est de type " + datum.getDamageType() + "\n";
            }
            binding.tv.setText(texte);
        }
        else {
            binding.tv.setText("-");
        }

        //2me donnée erreur
        if(model.getErrorMessage() != null){
            binding.tvError.setText(model.getErrorMessage());
            binding.tvError.setVisibility(View.VISIBLE);
        }
        else {
            binding.tvError.setVisibility(View.GONE);
        }
    }
}