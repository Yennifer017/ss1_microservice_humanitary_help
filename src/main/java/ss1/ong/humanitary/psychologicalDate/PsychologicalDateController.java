package ss1.ong.humanitary.psychologicalDate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.psychologicalDate.dto.request.CreatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.request.UpdatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.response.PsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.response.SimplePsychologicalDateDTO;

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
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class PsychologicalDateController {

    private final PsychologicalDateService psychologicalDateService;
    private final PsychologicalDateMapper psychologicalDateMapper;

    /**
     * Crea una cita psicologica
     */
    @Operation(summary = "Crea una cita psicologica",
            description = "Crea una cita psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public SimplePsychologicalDateDTO create(@RequestBody @Valid CreatePsychologicalDateDTO createPsychologicalDateDTO)
            throws NotFoundException {
        return psychologicalDateMapper.psychologicalDateToSimplePsychologicalDate(
                this.psychologicalDateService.createByPsycho(createPsychologicalDateDTO)
        );
    }

    /**
     * actualizar una cita psicologica
     */
    @Operation(summary = "actualizar una cita psicologica",
            description = "actualizar una cita psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PutMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public SimplePsychologicalDateDTO update(
            @RequestPart("file") MultipartFile file,
            @RequestParam("text") UpdatePsychologicalDateDTO updatePsychologicalDateDTO
    ) throws NotFoundException, IOException {
        return psychologicalDateMapper.psychologicalDateToSimplePsychologicalDate(
             this.psychologicalDateService.update(updatePsychologicalDateDTO, file)
        );
    }

    /**
     * Obtiene el detalle de una cita psicologica
     **/
    @Operation(summary = "Obtiene el detalle de una cita psicologica",
            description = "Obtiene el detalle de una cita psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{psychoDateId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST', 'ADMIN')")
    public PsychologicalDateDTO getById(@PathVariable Integer psychoDateId) throws NotFoundException {
        return this.psychologicalDateMapper.psychologicalDateToPsychologicalDateDto(
                this.psychologicalDateService.getById(psychoDateId)
        );
    }

    /**
     * Obtener todas las citas psicologicas de un registro de ayuda psicologica
     **/
    @Operation(summary = "Obtener todas las citas psicologicas de un registro de ayuda psicologica",
            description = "Obtener todas las citas psicologicas de un registro de ayuda psicologica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/all/{psychoHelpId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<SimplePsychologicalDateDTO> all(@PathVariable Integer psychoHelpId) throws NotFoundException {
        return this.psychologicalDateService.getAllByPsychoHelpId(psychoHelpId);
    }
}
