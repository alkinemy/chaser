package chaser.standalone.configuration;

import chaser.standalone.domain.ChaserDomains;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ChaserDomains.class)
public class ChaserStandaloneConfig {

	@Bean
	public Vertx vertx() {
		return Vertx.vertx();
	}

	@Bean
	public EventBus eventBus() {
		return vertx().eventBus();
	}

}
