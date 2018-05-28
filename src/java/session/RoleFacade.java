package session;

import entity.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Фасад отвечающий за работу с ролями
 * @author Lomovskoy
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> {

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

    /**
     * Конструктор по умолчению
     */
    public RoleFacade() {
        super(Role.class);
    }

}
