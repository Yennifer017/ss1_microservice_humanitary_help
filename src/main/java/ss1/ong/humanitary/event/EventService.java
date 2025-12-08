package ss1.ong.humanitary.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.catastrophe.Catastrophe;
import ss1.ong.humanitary.catastrophe.CatastropheService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.event.dto.request.CreateEventDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;

import java.util.List;
/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final CatastropheService catastropheService;

    public Event createEvent(CreateEventDTO createEventDTO) throws NotFoundException {
        Catastrophe catastrophe = catastropheService.getById(createEventDTO.getCatastropheId());
        Event event = eventMapper.createEventDtoToEvent(createEventDTO);
        event.setCatastrophe(catastrophe);
        return eventRepository.save(event);
    }

    public List<EventDTO> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        return eventMapper.eventToEventDto(events);
    }

    public Event getEventById(Integer eventId) throws NotFoundException {
        return this.eventRepository.findById(eventId)
                .orElseThrow(()-> new NotFoundException("No se encontro un evento"));
    }

    public List<EventDTO> getByCatastropheId(Integer id){
        List<Event> events = this.eventRepository.findByCatastropheId(id);
        return this.eventMapper.eventToEventDto(events);
    }

}
