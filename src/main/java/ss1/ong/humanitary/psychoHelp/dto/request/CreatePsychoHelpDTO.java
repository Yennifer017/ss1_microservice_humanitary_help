package ss1.ong.humanitary.psychoHelp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreatePsychoHelpDTO {
    @NotBlank(message = "Debe especificarse un paciente")
    String usernamePatient;

    @NotBlank(message = "Debe especificarse un profesional de la salud mental")
    String usernamePsycho;

    String notes;

    Integer progress;

}
