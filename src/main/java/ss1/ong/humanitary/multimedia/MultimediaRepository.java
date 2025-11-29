package ss1.ong.humanitary.multimedia;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {
    public List<Multimedia> findByEventId(Integer eventId);
}
