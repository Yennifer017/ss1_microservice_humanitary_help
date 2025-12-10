package ss1.ong.humanitary.psychologicalDate.dto.response;

import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;
import ss1.ong.humanitary.psychoHelp.dto.response.PsychoHelpDTO;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PsychologicalDateDTO {
    LocalDateTime init;
    LocalDateTime endDateTime;
    PsychoHelpDTO psychoHelp;
    SimpleUserDTO psycho;
    String status;
    String urlEvidence;
}
