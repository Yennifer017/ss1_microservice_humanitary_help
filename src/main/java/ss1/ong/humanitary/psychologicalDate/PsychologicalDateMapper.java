package ss1.ong.humanitary.psychologicalDate;

import org.mapstruct.*;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.psychologicalDate.dto.request.CreatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.request.UpdatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.response.PsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.response.SimplePsychologicalDateDTO;
import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PsychologicalDateMapper {
    public PsychologicalDateDTO psychologicalDateToPsychologicalDateDto(PsychologicalDate psychologicalDate);
    public SimplePsychologicalDateDTO psychologicalDateToSimplePsychologicalDate(PsychologicalDate psychologicalDate);
    public List<SimplePsychologicalDateDTO> psychologicalDateToSimplePsychologicalDate(List<PsychologicalDate> psychologicalDates);
    public PsychologicalDate createPsychologicalDateDtoToPsychologicalDate(CreatePsychologicalDateDTO createPsychologicalDateDTO);
    /** update parcial
     *
     * @param dto
     * @param entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePsychologicalDateFromDto(UpdatePsychologicalDateDTO dto, @MappingTarget PsychologicalDate entity);
}
