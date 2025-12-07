package ss1.ong.humanitary.common.config.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Getter
@Setter
@Configuration
public class RestClientConf {

    @Value("${app.dc.service}")
    private String BASE_URL;

    @Bean
    public RestClient userRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

}
