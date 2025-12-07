package ss1.ong.humanitary.auth.users;

import org.mapstruct.*;
import ss1.ong.humanitary.auth.users.dto.microservice.MicroserviceUserDTO;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.auth.users.dto.response.SimpleUserDTO;
import ss1.ong.humanitary.auth.users.dto.response.UserDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppUserMapper {
    public SimpleUserDTO appUserToSimpleUserDto(AppUser appUser);
    public List<SimpleUserDTO> appUserToSimpleUserDto(List<AppUser> appUsers);

    public UserDTO appUserToUserDto(AppUser appUser);
    public List<UserDTO> appUsersToUserDtos(List<AppUser> appUsers);

    public AppUser microserviceUserDtoToAppUser(MicroserviceUserDTO microserviceUserDTO);

    /** update parcial
     *
     * @param dto
     * @param entity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAppUserFromDto(UpdateUserDTO dto, @MappingTarget AppUser entity);

}
