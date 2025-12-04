package ss1.ong.humanitary.userProfession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.userProfession.dto.response.OwnedProfessionDTO;
import ss1.ong.humanitary.userProfession.dto.response.UserProfessionDTO;

import java.io.IOException;
import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/userProfession")
@RequiredArgsConstructor
public class UserProfessionController {

    private UserProfessionMapper userProfessionMapper;
    private UserProfessionService userProfessionService;

    /**
     * Registra una profesion de un usuario
     */
    @Operation(summary = "Registra una profesion de un usuario",
            description = "Registra una profesion de un usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping(path = "/{professionId}", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public UserProfessionDTO create(
            @PathVariable Integer professionId,
            @RequestPart("file") MultipartFile file
    ) throws NotFoundException, IOException {
        return this.userProfessionMapper
                .userProfessionToUserProfessionDTO(userProfessionService.registerProfession(file, professionId));
    }

    /**
     * Obtiene todas las profesiones de un usuario logueado
     **/
    @Operation(summary = "Obtiene todas las profesiones de un usuario logueado",
            description = "Obtiene todas las profesiones de un usuario logueado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CLIENT', 'JOURNALIST')")
    public List<OwnedProfessionDTO> getAllOwned() throws NotFoundException {
        return this.userProfessionService.getOwned();
    }

    /**
     * Obtiene a todos los usuarios de una profesion especificada
     **/
    @Operation(summary = "example",
            description = "example",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{professionId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfessionDTO> getAllFromProfession(@PathVariable Integer professionId) throws NotFoundException {
        return this.userProfessionService.getByProfessionId(professionId);
    }

    /**
     * Elimina el registro de una profession de un usuario logueado
     **/
    @Operation(summary = "Elimina el registro de una profession de un usuario logueado",
            description = "Elimina el registro de una profession de un usuario logueado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{professionId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public void delete(@PathVariable Integer professionId) throws NotFoundException {
        this.userProfessionService.deleteProfession(professionId);
    }

}
