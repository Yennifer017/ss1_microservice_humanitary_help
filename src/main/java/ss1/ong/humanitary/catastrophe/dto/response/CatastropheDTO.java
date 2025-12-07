package ss1.ong.humanitary.catastrophe.dto.response;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class CatastropheDTO {
    Integer id;
    String name;
    String details;
    LocalDateTime desactivatedAt;
}
