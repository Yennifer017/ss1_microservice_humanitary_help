package ss1.ong.humanitary.profession.dto.response;

import lombok.Value;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;

@Value
public class ProfessionDTO {
    Integer id;
    DonationUtilDTO donationUtil;
    Boolean forPsycho;
}
