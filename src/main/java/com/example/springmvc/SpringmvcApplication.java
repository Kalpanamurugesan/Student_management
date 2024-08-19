package com.example.springmvc;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringmvcApplication {
	public static void main(String[] argv) {
		SpringApplication springApplication = new SpringApplication(SpringmvcApplication.class);
     	//springApplication.setBannerMode(Banner.Mode.OFF);  remove logo
		springApplication.run(argv);

	}
}
