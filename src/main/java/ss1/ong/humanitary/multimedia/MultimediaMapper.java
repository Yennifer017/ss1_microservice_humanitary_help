package ss1.ong.humanitary.multimedia;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.multimedia.dto.response.MultimediaDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MultimediaMapper {
    public MultimediaDTO multimediaToMultimediaDto(Multimedia multimedia);
    public List<MultimediaDTO> multimediaToMultimediaDto(List<Multimedia> multimediaList);
}
