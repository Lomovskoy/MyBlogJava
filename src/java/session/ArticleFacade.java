package session;

import entity.Article;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> {

    @PersistenceContext(unitName = "MyBlogLomovskoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    public List<Article> findPagination(Integer articleFirst, Integer articleLast) {
        try {
            List<Article> article = (List<Article>) em.createQuery("SELECT u FROM Article u ORDER BY u.id DESC")
                    .setFirstResult(articleFirst)
                    .setMaxResults(articleLast)
                    .getResultList();
            return article;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<Article> searchPagination(String search) {
        try {
            List<Article> article = (List<Article>) em.createQuery("SELECT u FROM Article u WHERE u.content LIKE :search ORDER BY u.id DESC")
                    .setParameter("search", "%" + search + "%")
                    .getResultList();
            return article;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
