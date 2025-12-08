package ss1.ong.humanitary.catastrophe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.catastrophe.dto.request.CreateCatastropheDTO;
import ss1.ong.humanitary.catastrophe.dto.response.CatastropheDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/catastrophe")
@RequiredArgsConstructor
public class CatastropheController {

    private final CatastropheMapper catastropheMapper;
    private final CatastropheService catastropheService;

    /**
     * Crea una catastrofe
     */
    @Operation(summary = "Crea una catastrofe",
            description = "Crea una catastrofe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public CatastropheDTO create(@RequestBody @Valid CreateCatastropheDTO createCatastropheDTO) {
        return catastropheMapper.catastropheToCatastropheDto(catastropheService.create(createCatastropheDTO));
    }

    /**
     * Obtienen todas las catastrofes
     **/
    @Operation(summary = "Obtienen todas las catastrofes",
            description = "Obtienen todas las catastrofes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CatastropheDTO> getAll() throws NotFoundException {
        return catastropheService.getAll();
    }

    /**
     * Desactiva una catastrofe
     **/
    @Operation(summary = "Desactiva una catastrofe",
            description = "Desactiva una catastrofe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void desactivate(@PathVariable Integer id) throws NotFoundException {
        catastropheService.desactivate(id);
    }
}
