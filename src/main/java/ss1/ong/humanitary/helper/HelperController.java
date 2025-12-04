package ss1.ong.humanitary.helper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.helper.dto.request.CreateHelperDTO;
import ss1.ong.humanitary.helper.dto.response.HelperDTO;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/helper")
@RequiredArgsConstructor
public class HelperController {

    private final HelperService helperService;
    private final HelperMapper helperMapper;

    /**
     * Crear una ayuda para promocionar
     */
    @Operation(summary = "Crear una ayuda para promocionar",
            description = "Crear una ayuda para promocionar",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public HelperDTO create(@RequestBody @Valid CreateHelperDTO createHelperDTO) throws NotFoundException {
        return helperMapper.helperToHelperDto(helperService.create(createHelperDTO));
    }

    /**
     * Obtener todas las ayudas de una catastrofe
     **/
    @Operation(summary = "Obtener todos las ayudas de una catastrofe",
            description = "Obtener todos las ayudas de una catastrofe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{catastropheId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HelperDTO> getAllFromCatastrophe(@PathVariable Integer catastropheId) throws NotFoundException {
        return helperService.getAllFromCatastrophe(catastropheId);
    }
}
