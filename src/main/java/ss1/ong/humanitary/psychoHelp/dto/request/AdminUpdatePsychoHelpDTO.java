package ss1.ong.humanitary.psychoHelp.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import ss1.ong.humanitary.psychoHelp.enums.PsychoHelpStatusEnum;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
public class AdminUpdatePsychoHelpDTO extends UpdatePsychoHelpDTO{
    String usernamePsycho;
}
