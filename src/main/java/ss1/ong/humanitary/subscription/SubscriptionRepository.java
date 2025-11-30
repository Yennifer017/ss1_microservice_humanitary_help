package ss1.ong.humanitary.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    public List<Subscription> findByAppUserId(Integer id);
    public List<Subscription> findByEventId(Integer id);

    public Optional<Subscription> findByAppUserIdAndEventId(Integer appUserId, Integer eventId);
}
