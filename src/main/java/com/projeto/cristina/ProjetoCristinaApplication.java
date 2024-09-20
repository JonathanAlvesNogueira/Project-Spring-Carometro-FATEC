package com.projeto.cristina;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Carômetro", version = "1",
		description = "API desenvolvida para o trabalho de Laboratório de Engenharia de Software"))
public class ProjetoCristinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCristinaApplication.class, args);
	}

}
