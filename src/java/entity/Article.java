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
 * Сущьность статьи
 * @author Lomovskoy
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, name = "caption")
    private String caption;

    @Column(columnDefinition = "TEXT", name = "content")
    private String content;

    @Column(name = "publicdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicdate;

    @OneToOne()
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "active")
    private Boolean active;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "ARTICLE_ID")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<User> likes;

    /**
     * Конструктор по умолчению
     */
    public Article() {
    }

    /**
     * Конструктор класса
     * @param caption String
     * @param content String
     * @param publicdate Date
     * @param author User
     * @param active Boolean
     * @param comments List Comment
     * @param likes List User
     */
    public Article(String caption, String content, Date publicdate, User author, 
            Boolean active, List<Comment> comments, List<User> likes) {
        this.caption = caption;
        this.content = content;
        this.publicdate = publicdate;
        this.author = author;
        this.active = active;
        this.comments = comments;
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
     * Получить заголовок
     * @return String
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Установить заголовок
     * @param caption String
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Получить содержание
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Установить содержание
     * @param content String
     */
    public void setContent(String content) {
        this.content = content;
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
     * Получить активной или нет
     * @return Boolean
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Установить активной или нет
     * @param active Boolean
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Получить комментарий
     * @return List Comment
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Установить комментарий
     * @param comments List Comment
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.caption);
        hash = 89 * hash + Objects.hashCode(this.content);
        hash = 89 * hash + Objects.hashCode(this.publicdate);
        hash = 89 * hash + Objects.hashCode(this.author);
        hash = 89 * hash + Objects.hashCode(this.active);
        hash = 89 * hash + Objects.hashCode(this.comments);
        hash = 89 * hash + Objects.hashCode(this.likes);
        return hash;
    }

    /**
     * Метод сравнения объектов
     * @param obj
     * @return
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
        final Article other = (Article) obj;
        if (!Objects.equals(this.caption, other.caption)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.publicdate, other.publicdate)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.likes, other.likes)) {
            return false;
        }
        return true;
    }

    /**
     * Метод выводящий в строку все данные
     * @return
     */
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", caption=" + caption + ", content=" + content + ", publicdate=" + publicdate + ", author=" + author + ", active=" + active + ", comments=" + comments + ", likes=" + likes + '}';
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
