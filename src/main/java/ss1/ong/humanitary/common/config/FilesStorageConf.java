package ss1.ong.humanitary.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuraciones para subir archivos de S3 de AWS
 * @author Yennifer de Leon
 * @since 2025-09-10
 * */
@Getter
@Setter
@Configuration
public class FilesStorageConf {

    @Value("${spring.cloud.azure.storage.blob.account-name}")
    private String azureAccountName;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String azureEndpoint;

    @Value("${spring.cloud.azure.storage.blob.account-key}")
    private String azureAccountKey;

    @Value("${app.storage.container}")
    private String azureContainer;

    @Value("${app.storage.region}")
    private String azureRegion;

}
