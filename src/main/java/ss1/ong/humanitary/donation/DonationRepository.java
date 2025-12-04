package ss1.ong.humanitary.donation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    public List<Donation> findByEventId(Integer id);
    public List<Donation> findByAppUserId(Integer id);
}
