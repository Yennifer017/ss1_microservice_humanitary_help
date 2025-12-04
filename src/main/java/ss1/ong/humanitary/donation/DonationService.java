package ss1.ong.humanitary.donation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.auth.users.AppUser;
import ss1.ong.humanitary.auth.users.AppUserService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.FilesService;
import ss1.ong.humanitary.donation.dto.request.CreateDonationDTO;
import ss1.ong.humanitary.donation.dto.response.DonationDTO;
import ss1.ong.humanitary.donationUtil.DonationUtil;
import ss1.ong.humanitary.donationUtil.DonationUtilService;
import ss1.ong.humanitary.event.Event;
import ss1.ong.humanitary.event.EventService;

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
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;

    private final EventService eventService;
    private final AppUserService appUserService;
    private final DonationUtilService donationUtilService;
    private final FilesService filesService;

    public Donation register(CreateDonationDTO createDonationDTO, MultipartFile file)
            throws NotFoundException, IOException {
        Event event = eventService.getEventById(createDonationDTO.getEventId());
        AppUser appUser = appUserService.getProfile();
        DonationUtil donationUtil = donationUtilService.findById(createDonationDTO.getDonationUtilId());
        String url = filesService.uploadFile(file, "donationsEvidence");
        Donation donation = donationMapper.createDonationDtoToDonation(createDonationDTO);
        donation.setEvent(event);
        donation.setAppUser(appUser);
        donation.setDonationUtil(donationUtil);
        donation.setUrlEvidence(url);
        return this.donationRepository.save(donation);
    }

    public List<DonationDTO> getOwned() throws NotFoundException {
        AppUser appUser = this.appUserService.getProfile();
        List<Donation> donations = this.donationRepository.findByAppUserId(appUser.getId());
        return this.donationMapper.donationToDonationDto(donations);
    }

    public List<DonationDTO> getByEventId(Integer id){
        List<Donation> donations = this.donationRepository.findByEventId(id);
        return this.donationMapper.donationToDonationDto(donations);
    }

}
