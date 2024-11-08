package com.challenge.sistema_notificacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableAsync
@OpenAPIDefinition(info = @Info(title = "Sistema de Notificações", version = "1", 
								description = "Desafio backend em Java e Spring Boot"))
@SpringBootApplication
public class SistemaNotificacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaNotificacoesApplication.class, args);
	}

}
