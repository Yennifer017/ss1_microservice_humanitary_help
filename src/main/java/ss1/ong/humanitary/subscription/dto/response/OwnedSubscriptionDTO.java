package ss1.ong.humanitary.subscription.dto.response;

import lombok.Value;
import ss1.ong.humanitary.event.dto.response.EventDTO;

@Value
public class OwnedSubscriptionDTO {
    Integer id;
    EventDTO event;
}
