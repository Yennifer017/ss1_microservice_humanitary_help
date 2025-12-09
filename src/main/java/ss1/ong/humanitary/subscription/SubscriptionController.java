package ss1.ong.humanitary.subscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.subscription.dto.response.EventSubscriptionDTO;
import ss1.ong.humanitary.subscription.dto.response.OwnedSubscriptionDTO;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * suscribirse
     */
    @Operation(summary = "Suscribirse",
            description = "Suscribir a un usuario a un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public OwnedSubscriptionDTO create(@PathVariable Integer eventId) throws NotFoundException {
        return this.subscriptionMapper.subscriptionToOwnedSubscriptionDto(subscriptionService.create(eventId));
    }

    /**
     * Obtener todos las suscripciones de un evento
     **/
    @Operation(summary = "Obtener todos las suscripciones de un evento",
            description = "Obtener todos las suscripciones de un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/byEvent/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<EventSubscriptionDTO> getByEventId(@PathVariable Integer eventId) throws NotFoundException {
        return this.subscriptionService.getByEventId(eventId);
    }

    /**
     * Obtener las suscripciones de un usuario especifico
     **/
    @Operation(summary = "Obtener las suscripciones de un usuario especifico",
            description = "Obtener las suscripciones de un usuario especifico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/owned")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public List<OwnedSubscriptionDTO> getOwned() throws NotFoundException {
        return this.subscriptionService.getOwned();
    }

    /**
     * Eliminar suscripcion
     **/
    @Operation(summary = "Eliminar suscripcion",
            description = "Eliminar suscripcion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('CLIENT', 'JOURNALIST')")
    public void deleteById(@PathVariable Integer eventId) throws NotFoundException {
        this.subscriptionService.deleteSubscription(eventId);
    }
}
