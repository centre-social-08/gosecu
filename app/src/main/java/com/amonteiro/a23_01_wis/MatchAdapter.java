package com.amonteiro.a23_01_wis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amonteiro.a23_01_wis.beans.MatchBean;
import com.amonteiro.a23_01_wis.beans.WindBean;
import com.amonteiro.a23_01_wis.databinding.RowGameBinding;
import com.amonteiro.a23_01_wis.databinding.RowWindBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MatchAdapter extends FirestoreRecyclerAdapter<MatchBean, MatchAdapter.ViewHolder> {

    public MatchAdapter(FirestoreRecyclerOptions<MatchBean> options) {
        super(options);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RowGameBinding binding;
        public ViewHolder(RowGameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    //Création des lignes
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new ViewHolder(RowGameBinding.inflate(li));
    }

    //remplissage des lignes
    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MatchBean model) {
        holder.binding.tvT1.setText(model.getNameTeam1());
        holder.binding.tvT2.setText(model.getNameTeam2());
        //Je concatene avec un String sinon cela utilise setText qui prend un entier de style R.string.blabla
        holder.binding.tvScore1.setText("" + model.getScoreTeam1());
        holder.binding.tvScore2.setText("" + model.getScoreTeam2());

        holder.binding.ivAddT1.setOnClickListener(v -> {
            //Modification de la donnée
            model.setScoreTeam1(model.getScoreTeam1() +1);

            //Fait l'update en base
            MatchFirebaseRepo.update(model, getSnapshots().getSnapshot(position).getId());
        });

        holder.binding.ivAddT2.setOnClickListener(v -> {
            model.setScoreTeam2(model.getScoreTeam2() +1);
            MatchFirebaseRepo.update(model, getSnapshots().getSnapshot(position).getId());
        });

    }
}
