package com.amonteiro.a23_01_wis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amonteiro.a23_01_wis.beans.MatchBean;
import com.amonteiro.a23_01_wis.databinding.ActivityFirebaseExampleBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

public class FirebaseExampleActivity extends AppCompatActivity {

    ActivityFirebaseExampleBinding binding;

    //On redirige le callback de connexion sur la méthode onSignInResult
    ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(), this::onSignInResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseExampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Réglages
        binding.rv.setLayoutManager(new GridLayoutManager(this, 1));
        binding.tvConnexion.setOnClickListener(v -> {

            //Les types de connexion qu'on souhaite
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            Intent signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.mipmap.ic_launcher)
                    .build();
            signInLauncher.launch(signInIntent);
        });

        binding.tvDeconnexion.setOnClickListener(v -> {
            AuthUI.getInstance()
                    .signOut(this)
                    //callback
                    .addOnCompleteListener(task -> {
                        //La deconnexion a eu lieu j'actualise l'écran
                        refreshScreen();
                    });
        });

        binding.fab.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateGameActivity.class));
        });
    }

    //Le callback l'écran devient visible
    //Si jamais la connexion a évoluée pendant que l'écran étant en arrière plan
    @Override
    protected void onStart() {
        super.onStart();


        FirestoreRecyclerOptions<MatchBean> option = new FirestoreRecyclerOptions
                .Builder<MatchBean>()
                .setQuery(MatchFirebaseRepo.getAllMatch(), MatchBean.class)
                //Réglages dans le onStart car Firebase se deco quand l'écran n'est plus visible
                //Indiquer dans la doc de setLifecycleOwner
                .setLifecycleOwner(this)
                .build();
        binding.rv.setAdapter(new MatchAdapter(option));

        refreshScreen();
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() != RESULT_OK) {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            System.out.println("erreur : " + response.getError().getErrorCode());
            response.getError().printStackTrace();
        }

        refreshScreen();
    }

    private void refreshScreen() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            binding.tvConnexion.setVisibility(View.VISIBLE);
            binding.tvDeconnexion.setVisibility(View.GONE);
            binding.fab.setVisibility(View.GONE);
            binding.tvName.setText("---");
            binding.ivProfile.setVisibility(View.GONE);
        }
        else {
            //J'ai un utilisateur
            binding.tvConnexion.setVisibility(View.GONE);
            binding.tvDeconnexion.setVisibility(View.VISIBLE);
            binding.fab.setVisibility(View.VISIBLE);
            binding.tvName.setText(user.getDisplayName());
            binding.ivProfile.setVisibility(View.VISIBLE);
            Picasso.get().load(user.getPhotoUrl()).into( binding.ivProfile);
        }
    }
}