package ss1.ong.humanitary.profession.dto.request;

import lombok.Data;
import ss1.ong.humanitary.donationUtil.dto.request.CreateDonationUtilDTO;

@Data
public class CreateProfessionDTO {
    private CreateDonationUtilDTO createDonationUtilDTO;
    private Boolean forPsycho;
}
