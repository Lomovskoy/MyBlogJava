package session;

import entity.Article;
import entity.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Фасад для работы с комментариями
 * @author Lomovskoy
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {

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

    public CommentFacade() {
        super(Comment.class);
    }
    
}
