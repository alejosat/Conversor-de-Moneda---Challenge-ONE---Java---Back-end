package com.ConversorDeMoneda;

import com.ConversorDeMoneda.controller.CurrencyController;
import com.ConversorDeMoneda.server.CurrencyHttpServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // CurrencyController controller = new CurrencyController();
        // controller.start();
        CurrencyHttpServer.main(args);
    }
}
