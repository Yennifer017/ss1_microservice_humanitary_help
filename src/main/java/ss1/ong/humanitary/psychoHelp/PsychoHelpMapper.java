package ss1.ong.humanitary.psychoHelp;

import org.mapstruct.*;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.CreatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.UpdatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.response.PsychoHelpDTO;
import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PsychoHelpMapper {
    public PsychoHelp createPsychoHelpDtoToPsychoHelp(CreatePsychoHelpDTO createPsychoHelpDTO);
    public PsychoHelpDTO psychoHelpToPsychoHelpDto(PsychoHelp psychoHelp);
    public List<PsychoHelpDTO> psychoHelpToPsychoHelpDto(List<PsychoHelp> psychoHelps);

    /** update parcial
     *
     * @param dto
     * @param entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePsychoHelpFromDto(UpdatePsychoHelpDTO dto, @MappingTarget PsychoHelp entity);
}
