package com.amonteiro.a23_01_wis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Composants graphique
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Créer l'interface
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //Affichage
        setContentView(binding.getRoot());

        binding.btValidate.setOnClickListener(v -> {
            binding.et.setText("Clic sur valider");
            System.out.println("Clic sur valdier");
        });
    }
}