package ss1.ong.humanitary.psychoHelp;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.psychoHelp.dto.request.AdminUpdatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.CreatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.request.UpdatePsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.dto.response.PsychoHelpDTO;
import ss1.ong.humanitary.psychoHelp.enums.PsychoHelpStatusEnum;
import ss1.ong.humanitary.userProfession.UserProfessionService;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PsychoHelpService {

    private final PsychoHelpRepository psychoHelpRepository;
    private final PsychoHelpMapper psychoHelpMapper;

    private final AppUserService appUserService;
    private final UserProfessionService userProfessionService;

    public PsychoHelp create(CreatePsychoHelpDTO createPsychoHelpDTO) throws NotFoundException, CustomException {
        AppUser patient = appUserService.getUserByUsername(createPsychoHelpDTO.getUsernamePatient());
        AppUser currentPsycho = appUserService.getUserByUsername(createPsychoHelpDTO.getUsernamePsycho());
        if(!userProfessionService.isPsychoProfessional(currentPsycho.getId()))
            throw new CustomException("El usuario especificado no tiene un titulo como medico de salud mental");
        PsychoHelp psychoHelp = psychoHelpMapper.createPsychoHelpDtoToPsychoHelp(createPsychoHelpDTO);
        psychoHelp.setCurrentPsycho(currentPsycho);
        psychoHelp.setPatient(patient);
        return psychoHelpRepository.save(psychoHelp);
    }

    public PsychoHelp getById(Integer id) throws NotFoundException {
        return this.psychoHelpRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro un registro de ayuda psicologica especifico"));
    }

    public List<PsychoHelpDTO> getOwnedAsPatient() throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        List<PsychoHelp> psychoHelpList = this.psychoHelpRepository.findByPatientId(appUser.getId());
        return this.psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpList);
    }

    public List<PsychoHelpDTO> getOwnedAsPsycho() throws NotFoundException {
        AppUser appUser = appUserService.getProfile();
        List<PsychoHelp> psychoHelpList = this.psychoHelpRepository.findByCurrentPsychoId(appUser.getId());
        return this.psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpList);
    }

    public List<PsychoHelpDTO> findAllByStatus(PsychoHelpStatusEnum status){
        List<PsychoHelp> psychoHelpList = this.psychoHelpRepository.findByStatus(status);
        return  this.psychoHelpMapper.psychoHelpToPsychoHelpDto(psychoHelpList);
    }


    public PsychoHelp update(AdminUpdatePsychoHelpDTO adminUpdatePsychoHelpDTO)
            throws NotFoundException, CustomException {
        PsychoHelp psychoHelp = this.getById(adminUpdatePsychoHelpDTO.getId());
        this.psychoHelpMapper.updatePsychoHelpFromDto(adminUpdatePsychoHelpDTO, psychoHelp);
        if(adminUpdatePsychoHelpDTO.getUsernamePsycho() != null){
            AppUser currentPsycho = appUserService.getUserByUsername(adminUpdatePsychoHelpDTO.getUsernamePsycho());
            if(!userProfessionService.isPsychoProfessional(currentPsycho.getId()))
                throw new CustomException("El usuario especificado no tiene un titulo como medico de salud mental");
            psychoHelp.setCurrentPsycho(currentPsycho);
        }
        return this.psychoHelpRepository.save(psychoHelp);
    }

    public PsychoHelp update(UpdatePsychoHelpDTO updatePsychoHelpDTO) throws NotFoundException, CustomException {
        AppUser appUser = this.appUserService.getProfile();
        PsychoHelp psychoHelp = this.getById(updatePsychoHelpDTO.getId());
        if(psychoHelp.getStatus() == PsychoHelpStatusEnum.BANNED)
            throw new CustomException("No se puede modificar esta ficha porque esta baneada");
        if(!Objects.equals(psychoHelp.getCurrentPsycho().getId(), appUser.getId()))
            throw new BadCredentialsException("Las credenciales no concuerdan, no se puede actualizar");
        this.psychoHelpMapper.updatePsychoHelpFromDto(updatePsychoHelpDTO, psychoHelp);
        return this.psychoHelpRepository.save(psychoHelp);
    }

    public void ban(Integer psychoHelpId){
        this.psychoHelpRepository.deleteById(psychoHelpId);
    }

}
