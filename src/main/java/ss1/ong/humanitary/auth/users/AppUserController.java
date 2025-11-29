package ss1.ong.humanitary.auth.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.auth.users.dto.response.UserDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final AppUserMapper appUserMapper;

    /**
     * Enpoint para obtener el perfil extendido del usuario
     * */
    @Operation(summary = "Obtener el perfil extendido del usuario",
            description = "Obtener el perfil extendido del usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public UserDTO getProfile() throws NotFoundException {
        return appUserMapper.appUserToUserDto(appUserService.getProfile());
    }

    /**
     * Actualiza el perfil para un usuario extendido
     * */
    @Operation(summary = "Actualiza el perfil para un usuario extendido",
            description = "Actualiza el perfil para un usuario extendido",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated()")
    public UserDTO example(@RequestBody @Valid UpdateUserDTO updateUserDTO)
            throws NotFoundException {
        return appUserMapper.appUserToUserDto(appUserService.updateUser(updateUserDTO));
    }


}
