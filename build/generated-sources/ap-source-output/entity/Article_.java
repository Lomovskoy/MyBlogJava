package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-18T14:58:38")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, User> author;
    public static volatile SingularAttribute<Article, String> caption;
    public static volatile SingularAttribute<Article, Long> id;
    public static volatile SingularAttribute<Article, Date> publicdate;
    public static volatile SingularAttribute<Article, String> content;

}