package ss1.ong.humanitary.psychologicalDate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreatePsychologicalDateDTO {
    @NotNull(message = "Debe especificarse un ")
    LocalDateTime init;
    LocalDateTime endDateTime;
    Integer psychoHelpId;
    String notes;
}
