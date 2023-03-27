package com.amonteiro.a23_01_wis;

import com.amonteiro.a23_01_wis.beans.WeatherBean;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
public class RequestUtils {
    public static void main(String[] args) {
        try {
            //System.out.println(sendGet("https://www.google.fr"));
            System.out.println(sendGet("https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fin");
    }

    public static WeatherBean loadWeather(String cityName) throws Exception {
        String json = sendGet("https://api.openweathermap.org/data/2.5/weather?appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q=" + cityName);

        Gson gson = new Gson();
        WeatherBean data =  gson.fromJson(json, WeatherBean.class);
        return data;
    }

    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
