package command;
/**
 * 
 * @author pupil
 * enum класс предназначенный для перечисления,
 * содержит поля класса как массив из ключ - значение
 * и метод возвращающий значение по ключу
 */
public enum CommandEnum {
    
    //Массив с ключами и згачениями
    PRODUCT{{this.command = new EmptyCommand();}},
    INDEX{{this.command = new EmptyCommand();}},
    ADMINPANEL{{this.command = new AdminCommand();}},
    LOGIN{{this.command = new LoginCommand();}},
    CHECKOUT{{this.command = new OutCommand();}},
    ADDPAGE{{this.command = new AddFormCommand();}},
    ADDARTICLE{{this.command = new AddArticleCommand();}},
    DELLARTICLE{{this.command = new DellArticleCommand();}},
    UPDATEARTICLEFORM{{this.command = new UpdateFormArticleCommand();}},
    UPDATEARTICLE{{this.command = new UpdateArticleCommand();}},
    SHOWONEARTICLE{{this.command = new ShowOneArticleCommand();}},   
    REGFORM{{this.command = new RegFormCommand();}}, 
    REGISTRATION{{this.command = new RegistrationCommand();}},
    USERMANAGEMENT{{this.command = new UserManagementCommand();}},
    DELLUSER{{this.command = new DellUserCommand();}},
    UPDATEUSER{{this.command = new UpdateUserCommand();}},
    ADDCOMMENT{{this.command = new AddCommentCommand();}},
    DELLCOMMENT{{this.command = new DellCommentCommand();}},
    UPDATECOMMENT{{this.command = new UpdateCommentCommand();}},
    ADDFILE{{this.command = new UpdateFileFormCommand();}},
    DELLIMAGE{{this.command = new DellImageCommand();}};
    ActionCommand command;
    
    /**
     * @return 
     * Возврашает класс назденный по значению,
     * знавчение берётся из ссылки значения command=ключ
     */
    public ActionCommand getCurrentCommand(){
        
        return this.command;
    }
}