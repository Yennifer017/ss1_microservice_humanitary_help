package ss1.ong.humanitary.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ss1.ong.humanitary.article.dto.request.CreateArticleDTO;
import ss1.ong.humanitary.article.dto.response.ArticleDTO;
import ss1.ong.humanitary.article.dto.response.SimpleArticleDTO;
import ss1.ong.humanitary.common.exceptions.NotFoundException;

import java.util.List;

/**
 * Controlador REST para la gestión de usuarios
 *
 * @author Yennifer de Leon
 * @version 1.0
 * @since 2025-28-08
 */
@RestController
@RequestMapping("/api/hh/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleMapper articleMapper;
    private final ArticleService articleService;

    /**
     * Crear un articulo
     */
    @Operation(summary = "Crear un articulo",
            description = "Crear un articulo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exito"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ArticleDTO create(@RequestBody @Valid CreateArticleDTO createArticleDTO) throws NotFoundException {
        return articleMapper.articleToArticleDto(articleService.createArticle(createArticleDTO));
    }

    /**
     * Obtener todos los articulos
     **/
    @Operation(summary = "Obtener todos los articulos",
            description = "Obtener todos los articulos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleArticleDTO> getAll() {
        return articleService.getAllArticles();
    }

    /**
     * Obtener todos los articulos de un evento
     **/
    @Operation(summary = "Obtener todos los articulos de un evento",
            description = "Obtener todos los articulos de un evento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/byEvent/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SimpleArticleDTO> getAllByEvent(@PathVariable Integer eventId) {
        return articleService.getALlArticles(eventId);
    }

    /**
     * Obtener un articulo
     **/
    @Operation(summary = "Obtener un articulo",
            description = "Obtener un articulo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO get(@PathVariable Integer id) throws NotFoundException {
        return articleMapper.articleToArticleDto(articleService.getArticleById(id));
    }


    /**
     * Elimina un articulo
     **/
    @Operation(summary = "Elimina un articulo",
            description = "Elimina un articulo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Exitoso"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void example(@PathVariable Integer id) throws NotFoundException {
        articleService.deleteArticle(id);
    }
}
