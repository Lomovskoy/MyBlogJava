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
        System.out.print(articleFirst);
        System.out.print(articleLast);
        try{
            List<Article> article = (List<Article>) 
                    em.createQuery("SELECT u FROM Article u ORDER BY u.id DESC")
                    .setFirstResult(articleFirst)
                    .setMaxResults(articleLast)
                    .getResultList();
            return article;
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
