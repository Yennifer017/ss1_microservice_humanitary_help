package ss1.ong.humanitary.donation.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;

@Value
public class DonationDTO {
    EventDTO event;
    SimpleUserDTO appUser;
    DonationUtilDTO donationUtil;
    Integer quantity;
    String notes;
    String urlEvidence;
}
