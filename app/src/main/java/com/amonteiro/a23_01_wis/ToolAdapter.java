package com.amonteiro.a23_01_wis;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amonteiro.a23_01_wis.beans.ToolBean;
import com.amonteiro.a23_01_wis.databinding.RowGameBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToolAdapter extends FirestoreRecyclerAdapter<ToolBean, ToolAdapter.ViewHolder> {

    public ToolAdapter(FirestoreRecyclerOptions<ToolBean> options) {
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
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ToolBean model) {
        holder.binding.tvT1.setText(model.getName());
        //Je concatene avec un String sinon cela utilise setText qui prend un entier
        holder.binding.tvScore1.setText("" + model.getQuantity());

        holder.binding.ivAddT1.setOnClickListener(v -> {
            //Modification de la donnée
            model.setQuantity(model.getQuantity() +1);

            //Fait l'update en base
            ToolFirebaseRepo.update(model, getSnapshots().getSnapshot(position).getId());
        });

        holder.binding.ivRemoveT1.setOnClickListener(v -> {
            //Modification de la donnée
            if ( model.getQuantity() == 0 ) {
                model.setQuantity(0);
            } else {
                model.setQuantity(model.getQuantity() - 1);
            }
            //Fait l'update en base
            ToolFirebaseRepo.update(model, getSnapshots().getSnapshot(position).getId());
        });
    }
}
