package ss1.ong.humanitary.userProfession.dto.response;

import lombok.Value;
import ss1.ong.humanitary.profession.dto.response.ProfessionDTO;

@Value
public class OwnedProfessionDTO {
    Integer id;
    ProfessionDTO profession;
    String degreeUrl;
}
