package session;

import entity.Role;
import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lomov
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "MyBlogLomovskoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findByLogin(String login){

        try{
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.login=:login")
                    .setParameter("login", login)
                    .getSingleResult();
            return user;

        }catch(Exception e){
            return null;
        }
    }
     public User findByEmail(String email){

        try{
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.email=:email")
                    .setParameter("email", email)
                    .getSingleResult();
            return user;

        }catch(Exception e){
            return null;
        }
    }

    public void create(Role role1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
