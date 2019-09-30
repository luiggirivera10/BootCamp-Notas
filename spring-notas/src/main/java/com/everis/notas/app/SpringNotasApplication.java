package com.everis.notas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import com.everis.notas.app.config.SwaggerConfiguration;

/**
 * .
 * @author lriveras
 *
 */
@SpringBootApplication
@Import(SwaggerConfiguration.class)
@EnableDiscoveryClient
public class SpringNotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNotasApplication.class, args);
	}

}
