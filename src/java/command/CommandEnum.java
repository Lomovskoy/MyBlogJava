package command;

import command.update.UpdateUserCommand;
import command.add.AddArticleCommand;
import command.add.AddCommentCommand;
import command.add.AddFormCommand;
import command.add.UpdateFileFormCommand;
import command.admin.AdminCommand;
import command.admin.UserManagementCommand;
import command.dell.DellArticleCommand;
import command.dell.DellCommentCommand;
import command.dell.DellImageCommand;
import command.dell.DellUserCommand;
import command.reg_login.LoginCommand;
import command.reg_login.OutCommand;
import command.reg_login.RegFormCommand;
import command.reg_login.RegistrationCommand;
import command.update.ChangeInformationCommand;
import command.update.ChangeInformationFormCommand;
import command.update.UpdateArticleCommand;
import command.update.UpdateCommentCommand;
import command.update.UpdateFormArticleCommand;

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
    DELLIMAGE{{this.command = new DellImageCommand();}},
    CHANGEINFORMATIONFORM{{this.command = new ChangeInformationFormCommand();}},
    CHANGEINFORMATION{{this.command = new ChangeInformationCommand();}},
    VIEWARTICLES{{this.command = new ShowArticlesCommand();}};
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