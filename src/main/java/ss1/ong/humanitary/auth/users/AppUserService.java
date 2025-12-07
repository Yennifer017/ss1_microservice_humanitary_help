package ss1.ong.humanitary.auth.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.auth.users.dto.microservice.MicroserviceUserDTO;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.auth.users.enums.GenreEnum;
import ss1.ong.humanitary.common.config.web.RestClientConf;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserMapper appUserMapper;
    private final AppUserRepository appUserRepository;

    private final RestClientConf restClientConf;

    public AppUser getUserByUsername(String username) throws NotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findUserByUsername(username);
        if(appUserOptional.isPresent()){
            return appUserOptional.get();
        }
        Optional<MicroserviceUserDTO> optionalMicroserviceUserDTO = this.getUserFromRemote(username);
        if(optionalMicroserviceUserDTO.isEmpty())
            throw new NotFoundException("El usuario no esta registrado");
        AppUser appUser = this.appUserMapper.microserviceUserDtoToAppUser(optionalMicroserviceUserDTO.get());
        appUser.setId(null);
        appUser.setGenre(GenreEnum.O);
        appUser.setBloodType(1);
        return appUserRepository.save(appUser);
    }

    private Optional<MicroserviceUserDTO> getUserFromRemote(String username) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof Map<?,?> map) {
            String jwt = (String) map.get("jwt");
            MicroserviceUserDTO microserviceUserDTO = restClientConf.userRestClient().get()
                    .uri("/user/username/{username}", username)
                    .header("Authorization", "Bearer " + jwt)
                    .retrieve()
                    .body(MicroserviceUserDTO.class);
            return Optional.ofNullable(microserviceUserDTO);
        }
        return Optional.empty();
    }

    public AppUser getProfile() throws NotFoundException {
        return this.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public AppUser updateUser(UpdateUserDTO updateUserDTO) throws NotFoundException {
        AppUser appUser = getProfile();
        appUserMapper.updateAppUserFromDto(updateUserDTO, appUser);
        return appUserRepository.save(appUser);
    }

}
