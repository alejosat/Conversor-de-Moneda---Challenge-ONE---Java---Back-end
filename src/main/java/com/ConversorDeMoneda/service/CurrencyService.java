package com.ConversorDeMoneda.service;

import com.ConversorDeMoneda.model.CurrencyRateRecord;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyService {

    private static final String API_KEY = "a68808924fdd053b9c10cf74";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public CurrencyRateRecord getCurrencyRates() throws IOException, InterruptedException {
        URI apiUrl = URI.create(API_URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(apiUrl)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al conectar con la API: " + response.statusCode());
        }

        return new Gson().fromJson(response.body(), CurrencyRateRecord.class);
    }

    public double convertCurrency(double amount, double rate) {
        return amount * rate;
    }
}
