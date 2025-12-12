package ss1.ong.humanitary.event;

import org.mapstruct.*;
import ss1.ong.humanitary.catastrophe.CatastropheMapper;
import ss1.ong.humanitary.event.dto.request.CreateEventDTO;
import ss1.ong.humanitary.event.dto.request.UpdateEventDTO;
import ss1.ong.humanitary.event.dto.response.EventDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CatastropheMapper.class
)
public interface EventMapper {
    public Event createEventDtoToEvent(CreateEventDTO createEventDTO);

    public EventDTO eventToEventDto(Event event);
    public List<EventDTO> eventToEventDto(List<Event> events);

    /** update parcial
     *
     * @param dto
     * @param entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePsychoHelpFromDto(UpdateEventDTO dto, @MappingTarget Event entity);
}
