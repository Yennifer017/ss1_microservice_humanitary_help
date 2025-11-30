package ss1.ong.humanitary.userProfession;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserProfessionRepository extends JpaRepository<UserProfession, Integer> {
    public List<UserProfession> findByAppUserId(Integer id);
    public List<UserProfession> findByProfessionId(Integer id);

    public Optional<UserProfession> findByAppUserIdAndProfessionId(Integer appUserId, Integer professionId);
}
