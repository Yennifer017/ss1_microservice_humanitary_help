package ss1.ong.humanitary.auth.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public Boolean existsByUsername(String username);
    public Optional<AppUser> findUserByUsername(String username);

}
