package ss1.ong.humanitary.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.event.dto.request.CreateEventDTO;
import ss1.ong.humanitary.event.dto.request.UpdateEventDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;

import java.util.List;
/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    /**
     * Crea un evento
     */
    @Operation(summary = "Crea un evento",
            description = "Crea un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public EventDTO create(@RequestBody @Valid CreateEventDTO createEventDTO) throws NotFoundException {
        return eventMapper.eventToEventDto(eventService.createEvent(createEventDTO));
    }

    /**
     * Actualiza un evento
     */
    @Operation(summary = "Actualiza un evento",
            description = "Actualiza un evento y notifica a los suscriptores de su actualizacion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public EventDTO update(@RequestBody @Valid UpdateEventDTO updateEventDTO) throws NotFoundException {
        return eventMapper.eventToEventDto(eventService.updateEvent(updateEventDTO));
    }

    /**
     * Obtener todos los eventos
     **/
    @Operation(summary = "Obtener todos los eventos",
            description = "Obtener todos los eventos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<EventDTO> getAll() throws NotFoundException {
        return eventService.getAllEvents();
    }

    /**
     * Obtener los eventos de una catastrofe
     **/
    @Operation(summary = "Obtener los eventos de una catastrofe",
            description = "Obtener los eventos de una catastrofe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{catastropheId}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDTO> getByCatastropheId(@PathVariable Integer catastropheId) throws NotFoundException {
        return eventService.getByCatastropheId(catastropheId);
    }
}
