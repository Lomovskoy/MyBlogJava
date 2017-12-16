/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Article;
import entity.Comment;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> {

    @PersistenceContext(unitName = "MyBlogLomovskoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentFacade() {
        super(Comment.class);
    }

    public List<Comment> findById(Article articleId) {
        try{
            List<Comment> comments = (List<Comment>) em.createQuery("SELECT u FROM Comment u WHERE u.article=:article")
                    .setParameter("article", articleId)
                    .getResultList();
            return comments;

        }catch(Exception e){
            return null;
        }
    }
    
}
