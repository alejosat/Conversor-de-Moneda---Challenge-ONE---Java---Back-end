package com.ConversorDeMoneda.model;

import java.util.Map;

public record CurrencyRateRecord(String baseCode,
                                 Map<String, Double> conversion_rates) {
}
