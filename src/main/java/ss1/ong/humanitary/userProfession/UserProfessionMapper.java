package ss1.ong.humanitary.userProfession;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.userProfession.dto.response.OwnedProfessionDTO;
import ss1.ong.humanitary.userProfession.dto.response.UserProfessionDTO;

import java.util.List;
@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserProfessionMapper {
    OwnedProfessionDTO userProfessionToOwnedProfessionDTO(UserProfession userProfession);
    List<OwnedProfessionDTO> userProfessionToOwnedProfessionDTO(List<UserProfession> userProfessions);

    UserProfessionDTO userProfessionToUserProfessionDTO(UserProfession userProfession);
    List<UserProfessionDTO> userProfessionToUserProfessionDTO(List<UserProfession> userProfessions);
}
