package ss1.ong.humanitary.buy.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class CreateBuyDTO {
    @NotNull(message = "Debe especificarse un id de la ayuda")
    Integer helperId;

    @NotNull(message = "Debe especificarse una cantidad")
    @Positive(message = "La cantidad debe ser positiva")
    Integer quantity;
}
