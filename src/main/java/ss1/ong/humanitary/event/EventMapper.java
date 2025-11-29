package ss1.ong.humanitary.event;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.event.dto.request.CreateEventDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMapper {
    public Event createEventDtoToEvent(CreateEventDTO createEventDTO);

    public EventDTO eventToEventDto(Event event);
    public List<EventDTO> eventToEventDto(List<Event> events);
}
