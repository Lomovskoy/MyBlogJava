package entity;

import entity.Comment;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-30T14:22:01")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile ListAttribute<Article, Comment> comments;
    public static volatile SingularAttribute<Article, User> author;
    public static volatile SingularAttribute<Article, String> caption;
    public static volatile SingularAttribute<Article, Boolean> active;
    public static volatile SingularAttribute<Article, Long> id;
    public static volatile SingularAttribute<Article, Date> publicdate;
    public static volatile SingularAttribute<Article, String> content;
    public static volatile ListAttribute<Article, User> likes;

}