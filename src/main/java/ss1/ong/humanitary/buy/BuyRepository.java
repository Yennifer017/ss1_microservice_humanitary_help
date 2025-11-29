package ss1.ong.humanitary.buy;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Integer> {
    public List<Buy> findByAppUserId(Integer id);
    public List<Buy> findByHelperId(Integer id);
}
