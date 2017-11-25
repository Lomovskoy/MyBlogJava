package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author pupil
 * Сцщность статьи в блоге
 */
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=256)
    @Column(name = "caption")
    private String caption;
    
    @Column(length = 20000, name = "content")
    private String content;
    
    @Column(name = "publicdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicdate;
    
    @OneToOne()
    private User author;

    public Article() {
    }
    
    public Article(String caption, String content, Date publicdate, User author) {
        this.caption = caption;
        this.content = content;
        this.publicdate = publicdate;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicdate() {
        return publicdate;
    }

    public void setPublicdate(Date publicdate) {
        this.publicdate = publicdate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.caption);
        hash = 23 * hash + Objects.hashCode(this.content);
        hash = 23 * hash + Objects.hashCode(this.publicdate);
        hash = 23 * hash + Objects.hashCode(this.author);
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
        return true;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", caption=" + caption + ", content=" + content + ", publicdate=" + publicdate + ", author=" + author.toString() + '}';
    }

   
    
    
}
