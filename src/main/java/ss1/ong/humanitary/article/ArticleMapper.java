package ss1.ong.humanitary.article;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ss1.ong.humanitary.article.dto.request.CreateArticleDTO;
import ss1.ong.humanitary.article.dto.response.ArticleDTO;
import ss1.ong.humanitary.article.dto.response.SimpleArticleDTO;

import java.util.List;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ArticleMapper {
    public Article createArticleDtoToArticle(CreateArticleDTO createArticleDTO);
    public ArticleDTO articleToArticleDto(Article article);
    public SimpleArticleDTO articleToSimpleArticleDto(Article article);
    public List<SimpleArticleDTO> articleToSimpleArticleDto(List<Article> articleList);
}
