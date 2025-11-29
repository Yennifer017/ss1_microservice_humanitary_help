package ss1.ong.humanitary.requeriment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreateRequirementDTO {
    @NotNull(message = "Debe especificar un event")
    Integer eventId;

    @NotNull(message = "Debe especificarse una donacion")
    Integer DonationUtilId;
}
