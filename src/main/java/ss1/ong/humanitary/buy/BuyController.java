package ss1.ong.humanitary.buy;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.buy.dto.request.CreateBuyDTO;
import ss1.ong.humanitary.buy.dto.response.BuyDTO;
import ss1.ong.humanitary.buy.dto.response.MinBuyDTO;
import ss1.ong.humanitary.buy.dto.response.OwnedBuyDTO;
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
@RequestMapping("/api/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BuyMapper buyMapper;
    private final BuyService buyService;

    /**
     * Compra un insumo para una ayuda para una catastrofe
     */
    @Operation(summary = "Compra un insumo para una ayuda para una catastrofe",
            description = "Compra un insumo para una ayuda para una catastrofe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public MinBuyDTO buy(@RequestBody @Valid CreateBuyDTO createBuyDTO) throws NotFoundException {
        return buyMapper.buyToMinBuyDto(buyService.create(createBuyDTO));
    }

    /**
     * Obtener todas las compras de donaciones desde un helper
     **/
    @Operation(summary = "Obtener todas las compras de donaciones desde un helper",
            description = "Obtener todas las compras de donaciones desde un helper",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{helperId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<BuyDTO> getFromHelperId(@PathVariable Integer helperId){
        return buyService.getAllFromHelper(helperId);
    }

    /**
     * Obtener todas las compras de donaciones que un usuario posee
     **/
    @Operation(summary = "Obtener todas las compras de donaciones que un usuario posee",
            description = "Obtener todas las compras de donaciones que un usuario posee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/owned")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public List<OwnedBuyDTO> getOwned() throws NotFoundException {
        return buyService.getAllOwned();
    }
}
