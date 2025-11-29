package ss1.ong.humanitary.donationUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.common.exceptions.CustomException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.donationUtil.dto.request.CreateDonationUtilDTO;
import ss1.ong.humanitary.donationUtil.dto.response.DonationUtilDTO;
import ss1.ong.humanitary.donationUtil.enums.DonationUtilTypeEnum;

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
public class DonationUtilService {

    private final DonationUtilMapper donationUtilMapper;
    private final DonationUtilRepository donationUtilRepository;

    public DonationUtil create(CreateDonationUtilDTO createDonationUtilDTO){
        DonationUtil donationUtil = donationUtilMapper.createDonationUtilDtoToDonationUtil(createDonationUtilDTO);
        return donationUtilRepository.save(donationUtil);
    }

    public List<DonationUtilDTO> getAll(DonationUtilTypeEnum type){
        List<DonationUtil> donationUtils = donationUtilRepository.findByType(type);
        return this.donationUtilMapper.donationUtilToDonationUtilDto(donationUtils);
    }

    public DonationUtil findById(Integer id) throws NotFoundException {
        return this.donationUtilRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro algo para donacion"));
    }

    public void softDelete(Integer id) throws NotFoundException, CustomException {
        DonationUtil donationUtil = this.findById(id);
        if(donationUtil.getType() == DonationUtilTypeEnum.BLOOD)
            throw new CustomException("No se puede eliminar un tipo de sangre");
        donationUtilRepository.delete(donationUtil);
    }
}
