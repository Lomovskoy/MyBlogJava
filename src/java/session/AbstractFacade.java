package session;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Абстракный класс реализующий все основные методы работы с б.д.
 * @author Lomovskoy
 * @param <T> Class получает объект статьи или пользователя 
 * в зависимости от реализауции.
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    /**
     * Конструктор принимающицй класс постоянства
     * @param entityClass Class
     */
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Полуть Эентити менеджера
     * @return EntityManager
     */
    protected abstract EntityManager getEntityManager();

    /**
     * Создать запись
     * @param entity Object
     */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    /**
     * Изменить запись
     * @param entity Object
     */
    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    /**
     * Удалить запись
     * @param entity Object
     */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    /**
     * Получить по идентификатору
     * @param id Object
     * @return Object
     */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Поулчить всё
     * @return List Object
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Получить записи в диакпазоне
     * @param range int[]
     * @return List Object
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * Количество записей в базе
     * @return int
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
