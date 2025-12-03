package ss1.ong.humanitary.psychoHelp.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;

@Value
public class PsychoHelpDTO {
    SimpleUserDTO patient;
    String status;
    String notes;
    Integer progress;
    SimpleUserDTO currentPsycho;
}
