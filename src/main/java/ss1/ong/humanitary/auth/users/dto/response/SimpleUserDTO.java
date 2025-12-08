package ss1.ong.humanitary.auth.users.dto.response;

import lombok.Value;

@Value
public class SimpleUserDTO {
    Integer id;
    String username;
    String name;
    String lastname;
}
