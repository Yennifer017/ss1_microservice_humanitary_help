package ss1.ong.humanitary.donationUtil;

import org.springframework.data.jpa.repository.JpaRepository;
import ss1.ong.humanitary.donationUtil.enums.DonationUtilTypeEnum;

import java.util.List;

public interface DonationUtilRepository extends JpaRepository<DonationUtil, Integer> {
    public List<DonationUtil> findByType(DonationUtilTypeEnum type);
}
