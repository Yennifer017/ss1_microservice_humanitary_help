package ss1.ong.humanitary.requeriment.dto.response;

import lombok.Value;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;

@Value
public class RequirementDTO {
    Integer id;
    Integer quantity;
    DonationUtilDTO donationUtil;
}
