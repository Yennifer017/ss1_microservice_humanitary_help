package ss1.ong.humanitary.multimedia;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.multimedia.dto.response.MultimediaDTO;

import java.io.IOException;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/multimedia")
@RequiredArgsConstructor
public class MultimediaController {

    private final MultimediaService multimediaService;
    private final MultimediaMapper multimediaMapper;

    /**
     * Crea un recurso multimedia
     * */
    @Operation(summary = "Crea un recurso multimedia",
            description = "Crea un recurso multimedia",
            responses = {
                @ApiResponse(responseCode = "200", description = "Exito"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
            })
    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public MultimediaDTO create(
            @RequestParam("eventId") Integer eventId,
            @RequestPart("file") MultipartFile file
    ) throws NotFoundException, IOException {
        return multimediaMapper.multimediaToMultimediaDto(multimediaService.save(file, eventId));
    }

    /**
     * Obtiene todos los recursos multimedia de un evento
     **/
    @Operation(summary = "example",
            description = "example",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{example}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void example(@PathVariable String example) throws NotFoundException {

    }

    /**
     * Obtiene todos los recursos multimedia de un evento
     **/
    @Operation(summary = "example",
            description = "example",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{example}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void example(@PathVariable String example) throws NotFoundException {

    }
}
