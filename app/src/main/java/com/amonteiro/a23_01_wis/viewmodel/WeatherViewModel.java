package com.amonteiro.a23_01_wis.viewmodel;

import android.content.Context;
import android.location.Location;

import com.amonteiro.a23_01_wis.utils.LocationUtils;
import com.amonteiro.a23_01_wis.RequestUtils;
import com.amonteiro.a23_01_wis.beans.WeatherBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeatherViewModel extends ViewModel {

    private MutableLiveData<WeatherBean> data = new MutableLiveData<>(null);
    private MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    private MutableLiveData<Boolean> runInProgress = new MutableLiveData<>(false);

    public void loadDataWithPosition(Context context){
        //reset de donnée
        data.postValue(null);
        errorMessage.postValue(null);
        runInProgress.postValue(true);

        new Thread(() -> {
            try {
                Location location = LocationUtils.getLastKnownLocation(context);
                if(location == null){
                    throw new Exception("Pas de localisation");
                }
                //Post value se déclanche sur l'UIThread
                data.postValue(RequestUtils.loadWeather(location.getLatitude(), location.getLongitude()));
            }
            catch(Exception e){
                e.printStackTrace();
                errorMessage.postValue(e.getMessage());
            }
            runInProgress.postValue(false);
        }).start();
    }


    public void loadData(String cityName){
        //reset de donnée
        data.postValue(null);
        errorMessage.postValue(null);
        runInProgress.postValue(true);

        new Thread(() -> {
            try {
                //Post value se déclanche sur l'UIThread
                data.postValue(RequestUtils.loadWeather(cityName));
            }
            catch(Exception e){
                e.printStackTrace();
                errorMessage.postValue(e.getMessage());
            }
            runInProgress.postValue(false);
        }).start();
    }

    public MutableLiveData<WeatherBean> getData() {
        return data;
    }

    public void setData(MutableLiveData<WeatherBean> data) {
        this.data = data;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(MutableLiveData<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MutableLiveData<Boolean> getRunInProgress() {
        return runInProgress;
    }

    public void setRunInProgress(MutableLiveData<Boolean> runInProgress) {
        this.runInProgress = runInProgress;
    }
}
