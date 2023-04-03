package com.amonteiro.a23_01_wis.viewmodel;

import com.amonteiro.a23_01_wis.RequestUtils;
import com.amonteiro.a23_01_wis.beans.PokemonUnitBean;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class PokemonViewModel extends ViewModel {

    private List<PokemonUnitBean> data;
    private String errorMessage;

    public void loadData(){
        data = null;
        errorMessage = null;
        try {
            //Requête
            data =  RequestUtils.loadPokemonUnit();
        }
        catch (Exception e) {
            e.printStackTrace();
            errorMessage = "Une erreur est survenue : " + e.getMessage();
        }
    }



    public List<PokemonUnitBean> getData() {
        return data;
    }

    public void setData(List<PokemonUnitBean> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
