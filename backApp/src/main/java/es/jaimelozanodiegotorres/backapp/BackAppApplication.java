package es.jaimelozanodiegotorres.backapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@Slf4j
public class BackAppApplication implements CommandLineRunner {

	@Value("${spring.profiles.active}")
	private String profile;
	@Value("${server.port}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(BackAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Aplicacion escuchando en puerto: {}, Perfil: {}", port, profile);
		if (profile.equals("dev")) {
			log.info("Swagger activo: https://localhost:3000/v1/swagger-ui/index.html");
		}
	}

}
