/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pupil
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {

    @PersistenceContext(unitName = "MyBlogLomovskoyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }
    
    public Admin findByLogin(String login){

        try{
            Admin user = (Admin) em.createQuery("SELECT u FROM Admin u WHERE u.login=:login")
                    .setParameter("login", login)
                    .getSingleResult();
            return user;

        }catch(Exception e){
            return null;
        }
    }
}
