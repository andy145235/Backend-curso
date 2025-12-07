package com.ucb.plataforma.cursos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//anotacion= que dice que trabajamos con sprint boot
@SpringBootApplication
public class CursosApplication {

     //PUNTO DE ARANQUE DE JAVA EL MAIN
	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
	}

}
