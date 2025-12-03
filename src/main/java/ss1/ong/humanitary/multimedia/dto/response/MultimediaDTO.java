package ss1.ong.humanitary.multimedia.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;

@Value
public class MultimediaDTO {
    Integer id;
    String url;
    SimpleUserDTO appUser;
}
