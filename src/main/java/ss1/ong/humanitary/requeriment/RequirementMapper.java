package ss1.ong.humanitary.requeriment;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.requeriment.dto.response.RequirementDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RequirementMapper {
    public RequirementDTO requirementToRequirementDto(Requirement requirement);
    public List<RequirementDTO> requirementToRequirementDto(List<Requirement> requirements);
}
