package ss1.ong.humanitary.catastrophe;

import org.springframework.data.jpa.repository.JpaRepository;
import ss1.ong.humanitary.example.Model;

public interface CatastropheRepository extends JpaRepository<Catastrophe, Integer> {
}
