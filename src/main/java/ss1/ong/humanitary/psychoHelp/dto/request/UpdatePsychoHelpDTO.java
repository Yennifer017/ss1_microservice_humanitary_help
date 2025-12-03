package ss1.ong.humanitary.psychoHelp.dto.request;

import lombok.Data;
import lombok.Getter;
import ss1.ong.humanitary.psychoHelp.enums.PsychoHelpStatusEnum;

@Data
@Getter
public class UpdatePsychoHelpDTO {
    Integer id;
    Integer progress;
    String notes;
    PsychoHelpStatusEnum status;
}
