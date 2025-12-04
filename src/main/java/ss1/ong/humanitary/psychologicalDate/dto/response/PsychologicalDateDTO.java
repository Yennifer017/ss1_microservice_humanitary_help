package ss1.ong.humanitary.psychologicalDate.dto.response;

import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;
import ss1.ong.humanitary.psychoHelp.dto.response.PsychoHelpDTO;

import java.time.LocalDateTime;

public class PsychologicalDateDTO {
    LocalDateTime init;
    LocalDateTime end;
    PsychoHelpDTO psychoHelp;
    SimpleUserDTO psycho;
    String status;
    String urlEvidence;
}
