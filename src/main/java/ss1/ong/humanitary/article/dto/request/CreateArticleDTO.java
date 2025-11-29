package ss1.ong.humanitary.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreateArticleDTO {

    @NotNull(message = "Se debe especificar un evento")
    Integer eventId;

    @NotBlank(message = "Se debe especificar un titulo")
    String title;

    @NotBlank(message = "Debe haber contenido")
    String content;

}
