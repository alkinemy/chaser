package chaser.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChaserStandalone {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ChaserStandalone.class);
		springApplication.run(args);
	}

}
