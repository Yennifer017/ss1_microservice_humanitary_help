package ss1.ong.humanitary.profession;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.profession.dto.request.CreateProfessionDTO;
import ss1.ong.humanitary.profession.dto.response.ProfessionDTO;
import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProfessionMapper {
    public Profession createProfessionDtoToProfession(CreateProfessionDTO createProfessionDTO);
    public ProfessionDTO professionToProfessionDto(Profession profession);
    public List<ProfessionDTO> professionToProfessionDto(List<Profession> professions);
}
