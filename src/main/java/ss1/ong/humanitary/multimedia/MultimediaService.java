package ss1.ong.humanitary.multimedia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.common.utils.FilesService;
import ss1.ong.humanitary.event.Event;
import ss1.ong.humanitary.event.EventService;
import ss1.ong.humanitary.multimedia.dto.response.MultimediaDTO;

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
public class MultimediaService {

    private final MultimediaMapper multimediaMapper;
    private final MultimediaRepository multimediaRepository;

    private final EventService eventService;
    private final FilesService filesService;

    public Multimedia save(MultipartFile file, Integer eventId) throws NotFoundException, IOException {
        Event event = eventService.getEventById(eventId);
        String url = filesService.uploadFile(file, "event_multimedia");
        Multimedia multimedia = new Multimedia(url, event);
        return multimediaRepository.save(multimedia);
    }

    public List<MultimediaDTO> getAllFromEvent(Integer eventId){
        List<Multimedia> multimedia = multimediaRepository.findByEventId(eventId);
        return multimediaMapper.multimediaToMultimediaDto(multimedia);
    }

    public Multimedia getMultimediaById(Integer id) throws NotFoundException {
        return multimediaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro un recurso multimedia"));
    }

    public void delete(Integer id) throws NotFoundException {
        Multimedia multimedia = this.getMultimediaById(id);
        multimediaRepository.delete(multimedia);
    }

}
