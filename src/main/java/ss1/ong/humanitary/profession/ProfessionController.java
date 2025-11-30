package ss1.ong.humanitary.profession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.profession.dto.request.CreateProfessionDTO;
import ss1.ong.humanitary.profession.dto.response.ProfessionDTO;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/profession")
@RequiredArgsConstructor
public class ProfessionController {

    private final ProfessionService professionService;
    private final ProfessionMapper professionMapper;

    /**
     * Crear una profession
     */
    @Operation(summary = "Crear una profession",
            description = "Crear una profession",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProfessionDTO create(@RequestBody @Valid CreateProfessionDTO createProfessionDTO) {
        return professionMapper.professionToProfessionDto(professionService.create(createProfessionDTO));
    }

    /**
     * Obtener todas las profesiones
     **/
    @Operation(summary = "Obtener todas las profesiones",
            description = "Obtener todas las profesiones",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessionDTO> getAll() {
        return professionService.getAll();
    }

    /**
     * Obtener segun si es profesional de la salud mental
     **/
    @Operation(summary = "Obtener segun si es profesional de la salud mental",
            description = "Obtener segun si es profesional de la salud mental",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{forPsycho}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessionDTO> getFromPsycho(@PathVariable Boolean forPsycho) {
        return professionService.getByForPsycho(forPsycho);
    }

    /**
     * Soft delete para una profesion
     **/
    @Operation(summary = "Soft delete para una profesion",
            description = "Soft delete para una profesion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void example(@PathVariable Integer id) throws NotFoundException, CustomException {
        professionService.softDelete(id);
    }
}
