package ss1.ong.humanitary.auth.users.dto.request;

import lombok.Value;

@Value
public class RecoveryPasswordDTO {
    String username;
    String code;
    String password;
}
