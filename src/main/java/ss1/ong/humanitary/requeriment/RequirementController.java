package ss1.ong.humanitary.requeriment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.requeriment.dto.request.CreateRequirementDTO;
import ss1.ong.humanitary.requeriment.dto.response.RequirementDTO;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/requirement")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementMapper requirementMapper;
    private final RequirementService requirementService;

    /**
     * Crea un requerimiento
     */
    @Operation(summary = "Crea un requerimiento",
            description = "Crea un requerimiento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public RequirementDTO createRequirement(@RequestBody @Valid CreateRequirementDTO createRequirementDTO)
            throws NotFoundException {
        return requirementMapper.requirementToRequirementDto(requirementService.create(createRequirementDTO));
    }

    /**
     * Obtiene los requerimientos de un evento
     **/
    @Operation(summary = "Obtiene los requerimientos de un evento",
            description = "Obtiene los requerimientos de un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RequirementDTO> getFromEvent(@PathVariable Integer eventId) throws NotFoundException {
        return requirementService.getAllFromEvent(eventId);
    }

    /**
     * Elimina un requerimiento para un evento
     **/
    @Operation(summary = "Elimina un requerimiento para un evento",
            description = "Elimina un requerimiento para un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) throws NotFoundException {
        requirementService.delete(id);
    }
}
