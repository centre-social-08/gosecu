package com.amonteiro.a23_01_wis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amonteiro.a23_01_wis.beans.WindBean;
import com.amonteiro.a23_01_wis.databinding.RowWindBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class WindAdapter extends ListAdapter<WindBean, WindAdapter.ViewHolder> {

    public WindAdapter() {
        super(new Comparator());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RowWindBinding binding;
        public ViewHolder(RowWindBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class Comparator extends DiffUtil.ItemCallback<WindBean> {

        @Override
        public boolean areItemsTheSame(@NonNull WindBean oldItem, @NonNull WindBean newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull WindBean oldItem, @NonNull WindBean newItem) {
            return oldItem.getSpeed() == newItem.getSpeed();
        }
    }

    //Création des lignes
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new ViewHolder(RowWindBinding.inflate(li));
    }

    //remplissage des lignes
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       WindBean data =  getItem(position);
       holder.binding.tvSpeed.setText("" + data.getSpeed());
    }
}
