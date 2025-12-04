package ss1.ong.humanitary.donation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.donation.dto.request.CreateDonationDTO;
import ss1.ong.humanitary.donation.dto.response.DonationDTO;

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
@RequestMapping("/api/hh/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;
    private final DonationMapper donationMapper;

    /**
     * Registrar una donacion
     */
    @Operation(summary = "Registrar una donacion",
            description = "Registrar una donacion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public DonationDTO register(
            @RequestPart("file") MultipartFile file,
            @RequestParam("text") CreateDonationDTO createDonationDTO
    ) throws NotFoundException, IOException {
        return donationMapper.donationToDonationDto(donationService.register(createDonationDTO, file));
    }

    /**
     * Obtener las donaciones hechas
     **/
    @Operation(summary = "Obtener las donaciones hechas",
            description = "Obtener las donaciones hechas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public List<DonationDTO> getOwned() throws NotFoundException {
        return this.donationService.getOwned();
    }

    /**
     * Obtener todas las donaciones de un evento en particular
     **/
    @Operation(summary = "Obtener todas las donaciones de un evento en particular",
            description = "Obtener todas las donaciones de un evento en particular",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<DonationDTO> getFromEventId(@PathVariable Integer eventId) throws NotFoundException {
        return this.donationService.getByEventId(eventId);
    }
}
