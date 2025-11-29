package ss1.ong.humanitary.article.dto.response;

import lombok.Value;
import ss1.ong.humanitary.event.dto.response.EventDTO;

@Value
public class ArticleDTO {
    Integer id;
    String title;
    String content;
    EventDTO event;
}
