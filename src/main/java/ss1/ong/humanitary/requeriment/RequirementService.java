package ss1.ong.humanitary.requeriment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.common.exceptions.DuplicateResourceException;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.donationUtil.DonationUtil;
import ss1.ong.humanitary.donationUtil.DonationUtilService;
import ss1.ong.humanitary.event.Event;
import ss1.ong.humanitary.event.EventService;
import ss1.ong.humanitary.requeriment.dto.request.CreateRequirementDTO;
import ss1.ong.humanitary.requeriment.dto.response.RequirementDTO;

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
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final RequirementMapper requirementMapper;

    private final EventService eventService;
    private final DonationUtilService donationUtilService;

    public Requirement create(CreateRequirementDTO createRequirementDTO) throws NotFoundException {
        if(isRequirement(createRequirementDTO))
            throw new DuplicateResourceException("No se puede registrar un requerimeinto duplicado");
        Requirement requirement = new Requirement();
        Event event = eventService.getEventById(createRequirementDTO.getEventId());
        DonationUtil donationUtil = donationUtilService.findById(createRequirementDTO.getDonationUtilId());
	requirement.setQuantity(createRequirementDTO.getQuantity());
        requirement.setEvent(event);
        requirement.setDonationUtil(donationUtil);
        return requirementRepository.save(requirement);
    }

    public Requirement getById(Integer id) throws NotFoundException {
        return requirementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro un requerimiento"));
    }

    public void delete(Integer id) {
        requirementRepository.deleteById(id);
    }

    public List<RequirementDTO> getAllFromEvent(Integer eventId){
        List<Requirement> requirements = requirementRepository.findByEventId(eventId);
        return requirementMapper.requirementToRequirementDto(requirements);
    }

    public boolean isRequirement(Integer eventId, Integer donationUtilId){
        return requirementRepository.existsByEventIdAndDonationUtilId(eventId, donationUtilId);
    }

    private boolean isRequirement(CreateRequirementDTO createRequirementDTO){
        return requirementRepository.existsByEventIdAndDonationUtilId(
                createRequirementDTO.getEventId(), createRequirementDTO.getDonationUtilId()
        );
    }
}
