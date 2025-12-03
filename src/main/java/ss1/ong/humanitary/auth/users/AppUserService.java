package ss1.ong.humanitary.auth.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.auth.users.dto.request.UpdateUserDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

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
    private final PasswordEncoder passwordEncoder;

    public AppUser getUserByUsername(String username) throws NotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findUserByUsername(username);
        if(appUserOptional.isPresent()){
            return appUserOptional.get();
        }
        //TODO conectar el otro microservicio
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setBloodType(1);
        return appUserRepository.save(appUser);
    }

    public AppUser getProfile() throws NotFoundException {
        return this.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public AppUser updateUser(UpdateUserDTO updateUserDTO) throws NotFoundException {
        AppUser appUser = getProfile();
        appUserMapper.updateAppUserFromDto(updateUserDTO, appUser);
        return appUserRepository.save(appUser);
    }

    public AppUser getUserById(Integer id){
        //TODO conectar el otro microservicio
        AppUser appUser = null;
        return appUser;
    }

}
