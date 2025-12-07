package com.example.config_server; // Tu paquete puede variar un poco

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer; // Importar esto

@SpringBootApplication
@EnableConfigServer  // <--- ¡ESTO ES LO IMPORTANTE!
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}



