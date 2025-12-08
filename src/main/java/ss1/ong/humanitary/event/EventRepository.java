package ss1.ong.humanitary.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    public List<Event> findByCatastropheId(Integer id);
}
