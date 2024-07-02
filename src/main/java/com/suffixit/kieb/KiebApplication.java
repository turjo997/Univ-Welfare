package com.suffixit.kieb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KiebApplication {


	private final Logger logger = LoggerFactory.getLogger(KiebApplication.class.getName());
	private final Environment environment;

	public KiebApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(KiebApplication.class, args);

		//System.out.println("Hello world");
	}

	@Bean
	public void generateDirectory() {
		File fileDirectoryDir = new File(Objects.requireNonNull(environment.getProperty("fileStore.directory")));
		if (Files.notExists(fileDirectoryDir.toPath())) {
			try {
				Files.createDirectory(fileDirectoryDir.toPath());
			} catch (IOException ioException) {
				logger.error("Can't create directory on: " + fileDirectoryDir.getPath());
				logger.error("Caught: " + ioException);
			}
		}
	}



}
