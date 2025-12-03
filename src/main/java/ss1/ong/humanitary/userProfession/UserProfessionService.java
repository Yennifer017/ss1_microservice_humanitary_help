package ss1.ong.humanitary.userProfession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.FilesService;
import ss1.ong.humanitary.profession.Profession;
import ss1.ong.humanitary.profession.ProfessionService;
import ss1.ong.humanitary.userProfession.dto.response.OwnedProfessionDTO;
import ss1.ong.humanitary.userProfession.dto.response.UserProfessionDTO;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserProfessionService {

    private final UserProfessionRepository userProfessionRepository;
    private final UserProfessionMapper userProfessionMapper;

    private final AppUserService appUserService;
    private final ProfessionService professionService;
    private final FilesService filesService;

    public UserProfession registerProfession(MultipartFile file, Integer professionId)
            throws NotFoundException, IOException {
        AppUser appUser = this.appUserService.getProfile();
        Profession profession = this.professionService.getById(professionId);
        String url = filesService.uploadFile(file, "profession_evidence");
        return this.userProfessionRepository.save(new UserProfession(appUser, profession, url));
    }

    public void deleteProfession(Integer professionId) throws NotFoundException {
        AppUser appUser = this.appUserService.getProfile();
        UserProfession userProfession = this.userProfessionRepository
                .findByAppUserIdAndProfessionId(appUser.getId(), professionId)
                .orElseThrow(() -> new NotFoundException("No se encontro una profesion de usuario"));
        this.userProfessionRepository.delete(userProfession);
    }

    public List<UserProfessionDTO> getByProfessionId(Integer professionId){
        List<UserProfession> userProfessions = this.userProfessionRepository.findByProfessionId(professionId);
        return this.userProfessionMapper.userProfessionToUserProfessionDTO(userProfessions);
    }

    public List<OwnedProfessionDTO> getOwned() throws NotFoundException {
        AppUser appUser = this.appUserService.getProfile();
        List<UserProfession> userProfessions = this.userProfessionRepository.findByAppUserId(appUser.getId());
        return this.userProfessionMapper.userProfessionToOwnedProfessionDTO(userProfessions);
    }

    public boolean isPsychoProfessional(Integer appUserId) {
        List<UserProfession> userProfessions = this.userProfessionRepository.findByAppUserId(appUserId);
        if(userProfessions.isEmpty()) return false;

        for (UserProfession userProfession : userProfessions) {
            if(userProfession.getProfession().getForPsycho() == true) return true;
        }
        return false;
    }

}
