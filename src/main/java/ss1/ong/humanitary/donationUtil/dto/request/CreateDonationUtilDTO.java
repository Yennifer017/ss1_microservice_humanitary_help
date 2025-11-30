package ss1.ong.humanitary.donationUtil.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ss1.ong.humanitary.donationUtil.enums.DonationUtilTypeEnum;

@Data
public class CreateDonationUtilDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String description;

    private DonationUtilTypeEnum type;
}