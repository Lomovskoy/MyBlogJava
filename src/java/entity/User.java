package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Сущьность пользователь
 * @author Lomovskoy
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=3,max=32)
    @Column(name = "login", unique = true)
    private String login;
    
    @Size(min=6,max=64)
    @Column(name = "password")
    private String password;
    
    @Size(min=3,max=32)
    @Column(name = "salts")
    private String salts;

    @Column(name = "active")
    private Boolean active;
    
    @OneToOne
    @JoinColumn(name = "role")
    private Role role;
    
    @Size(min=6,max=255)
    @Column(name = "email", unique = true)
    private String email;
    
    @Size(min=1,max=255)
    @Column(name = "image")
    private String image;
    
    /**
     * Конструктор по умолчению
     */
    public User() {
    }

    /**
     * Конструктор класса
     * @param login
     * @param password
     * @param salts
     * @param active
     * @param role
     * @param email
     * @param image
     */
    public User(String login, String password, String salts, Boolean active, Role role, String email, String image) {
        this.login = login;
        this.password = password;
        this.salts = salts;
        this.active = active;
        this.role = role;
        this.email = email;
        this.image = image;
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
     * Получить логин
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * Установить логин
     * @param login String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Получить пароль
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Установить пароль
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Получить соль
     * @return String
     */
    public String getSalts() {
        return salts;
    }

    /**
     * Установить соль
     * @param salts String
     */
    public void setSalts(String salts) {
        this.salts = salts;
    }

    /**
     * Получить статус
     * @return Boolean
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Установить статус
     * @param active Boolean
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Получить роль
     * @return Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Установить роль
     * @param role Role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Получить почту
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установить почту
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получить картинку
     * @return String
     */
    public String getImage() {
        return image;
    }

    /**
     * Установить картинку
     * @param image String
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Метод отдающий хешь код обьекта
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.login);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.salts);
        hash = 29 * hash + Objects.hashCode(this.active);
        hash = 29 * hash + Objects.hashCode(this.role);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.image);
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
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.salts, other.salts)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
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
        return "User{" + "id=" + id + ", login=" + login + ", password=" + password + ", salts=" + salts + ", active=" + active + ", role=" + role + ", email=" + email + ", image=" + image + '}';
    }

   

}
