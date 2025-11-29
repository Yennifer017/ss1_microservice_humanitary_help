package ss1.ong.humanitary.helper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.helper.dto.request.CreateHelperDTO;
import ss1.ong.humanitary.helper.dto.response.HelperDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HelperMapper {
    public Helper CreateHelperDtoToHelper(CreateHelperDTO createHelperDTO);
    public HelperDTO helperToHelperDto(Helper helper);
    public List<HelperDTO> helperToHelperDto(List<Helper> helper);
}
