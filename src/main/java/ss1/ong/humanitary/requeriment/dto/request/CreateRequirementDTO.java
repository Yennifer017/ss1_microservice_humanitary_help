package ss1.ong.humanitary.requeriment.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class CreateRequirementDTO {
    @NotNull(message = "Debe especificar un event")
    Integer eventId;

    @NotNull(message = "Debe especificarse una donacion")
    Integer DonationUtilId;

    @NotNull(message = "Debe especificarse una cantidad")
    @Positive(message = "La cantidad debe ser positiva")
    Integer quantity;
}
