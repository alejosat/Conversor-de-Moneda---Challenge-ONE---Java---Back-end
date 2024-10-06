package com.ConversorDeMoneda.controller;

import com.ConversorDeMoneda.model.CurrencyRateRecord;
import com.ConversorDeMoneda.service.CurrencyService;
import com.ConversorDeMoneda.util.Menu;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyController {
    public void start() {
        CurrencyService currencyService = new CurrencyService();
        try {
            CurrencyRateRecord rates = currencyService.getCurrencyRates();

            if (rates.conversion_rates() == null) {
                System.err.println("Las tasas de conversión no están disponibles.");
                return;
            }

            boolean exit = false;

            while (!exit) {
                int option = Menu.displayMenu();

                switch (option) {
                    case 1:
                        // Convertir de USD a ARS
                        double amount1 = Menu.getAmount();
                        double result1 = currencyService.convertCurrency(amount1, rates.conversion_rates().get("ARS"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result1 + " ARS");
                        break;
                    case 2:
                        // Convertir de ARS a USD
                        double amount2 = Menu.getAmount();
                        double result2 = currencyService.convertCurrency(amount2, 1 / rates.conversion_rates().get("ARS"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result2 + " USD");
                        break;
                    case 3:
                        // Convertir de USD a BRL
                        double amount3 = Menu.getAmount();
                        double result3 = currencyService.convertCurrency(amount3, rates.conversion_rates().get("BRL"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result3 + " BRL");
                        break;
                    case 4:
                        // Convertir de BRL a USD
                        double amount4 = Menu.getAmount();
                        double result4 = currencyService.convertCurrency(amount4, 1 / rates.conversion_rates().get("BRL"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result4 + " USD");
                        break;
                    case 5:
                        // Convertir de USD a COP
                        double amount5 = Menu.getAmount();
                        double result5 = currencyService.convertCurrency(amount5, rates.conversion_rates().get("COP"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result5 + " COP");
                        break;
                    case 6:
                        // Convertir de COP a USD
                        double amount6 = Menu.getAmount();
                        double result6 = currencyService.convertCurrency(amount6, 1 / rates.conversion_rates().get("COP"));
                        System.out.println("*************************************");
                        System.out.println("Resultado: " + result6 + " USD");
                        break;
                    case 7:
                        exit = true;
                        System.out.println("*************************************");
                        System.out.println("Gracias por usar el convertidor.");
                        break;
                    default:
                        System.out.println("*************************************");
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
