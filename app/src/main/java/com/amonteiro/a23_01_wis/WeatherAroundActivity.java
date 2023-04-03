package com.amonteiro.a23_01_wis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

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

    //outils
    WindAdapter adapter = new WindAdapter();

    double number = 1.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Créer l'interface
        binding = ActivityWeatherAroundBinding.inflate(getLayoutInflater());
        //Affichage
        setContentView(binding.getRoot());

        //Réglage RecyclerView
        binding.rv.setAdapter(adapter);
        //Combien de colonnes on veut.
        binding.rv.setLayoutManager(new GridLayoutManager(this, 2));

        binding.btAdd.setOnClickListener(v -> {
            //Je crée une fake donnée dans ma liste
            data.add(0,new WindBean(number++));

            //Affichage
            //Je retourne une référence differente à chaque fois pour
            //Que l'adapter actualise
            adapter.submitList(new ArrayList<>(data));
            refreshScreen();
        });

        binding.btDelete.setOnClickListener(v -> {
            //Je crée une fake donnée dans ma liste
            if(!data.isEmpty()) {
                data.remove(0);
            }
            //Affichage
            adapter.submitList(new ArrayList<>(data));
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