package ss1.ong.humanitary.auth.users.dto.request;

import lombok.Value;
import ss1.ong.humanitary.auth.users.enums.GenreEnum;

@Value
public class UpdateUserDTO {

    String phoneNumber;
    Integer bloodType;
    GenreEnum genre;

}
