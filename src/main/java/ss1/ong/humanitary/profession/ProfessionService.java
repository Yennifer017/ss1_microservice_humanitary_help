package ss1.ong.humanitary.profession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.donationUtil.DonationUtil;
import ss1.ong.humanitary.donationUtil.DonationUtilService;
import ss1.ong.humanitary.donationUtil.enums.DonationUtilTypeEnum;
import ss1.ong.humanitary.profession.dto.request.CreateProfessionDTO;
import ss1.ong.humanitary.profession.dto.response.ProfessionDTO;

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
public class ProfessionService {

    private final ProfessionRepository professionRepository;
    private final ProfessionMapper professionMapper;

    private final DonationUtilService donationUtilService;

    public Profession create(CreateProfessionDTO createProfessionDTO){
        createProfessionDTO.getCreateDonationUtilDTO().setType(DonationUtilTypeEnum.PROFESSION);
        DonationUtil donationUtil = donationUtilService
                .create(createProfessionDTO.getCreateDonationUtilDTO(), false);
        Profession profession = new Profession(donationUtil, createProfessionDTO.getForPsycho());
        return professionRepository.save(profession);
    }

    public List<ProfessionDTO> getAll(){
        List<Profession> professions = professionRepository.findAll();
        return professionMapper.professionToProfessionDto(professions);
    }

    public List<ProfessionDTO> getByForPsycho(Boolean forPsycho){
        List<Profession> professions = professionRepository.findByForPsycho(forPsycho);
        return professionMapper.professionToProfessionDto(professions);
    }

    public Profession getById(Integer id) throws NotFoundException {
        return this.professionRepository.findById(id)
                .orElseThrow(() ->new NotFoundException("No se encontro una profession"));
    }

    public void softDelete(Integer id) throws NotFoundException, CustomException {
        Profession profession = this.getById(id);
        donationUtilService.softDelete(profession.getDonationUtil().getId());
        professionRepository.delete(profession);
    }


}
