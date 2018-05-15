package session;

import controller.FileUploadServlet;
import entity.Article;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Фасад для работы с статьями
 * @author Lomovskoy
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> {
    private final static Logger LOGGER
            = Logger.getLogger(FileUploadServlet.class.getCanonicalName());

    @PersistenceContext(unitName = "MyBlogLomovskoyPU")
    private EntityManager em;

    /**
     * Создание менеджера для работы с б.д.
     * @return EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    /**
     * Метод получение статей с цчётом пагинации
     * @param articleFirst
     * @param articleLast
     * @return List<Article>
     */
    public List<Article> findPagination(Integer articleFirst, Integer articleLast) {
        try {
            List<Article> article = (List<Article>) em.createQuery("SELECT u FROM Article u ORDER BY u.id DESC")
                    .setFirstResult(articleFirst)
                    .setMaxResults(articleLast)
                    .getResultList();
            return article;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
            return null;
        }
    }

    /**
     * Метод получения статей по поиску
     * @param search
     * @return List<Article>
     */
    public List<Article> searchPagination(String search) {
        try {
            List<Article> article = (List<Article>) em.createQuery("SELECT u FROM Article u WHERE u.content LIKE :search ORDER BY u.id DESC")
                    .setParameter("search", "%" + search + "%")
                    .getResultList();
            return article;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString());
            return null;
        }
    }
}
