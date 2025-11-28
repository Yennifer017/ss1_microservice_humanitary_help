package ss1.ong.humanitary.auth.users.dto.response;

import lombok.Value;

@Value
public class UserDTO {
    String id;
    String email;
    String phoneNumber;
    String name;
    String lastname;
    String username;
    String status;
    String role;
    Boolean mfaActivated;
}
