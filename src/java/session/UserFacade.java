package session;

import entity.Role;
import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Фасад отвечающий за работу с пользователями
 * @author lomov
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

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

    public UserFacade() {
        super(User.class);
    }
    
    /**
     * Получить пользователя по логнину
     * @param login
     * @return User
     */
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
    
    /**
     * Получить пользователя по почте
     * @param email
     * @return User
     */
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
    
}
