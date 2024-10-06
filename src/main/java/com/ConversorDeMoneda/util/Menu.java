package com.ConversorDeMoneda.util;

import java.util.Scanner;

public class Menu {
    public static int displayMenu() {
        System.out.println("*************************************");
        System.out.println("Bienvenido al Convertidor de Monedas");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Dólar -> Peso Argentino");
        System.out.println("2. Peso Argentino -> Dólar");
        System.out.println("3. Dólar -> Real Brasileño");
        System.out.println("4. Real Brasileño -> Dólar");
        System.out.println("5. Dólar -> Peso Colombiano");
        System.out.println("6. Peso Colombiano -> Dólar");
        System.out.println("7. Salir");
        System.out.println("*************************************");
        System.out.print("Elige una opción valida: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static double getAmount() {
        System.out.println("*************************************");
        System.out.println("Ingresa el monto a convertir: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}
