package ss1.ong.humanitary.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss1.ong.humanitary.article.dto.request.CreateArticleDTO;
import ss1.ong.humanitary.article.dto.response.SimpleArticleDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;
import ss1.ong.humanitary.event.Event;
import ss1.ong.humanitary.event.EventService;

import java.util.List;
/**
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-08-28
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    private final EventService eventService;

    public Article createArticle(CreateArticleDTO createArticleDTO) throws NotFoundException {
        Event event = eventService.getEventById(createArticleDTO.getEventId());
        Article article = articleMapper.createArticleDtoToArticle(createArticleDTO);
        article.setEvent(event);
        return articleRepository.save(article);
    }

    public List<SimpleArticleDTO> getAllArticles(){
        List<Article> articleList = articleRepository.findAll();
        return articleMapper.articleToSimpleArticleDto(articleList);
    }

    public List<SimpleArticleDTO> getALlArticles(Integer eventId){
        List<Article> articleList = articleRepository.findByEventId(eventId);
        return articleMapper.articleToSimpleArticleDto(articleList);
    }

    public Article getArticleById(Integer id) throws NotFoundException {
        return this.articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro un articulo"));
    }

    public void deleteArticle(Integer id) throws NotFoundException {
        Article article = this.getArticleById(id);
        articleRepository.delete(article);
    }

}
