package ss1.ong.humanitary.subscription.dto.response;

import lombok.Value;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;

@Value
public class EventSubscriptionDTO {
    Integer id;
    SimpleUserDTO appUser;
}
