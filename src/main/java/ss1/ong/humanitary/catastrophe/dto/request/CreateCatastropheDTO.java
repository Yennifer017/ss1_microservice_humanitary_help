package ss1.ong.humanitary.catastrophe.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateCatastropheDTO {
    @NotBlank(message = "Debe especificarse el nombre")
    String name;

    @NotBlank(message = "Debe especificarse detalles")
    String details;
}
