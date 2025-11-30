package ss1.ong.humanitary.profession;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    public List<Profession> findByForPsycho(Boolean forPsycho);
}
