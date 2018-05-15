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
    
    public Comment() {
    }

    public Comment(User author, String comment, Date publicdate, List<User> likes) {
        this.author = author;
        this.comment = comment;
        this.publicdate = publicdate;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPublicdate() {
        return publicdate;
    }

    public void setPublicdate(Date publicdate) {
        this.publicdate = publicdate;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

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
     * @param user
     * @return boolean
     */
    public boolean LikedByUser(User user) {
        return likes.contains(user);
    }

     /**
      * Добавить лайк
      * @param user 
      */
    public void LikeAdd(User user) {
        likes.add(user);
    }

    /**
     * Удалить лайк
     * @param user 
     */
    public void LikeDelete(User user) {
        likes.remove(user);
    }
}
