package com.amonteiro.a23_01_wis;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amonteiro.a23_01_wis.beans.MatchBean;
import com.amonteiro.a23_01_wis.databinding.ActivityCreateGameBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGameActivity extends AppCompatActivity {

    ActivityCreateGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvAdd.setOnClickListener(v -> {
            String text = binding.etTeam1.getText().toString().trim();
            String text2 = binding.etTeam2.getText().toString().trim();

            if (text.length() < 3) {
                binding.etTeam1.setError("Il faut au moins 3 caractères");
            }
            else if (text2.length() < 3) {
                binding.etTeam1.setError("Il faut au moins 3 caractères");
            }
            else if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                finish();
            }
            else {
                //Nouveau Match
                MatchBean match = new MatchBean(null, text, text2, null, 0, 0, new Date().getTime());
                MatchFirebaseRepo.create(match)
                        //Callback ca marche
                        .addOnSuccessListener(documentReference -> {
                            //Je tue l'écran courant cela retourne sur l'écran précédent
                            finish();
                        })
                        //Callback ca marche pas
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Fail : " + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        });
            }
        });
    }
}