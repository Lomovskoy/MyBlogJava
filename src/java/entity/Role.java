package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Сущьность роли
 * @author Lomovskoy
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max=10)
    @Column(name = "roles")
    private String roles;

    /**
     * Конструктор по умолчению
     */
    public Role() {
    }

    /**
     * Конструктор класса
     * @param roles String
     */
    public Role(String roles) {
        this.roles = roles;
    }

    /**
     * Получить идентифиукатор
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить идентифиукатор
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить роль
     * @return String
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Установить роль
     * @param roles String
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Метод отдающий хешь код обьекта
     * @return int 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    /**
     * Метод сравнения объектов
     * @param obj
     * @return Object
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Метод выводящий в строку все данные
     * @return String
     */
    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", roles=" + roles + '}';
    }
    
    
}
