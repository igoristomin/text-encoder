package igoristomin.textencoder;

import igoristomin.textencoder.service.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextEncoderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextEncoderApplication.class, args);
		MainService.run();
	}

}
