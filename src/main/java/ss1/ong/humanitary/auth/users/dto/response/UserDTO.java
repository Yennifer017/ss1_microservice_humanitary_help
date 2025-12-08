package ss1.ong.humanitary.auth.users.dto.response;

import lombok.Value;

@Value
public class UserDTO {
    Integer id;
    String username;
    String address;
    String phoneNumber;
    Integer bloodType;
    String name;
    String lastname;
    String genre;
    String email;
}
