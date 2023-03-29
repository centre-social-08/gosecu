package com.amonteiro.a23_01_wis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            if(binding.rbLike.isChecked()) {
                binding.et.setText(binding.rbLike.getText());
            }
            else if(binding.rbDislike.isChecked()) {
                binding.et.setText(binding.rbDislike.getText());
            }
            binding.iv.setImageResource(R.drawable.baseline_delete_forever_24);

        });

        binding.btCancel.setOnClickListener(v -> {
            binding.et.setText("");
            binding.rg.clearCheck();
            binding.iv.setImageResource(R.drawable.baseline_flag_24);

        });
    }

    //Créeer le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"Météo");
        menu.add(0,5,0,"RecyclerView");

        return super.onCreateOptionsMenu(menu);
    }

    //Clic sur le menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 1) {
            startActivity(new Intent(this, WeatherActivity.class));
        }
        else if(item.getItemId() == 5) {
            startActivity(new Intent(this, WeatherAroundActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}