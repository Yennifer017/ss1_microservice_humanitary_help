package ss1.ong.humanitary.donationUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.donationUtil.dto.request.CreateDonationUtilDTO;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;
import ss1.ong.humanitary.donationUtil.enums.DonationUtilTypeEnum;

import java.util.List;

/**
 * Controlador REST para la gestión de cosas para donacion
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/donationUtil")
@RequiredArgsConstructor
public class DonationUtilController {

    private DonationUtilService donationUtilService;
    private DonationUtilMapper donationUtilMapper;

    /**
     * Crea un objeto para una donacion
     */
    @Operation(summary = "Crea un objeto THING para una donacion",
            description = "Crea un objeto THING para una donacion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public DonationUtilDTO create(@RequestBody @Valid CreateDonationUtilDTO createDonationUtilDTO) {
        return this.donationUtilMapper.donationUtilToDonationUtilDto(
                donationUtilService.create(createDonationUtilDTO, true)
        );
    }

    /**
     * Obtiene todos los tipos de donativos segun su tipo
     **/
    @Operation(summary = "Obtiene todos los tipos de donativos segun su tipo",
            description = "Obtiene todos los tipos de donativos segun su tipo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<DonationUtilDTO> getAll(@PathVariable DonationUtilTypeEnum type) {
        return this.donationUtilService.getAll(type);
    }


    /**
     * Realiza un soft-delete de un objeto que puede ser donado
     **/
    @Operation(summary = "Realiza un soft-delete de un objeto que puede ser donado",
            description = "Realiza un soft-delete de un objeto que puede ser donado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void softDelete(@PathVariable Integer id) throws NotFoundException, CustomException {
        donationUtilService.softDelete(id);
    }
}
