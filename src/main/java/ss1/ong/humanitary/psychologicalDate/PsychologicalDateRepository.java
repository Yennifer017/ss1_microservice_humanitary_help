package ss1.ong.humanitary.psychologicalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PsychologicalDateRepository extends JpaRepository<PsychologicalDate, Integer> {
    public List<PsychologicalDate> findByPsychoHelpId(Integer id);

}
