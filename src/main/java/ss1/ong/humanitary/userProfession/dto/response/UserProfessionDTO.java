package ss1.ong.humanitary.userProfession.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;

@Value
public class UserProfessionDTO {
    Integer id;
    SimpleUserDTO appUser;
    String degreeUrl;
}
