package ss1.ong.humanitary.event.dto.response;

import lombok.Value;
import ss1.ong.humanitary.catastrophe.dto.response.CatastropheDTO;

import java.time.LocalDateTime;

@Value
public class EventDTO {
    String name;
    LocalDateTime limitDate;
    String details;
    String type;
    CatastropheDTO catastrophe;
}
