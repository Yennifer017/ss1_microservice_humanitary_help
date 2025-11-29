package ss1.ong.humanitary.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.catastrophe.Catastrophe;
import ss1.ong.humanitary.catastrophe.CatastropheService;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.helper.dto.request.CreateHelperDTO;
import ss1.ong.humanitary.helper.dto.response.HelperDTO;

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
public class HelperService {

    private final HelperRepository helperRepository;
    private final HelperMapper helperMapper;

    private final CatastropheService catastropheService;

    public Helper create(CreateHelperDTO createHelperDTO) throws NotFoundException {
        Catastrophe catastrophe = catastropheService.getById(createHelperDTO.getCatastropheId());
        Helper helper = helperMapper.CreateHelperDtoToHelper(createHelperDTO);
        helper.setCatastrophe(catastrophe);
        return this.helperRepository.save(helper);
    }

    public List<HelperDTO> getAllFromCatastrophe(Integer catastropheId){
        List<Helper> helpers = helperRepository.findByCatastropheId(catastropheId);
        return helperMapper.helperToHelperDto(helpers);
    }

    public Helper getById(Integer id) throws NotFoundException {
        return this.helperRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro una ayuda"));
    }

}
