package ss1.ong.humanitary.common.utils;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobHttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.config.FilesStorageConf;

import java.io.IOException;
import java.util.UUID;


@Service
public class FilesService {

    private final FilesStorageConf filesStorageConf;
    private final BlobServiceClient blobServiceClient;
    private final BlobContainerClient containerClient;

    public FilesService(FilesStorageConf filesStorageConf) {
        this.filesStorageConf = filesStorageConf;
        // Cliente principal de Azure Blob
        String connectionString =
                "DefaultEndpointsProtocol=https;" +
                        "AccountName=" + filesStorageConf.getAzureAccountName() + ";" +
                        "AccountKey=" + filesStorageConf.getAzureAccountKey() + ";" +
                        "EndpointSuffix=core.windows.net";

        this.blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        // Contenedor
        this.containerClient = blobServiceClient.getBlobContainerClient(
                filesStorageConf.getAzureContainer()
        );

        // Si no existe, lo crea
        if (!containerClient.exists()) {
            containerClient.create();
        }
    }

    /**
     * Sube un archivo al servidor de G3 y devuelve el path
     * */
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        // Obtener extensión
        String extension = "";
        String original = file.getOriginalFilename();
        if (original != null && original.contains(".")) {
            extension = original.substring(original.lastIndexOf("."));
        }

        // Generar nombre único con carpeta
        String blobName = folder + "/" + UUID.randomUUID() + extension;

        // Obtener referencia al blob
        BlobClient blobClient = containerClient.getBlobClient(blobName);

        // Subir archivo
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        // Configurar content type
        blobClient.setHttpHeaders(new BlobHttpHeaders()
                .setContentType(file.getContentType()));

        // Retornar URL pública
        return blobClient.getBlobUrl();
    }

}


