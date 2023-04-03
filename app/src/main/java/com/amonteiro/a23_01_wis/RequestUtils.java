package com.amonteiro.a23_01_wis;

import com.amonteiro.a23_01_wis.beans.PokemonBean;
import com.amonteiro.a23_01_wis.beans.PokemonUnitBean;
import com.amonteiro.a23_01_wis.beans.PokemonUnitResultBean;
import com.amonteiro.a23_01_wis.beans.WeatherBean;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    public static List<PokemonUnitBean> loadPokemonUnit() throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://pokemon-unite-pokemons.p.rapidapi.com/pokemon?page=1&pageSize=10")
                .get()
                .addHeader("X-RapidAPI-Key", "93329c7cf9msha136bd696cd1040p10a1dejsnbc52cdb0746e")
                .addHeader("X-RapidAPI-Host", "pokemon-unite-pokemons.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        Gson gson = new Gson();
        PokemonUnitResultBean data =  gson.fromJson(response.body().string(), PokemonUnitResultBean.class);
        return data.getItems();
    }

    public static List<PokemonBean> loadRandomPokemon() throws Exception {
        String json = sendGet("https://www.amonteiro.fr/api/pokemonN4");

        Gson gson = new Gson();
        PokemonBean[] data =  gson.fromJson(json, PokemonBean[].class);
        return Arrays.asList(data);
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
