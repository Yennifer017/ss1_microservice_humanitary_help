package ss1.ong.humanitary.auth.users.dto.microservice;

import lombok.Value;

@Value
public class MicroserviceUserDTO {
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
