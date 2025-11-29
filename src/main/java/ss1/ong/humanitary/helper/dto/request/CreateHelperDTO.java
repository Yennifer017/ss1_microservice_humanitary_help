package ss1.ong.humanitary.helper.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class CreateHelperDTO {
    @NotNull(message = "debe especificarse un id de una catastrofe")
    Integer catastropheId;

    @NotBlank(message = "Debe especificarse un nombre")
    String name;

    @NotBlank(message = "Debe especificarse una descripcion")
    String description;

    @NotNull(message = "Debe especificarse un precio")
    @Positive(message = "El precio debe ser positivo")
    Double price;
}
