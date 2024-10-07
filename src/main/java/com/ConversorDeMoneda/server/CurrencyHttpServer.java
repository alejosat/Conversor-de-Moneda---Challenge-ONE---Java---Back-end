package com.ConversorDeMoneda.server;

import com.ConversorDeMoneda.model.CurrencyRateRecord;
import com.ConversorDeMoneda.service.CurrencyService;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class CurrencyHttpServer {
    private static CurrencyService currencyService = new CurrencyService();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", exchange -> {
            String response = "<html><head><title>Currency Converter</title>" +
                    "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                    "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css\">"+
                    "</head><body>" + generateHtmlForm() + "</body></html>";
            byte[] responseBytes = response.getBytes();
            exchange.sendResponseHeaders(200, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        server.createContext("/convert", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder buf = new StringBuilder();
                int b;
                while ((b = br.read()) != -1) {
                    buf.append((char) b);
                }
                String formData = buf.toString();

                Map<String, String> params = parseFormData(formData);
                double amount = Double.parseDouble(params.get("amount"));
                String currencyFrom = params.get("currencyFrom");
                String currencyTo = params.get("currencyTo");

                CurrencyRateRecord rates = null;
                try {
                    rates = currencyService.getCurrencyRates();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                double conversionRate = rates.conversion_rates().get(currencyTo) / rates.conversion_rates().get(currencyFrom);
                double result = currencyService.convertCurrency(amount, conversionRate);

                String response = "<html><head><title>Resultado de Conversion</title>" +
                        "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                        "</head><body>" +
                        "<div class=\"container mt-5\">" +
                        "<div class=\"card\">" +
                        "<div class=\"card-header\">" +
                        "<i class=\"bi bi-currency-dollar\"></i>" +
                        "Resultado de la Conversion" +
                        "</div>" +
                        "<div class=\"card-body\">" +
                        "<h5 class=\"card-title\">Monto Convertido</h5>" +
                        "<p class=\"card-text\">" + amount + " " + currencyFrom + " equivale a " + result + " " + currencyTo + "</p>" +
                        "<a href=\"/\" class=\"btn btn-primary\">" +
                        "<i class=\"bi bi-skip-backward\"></i>" +
                        "Volver</a>" +
                        "</div></div></div></body></html>";
                byte[] responseBytes = response.getBytes();
                exchange.sendResponseHeaders(200, responseBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });

        server.start();
        System.out.println("Server is running on port 8000");
    }

    // Método para generar el formulario HTML dentro de una card
    private static String generateHtmlForm() {
        return "<div class=\"container mt-5\">" +
                "<div class=\"card\">" +
                "<div class=\"card-header\">" +
                "<i class=\"bi bi-currency-exchange\"></i>" +
                " Convertidor de Monedas</div>" +
                "<div class=\"card-body\">" +
                "<form action=\"/convert\" method=\"POST\">" +
                "<div class=\"input-group mb-3\">" +
                "<span class=\"input-group-text\" id=\"amount\">$</span>" +
                "<input type=\"number\" class=\"form-control\" placeholder=\"Monto\" id=\"amount\" name=\"amount\" required>" +
                "</div>" +
                "<div class=\"mb-3\">" +
                "<label for=\"currencyFrom\" class=\"form-label\">Desde</label>" +
                "<select class=\"form-select\" id=\"currencyFrom\" name=\"currencyFrom\">" +
                "<option value=\"USD\">Dolar (USD)</option>" +
                "<option value=\"ARS\">Peso Argentino (ARS)</option>" +
                "<option value=\"BRL\">Real Brasilero (BRL)</option>" +
                "<option value=\"COP\">Peso Colombiano (COP)</option>" +
                "</select>" +
                "</div>" +
                "<div class=\"mb-3\">" +
                "<label for=\"currencyTo\" class=\"form-label\">Hasta</label>" +
                "<select class=\"form-select\" id=\"currencyTo\" name=\"currencyTo\">" +
                "<option value=\"USD\">Dolar (USD)</option>" +
                "<option value=\"ARS\">Peso Argentino (ARS)</option>" +
                "<option value=\"BRL\">Real Brasilero (BRL)</option>" +
                "<option value=\"COP\">Peso Colombiano (COP)</option>" +
                "</select>" +
                "</div>" +
                "<button type=\"submit\" class=\"btn btn-primary\">" +
                "<i class=\"bi bi-caret-right\"></i>" +
                "Convertir</button>" +
                "</form></div></div></div>";
    }

    // Método para parsear los datos del formulario
    private static Map<String, String> parseFormData(String formData) {
        Map<String, String> params = new HashMap<>();
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                params.put(key, value);
            }
        }
        return params;
    }
}
