package ss1.ong.humanitary.psychoHelp;

import org.springframework.data.jpa.repository.JpaRepository;
import ss1.ong.humanitary.example.Model;
import ss1.ong.humanitary.psychoHelp.enums.PsychoHelpStatusEnum;

import java.util.List;

public interface PsychoHelpRepository extends JpaRepository<PsychoHelp, Integer> {
    public List<PsychoHelp> findByPatientId(Integer id);
    public List<PsychoHelp> findByCurrentPsychoId(Integer id);
    public List<PsychoHelp> findByStatus(PsychoHelpStatusEnum status);

}
