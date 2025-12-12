package ss1.ong.humanitary.event.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ss1.ong.humanitary.event.enums.EventTypeEnum;

import java.time.LocalDateTime;

@Value
public class UpdateEventDTO {

    @NotNull(message = "Se debe especificar el id del evento")
    Integer id;

    String name;
    LocalDateTime limitDate;
    String details;
    EventTypeEnum type;
}
