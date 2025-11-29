package ss1.ong.humanitary.event.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ss1.ong.humanitary.event.enums.EventTypeEnum;

import java.time.LocalDateTime;

@Value
public class CreateEventDTO {
    @NotBlank(message = "Se debe especificar el nombre del evento")
    String name;

    LocalDateTime limitDate;

    @NotBlank(message = "Se deben especificar los detalles")
    String details;

    @NotNull(message = "Se de especificar un tipo de evento")
    EventTypeEnum type;

    @NotNull(message = "Se debe especificar una catastrofe")
    Integer catastropheId;
}
