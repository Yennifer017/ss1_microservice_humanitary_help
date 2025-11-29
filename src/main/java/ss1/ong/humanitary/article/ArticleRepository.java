package ss1.ong.humanitary.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    public List<Article> findByEventId(Integer eventId);
}
