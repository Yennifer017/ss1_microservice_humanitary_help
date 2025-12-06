package ss1.ong.humanitary.psychologicalDate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ss1.ong.humanitary.psychologicalDate.PsychologicalDate;
import ss1.ong.humanitary.psychologicalDate.enums.PsychoDateStatusEnum;

import java.time.LocalDateTime;

@Value
public class UpdatePsychologicalDateDTO {
    @NotNull(message = "Debe especificarse un id de una cita de ayuda psicologica")
    Integer id;
    LocalDateTime init;
    LocalDateTime endDateTime;
    PsychoDateStatusEnum status;
    String notes;
}
