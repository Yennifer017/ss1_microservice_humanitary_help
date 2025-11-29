package ss1.ong.humanitary.auth.users.dto.request;

import lombok.Value;

@Value
public class UpdateUserDTO {

    String address;
    String phoneNumber;
    Integer bloodType;

}
