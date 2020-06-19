package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class VideowebApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideowebApplication.class, args);
	}

}
