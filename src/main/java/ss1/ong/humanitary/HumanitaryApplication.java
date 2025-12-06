package ss1.ong.humanitary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import ss1.ong.humanitary.common.config.env.EnvConfig;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class HumanitaryApplication {

	public static void main(String[] args) {
        new EnvConfig();
        SpringApplication.run(HumanitaryApplication.class, args);
	}

}
