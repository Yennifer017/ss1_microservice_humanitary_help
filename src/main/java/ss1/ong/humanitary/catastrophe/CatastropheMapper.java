package ss1.ong.humanitary.catastrophe;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.catastrophe.dto.request.CreateCatastropheDTO;
import ss1.ong.humanitary.catastrophe.dto.response.CatastropheDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CatastropheMapper {
    Catastrophe createCatastropheDtoToCatastrophe(CreateCatastropheDTO createCatastropheDTO);
    CatastropheDTO catastropheToCatastropheDto(Catastrophe catastrophe);
    List<CatastropheDTO> catastropheToCatastropheDto(List<Catastrophe> catastropheList);
}
