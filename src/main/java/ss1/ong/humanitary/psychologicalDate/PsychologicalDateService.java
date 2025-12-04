package ss1.ong.humanitary.psychologicalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.FilesService;
import ss1.ong.humanitary.psychoHelp.PsychoHelp;
import ss1.ong.humanitary.psychoHelp.PsychoHelpService;
import ss1.ong.humanitary.psychologicalDate.dto.request.CreatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.request.UpdatePsychologicalDateDTO;
import ss1.ong.humanitary.psychologicalDate.dto.response.SimplePsychologicalDateDTO;

import java.io.IOException;
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
public class PsychologicalDateService {

    private final PsychologicalDateRepository psychologicalDateRepository;
    private final PsychologicalDateMapper psychologicalDateMapper;

    private final AppUserService appUserService;
    private final PsychoHelpService psychoHelpService;
    private final FilesService filesService;

    public PsychologicalDate createByPsycho(CreatePsychologicalDateDTO createPsychologicalDateDTO)
            throws NotFoundException {
        PsychoHelp psychoHelp = psychoHelpService.getById(createPsychologicalDateDTO.getPsychoHelpId());
        AppUser psycho = appUserService.getProfile();
        PsychologicalDate psychologicalDate = psychologicalDateMapper
                .createPsychologicalDateDtoToPsychologicalDate(createPsychologicalDateDTO);
        if(!Objects.equals(psychoHelp.getCurrentPsycho().getId(), psycho.getId()))
            throw new BadCredentialsException("El psicologo no tiene los permisos necesarios");
        psychologicalDate.setPsycho(psycho);
        psychologicalDate.setPsychoHelp(psychoHelp);
        return this.psychologicalDateRepository.save(psychologicalDate);
    }

    public PsychologicalDate getById(Integer id) throws NotFoundException {
        return this.psychologicalDateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro una cita de ayuda psicologica"));
    }

    public List<SimplePsychologicalDateDTO> getAllByPsychoHelpId(Integer id){
        List<PsychologicalDate> psychologicalDates = this.psychologicalDateRepository.findByPsychoHelpId(id);
        return this.psychologicalDateMapper.psychologicalDateToSimplePsychologicalDate(psychologicalDates);
    }

    public PsychologicalDate update(UpdatePsychologicalDateDTO updatePsychologicalDateDTO, MultipartFile file)
            throws NotFoundException, IOException {
        PsychologicalDate psychologicalDate = this.getById(updatePsychologicalDateDTO.getId());
        this.psychologicalDateMapper.updatePsychologicalDateFromDto(updatePsychologicalDateDTO, psychologicalDate);
        String urlEvidence = filesService.uploadFile(file, "psychologicalDatesEvidence");
        psychologicalDate.setUrlEvidence(urlEvidence);
        return this.psychologicalDateRepository.save(psychologicalDate);
    }

}
