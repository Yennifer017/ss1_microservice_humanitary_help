package ss1.ong.humanitary.event;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.catastrophe.Catastrophe;
import ss1.ong.humanitary.catastrophe.CatastropheService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.MailService;
import ss1.ong.humanitary.event.dto.request.CreateEventDTO;
import ss1.ong.humanitary.event.dto.request.UpdateEventDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;
import ss1.ong.humanitary.subscription.Subscription;
import ss1.ong.humanitary.subscription.SubscriptionRepository;

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
    private final SubscriptionRepository subscriptionRepository;
    private final MailService mailService;

    public Event createEvent(CreateEventDTO createEventDTO) throws NotFoundException {
        Catastrophe catastrophe = catastropheService.getById(createEventDTO.getCatastropheId());
        Event event = eventMapper.createEventDtoToEvent(createEventDTO);
        event.setCatastrophe(catastrophe);
        return eventRepository.save(event);
    }

    public Event updateEvent(UpdateEventDTO updateEventDTO) throws NotFoundException {
        Event event = this.getEventById(updateEventDTO.getId());
        String legacyName = event.getName();
        eventMapper.updatePsychoHelpFromDto(updateEventDTO, event);
        String newName = event.getName();
        this.notificateAboutEvent(updateEventDTO.getId(), legacyName, newName);
        return eventRepository.save(event);
    }

    @Async
    public void notificateAboutEvent(Integer eventId, String legacyName, String newName){
        List<Subscription> subscriptions = this.subscriptionRepository.findByEventId(eventId);
        if(subscriptions.isEmpty()) return;
        for (Subscription subscription : subscriptions) {
            mailService.sendNotificationEmail(
                    subscription.getAppUser().getEmail(),
                    "Se actualizo un evento",
                    "El evento " + legacyName + " se actualizó"
                    + (legacyName.equals(newName) ? "" : " ahora lo encontraras con el nombre " + newName)
                    + "\n\nTe enviamos esta notificación porque estás suscrito a este evento"
            );
        }
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
