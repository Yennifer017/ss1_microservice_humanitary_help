package ss1.ong.humanitary.psychoHelp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.psychoHelp.dto.request.AdminUpdatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.CreatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.UpdatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.response.PsychoHelpDTO;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class PsychoHelpController {

    private PsychoHelpMapper psychoHelpMapper;
    private PsychoHelpService psychoHelpService;

    /**
     * Crear un servicio de ayuda psicologica
     */
    @Operation(summary = "Crear un servicio de ayuda psicologica",
            description = "Crear un servicio de ayuda psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public PsychoHelpDTO create(@RequestBody @Valid CreatePsychoHelpDTO createPsychoHelpDTO)
            throws NotFoundException, CustomException {
        return psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpService.create(createPsychoHelpDTO));
    }

    /**
     * Actualizar por el admin
     */
    @Operation(summary = "Actualizar por el admin",
            description = "Actualizar por el admin",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PatchMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public PsychoHelpDTO updateByAdmin(@RequestBody @Valid AdminUpdatePsychoHelpDTO adminUpdatePsychoHelpDTO)
            throws NotFoundException, CustomException {
        return this.psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpService.update(adminUpdatePsychoHelpDTO));
    }

    /**
     * Actualizar por el profesional de la salud mental
     */
    @Operation(summary = "Actualizar por el profesional de la salud mental",
            description = "Actualizar por el profesional de la salud mental",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PatchMapping("/byPsycho")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public PsychoHelpDTO updateByPsycho(@RequestBody @Valid UpdatePsychoHelpDTO updatePsychoHelpDTO)
            throws NotFoundException, CustomException {
        return this.psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpService.update(updatePsychoHelpDTO));
    }

    /**
     * Obtener los registros de ayuda psicologica propios
     **/
    @Operation(summary = "Obtener los registros de ayuda psicologica propios",
            description = "Obtener los registros de ayuda psicologica propios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/owned/patient")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'PATIENT')")
    public List<PsychoHelpDTO> getOwnedAsPatient() throws NotFoundException {
        return this.psychoHelpService.getOwnedAsPatient();
    }

    /**
     * Obtener los registros de ayuda psicologica que esta atendiendo
     **/
    @Operation(summary = "Obtener los registros de ayuda psicologica que esta atendiendo",
            description = "Obtener los registros de ayuda psicologica que esta atendiendo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/owned/psycho")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public List<PsychoHelpDTO> getOwnedAsPsycho() throws NotFoundException {
        return this.psychoHelpService.getOwnedAsPsycho();
    }

    /**
     * Banear una ficha de ayuda psicologica
     **/
    @Operation(summary = "Banear una ficha de ayuda psicologica",
            description = "Banear una ficha de ayuda psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{psychoHelpId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void ban(@PathVariable Integer psychoHelpId) throws NotFoundException {
        this.psychoHelpService.ban(psychoHelpId);
    }
}
