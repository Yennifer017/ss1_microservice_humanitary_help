package ss1.ong.humanitary.catastrophe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.catastrophe.dto.request.CreateCatastropheDTO;
import ss1.ong.humanitary.catastrophe.dto.response.CatastropheDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

import java.time.LocalDateTime;
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
public class CatastropheService {

    private final CatastropheRepository catastropheRepository;
    private final CatastropheMapper catastropheMapper;

    public Catastrophe create(CreateCatastropheDTO createCatastropheDTO){
        Catastrophe catastrophe = catastropheMapper.createCatastropheDtoToCatastrophe(createCatastropheDTO);
        return catastropheRepository.save(catastrophe);
    }

    public List<CatastropheDTO> getAll(){
        List<Catastrophe> catastropheList = catastropheRepository.findAll();
        return this.catastropheMapper.catastropheToCatastropheDto(catastropheList);
    }

    public Catastrophe getById(Integer id) throws NotFoundException {
        return this.catastropheRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No se encontro una catastrofe"));
    }

    public void desactivate(Integer id) throws NotFoundException {
        Catastrophe catastrophe = this.getById(id);
        catastrophe.setDesactivatedAt(LocalDateTime.now());
        catastropheRepository.save(catastrophe);
    }



}
