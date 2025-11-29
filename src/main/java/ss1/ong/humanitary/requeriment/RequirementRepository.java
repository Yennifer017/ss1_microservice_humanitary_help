package ss1.ong.humanitary.requeriment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
    public List<Requirement> findByEventId(Integer eventId);
    public boolean existsByEventIdAndDonationUtilId(Integer eventId, Integer donationUtilId);
}
