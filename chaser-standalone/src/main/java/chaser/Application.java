package chaser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Application.class);
		springApplication.setWebEnvironment(false);
		//TODO ChaserStandalone을 bean으로 만들어서 넣기
		//TODO 파일 경로를 인자로 받게 처리
		//TODO watcher bean으로 등록
		springApplication.run(args);
	}

}
