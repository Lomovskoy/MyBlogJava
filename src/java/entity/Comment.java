package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Сущьность комментарии
 * @author Lomovskoy
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne()
    @JoinColumn(name = "author")
    private User author;
    
    @Column(length = 2000, name = "comment")
    private String comment;

    @Column(name = "publicdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicdate;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> likes;
    
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Article article;*/

    /**
     * Конструктор по умолчению
     */
    public Comment() {
    }

    /**
     * Конструктор класса
     * @param author
     * @param comment
     * @param publicdate
     * @param likes
     */
    public Comment(User author, String comment, Date publicdate, List<User> likes) {
        this.author = author;
        this.comment = comment;
        this.publicdate = publicdate;
        this.likes = likes;
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
     * Получить автора
     * @return User
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Установить автора
     * @param author User
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Получить комментарий
     * @return String
     */
    public String getComment() {
        return comment;
    }

    /**
     * Установить комментарий
     * @param comment String
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Получить дату публикации
     * @return Date
     */
    public Date getPublicdate() {
        return publicdate;
    }

    /**
     * Установить дату публикации
     * @param publicdate Date
     */
    public void setPublicdate(Date publicdate) {
        this.publicdate = publicdate;
    }

    /**
     * Получить лайк
     * @return List User
     */
    public List<User> getLikes() {
        return likes;
    }

    /**
     * Установить лайк
     * @param likes List User
     */
    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    /**
     * Метод отдающий хешь код обьекта
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.comment);
        hash = 97 * hash + Objects.hashCode(this.publicdate);
        hash = 97 * hash + Objects.hashCode(this.likes);
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
        final Comment other = (Comment) obj;
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publicdate, other.publicdate)) {
            return false;
        }
        if (!Objects.equals(this.likes, other.likes)) {
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
        return "Comment{" + "id=" + id + ", author=" + author + ", comment=" + comment + ", publicdate=" + publicdate + ", likes=" + likes + '}';
    }
    /**
     * Количество лайков
     * @return int
     */
    public int LikesCount() {
        return likes.size();
    }

    /**
     * Ставил ли лийк этот пользователь
     * @param user User
     * @return boolean
     */
    public boolean LikedByUser(User user) {
        return likes.contains(user);
    }

     /**
      * Добавить лайк
      * @param user User
      */
    public void LikeAdd(User user) {
        likes.add(user);
    }

    /**
     * Удалить лайк
     * @param user User
     */
    public void LikeDelete(User user) {
        likes.remove(user);
    }
}
