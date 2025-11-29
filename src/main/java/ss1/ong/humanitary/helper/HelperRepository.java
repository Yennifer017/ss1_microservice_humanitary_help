package ss1.ong.humanitary.helper;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelperRepository extends JpaRepository<Helper, Integer> {
    public List<Helper> findByCatastropheId(Integer id);
}
