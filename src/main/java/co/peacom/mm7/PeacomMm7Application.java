package co.peacom.mm7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class PeacomMm7Application {

	public static void main(String[] args) {
		SpringApplication.run(PeacomMm7Application.class, args);
	}

}
