package com.hotel.gestionhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionHotelApplication {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("SISTEMA DE GESTIÓN HOTELERA");
        System.out.println("========================================");
        System.out.println("Iniciando aplicación...");
        System.out.println();
        
        SpringApplication.run(GestionHotelApplication.class, args);
        
        System.out.println();
        System.out.println("========================================");
        System.out.println("Aplicación iniciada correctamente");
        System.out.println("========================================");
        System.out.println("URL de la aplicación:");
        System.out.println("   http://localhost:8081");
        System.out.println("========================================");
    }
}
