package ss1.ong.humanitary.donation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class CreateDonationDTO {
    @NotNull(message = "Se debe especificar un evento")
    Integer eventId;

    @NotNull(message = "Se debe especificar un donativo")
    Integer donationUtilId;

    @NotNull(message = "Se debe especificar una cantidad de insumos/otros")
    @Positive(message = "La cantidad debe ser positiva")
    Integer quantity;

    String notes;
}
