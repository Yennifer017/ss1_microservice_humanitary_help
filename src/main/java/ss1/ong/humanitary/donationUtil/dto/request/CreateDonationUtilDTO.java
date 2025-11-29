package ss1.ong.humanitary.donationUtil.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateDonationUtilDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    String name;

    @NotBlank(message = "La descripcion no puede estar vacia")
    String description;
}