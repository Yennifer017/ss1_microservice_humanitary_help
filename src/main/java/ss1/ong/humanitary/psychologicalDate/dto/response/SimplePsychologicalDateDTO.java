package ss1.ong.humanitary.psychologicalDate.dto.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SimplePsychologicalDateDTO {
    LocalDateTime init;
    LocalDateTime endDateTime;
    String status;
    String urlEvidence;
}
