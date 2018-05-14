package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-14T13:58:59")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, User> author;
    public static volatile SingularAttribute<Comment, String> comment;
    public static volatile SingularAttribute<Comment, Long> id;
    public static volatile SingularAttribute<Comment, Date> publicdate;

}