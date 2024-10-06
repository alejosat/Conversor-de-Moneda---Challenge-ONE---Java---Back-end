package com.ConversorDeMoneda.model;

import java.util.Map;

public class CurrencyRate {
    private String baseCode;
    private Map<String, Double> conversionRates;

    public String getBaseCode() {
        return baseCode;
    }
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}
