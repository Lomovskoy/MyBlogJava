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
import command.update.UpdateCommentFormCommand;
import command.update.UpdateFormArticleCommand;

/**
 * Класс содержит константы Command и 
 * возвращает обьекты создаваемые вызванной командой
 * @author Lomovskoy
 */
public enum CommandEnum {
    
    //Массив с ключами и значениями

    /**
     * Возвращает комманд индексной страницы
     */
    PRODUCT{{this.command = new EmptyCommand();}},

    /**
     * Возвращает комманд индексной страницы
     */
    INDEX{{this.command = new EmptyCommand();}},

    /**
     * Возвращает комманд страницу админа
     */
    ADMINPANEL{{this.command = new AdminCommand();}},

    /**
     * Возвращает комманд страницу логину
     */
    LOGIN{{this.command = new LoginCommand();}},

    /**
     * Возвращает комманд сыхода из сессии
     */
    CHECKOUT{{this.command = new OutCommand();}},

    /**
     * Возвращает комманд страницу добавения 
     */
    ADDPAGE{{this.command = new AddFormCommand();}},

    /**
     * Возвращает комманд добваление стрницы
     */
    ADDARTICLE{{this.command = new AddArticleCommand();}},

    /**
     * Возвращает комманд удаления статьи
     */
    DELLARTICLE{{this.command = new DellArticleCommand();}},

    /**
     * Возвращает комманд страницу изменения статьи
     */
    UPDATEARTICLEFORM{{this.command = new UpdateFormArticleCommand();}},

    /**
     * Возвращает комманд изменения статьи
     */
    UPDATEARTICLE{{this.command = new UpdateArticleCommand();}},

    /**
     * Возвращает комманд показа одлной статьи
     */
    SHOWONEARTICLE{{this.command = new ShowOneArticleCommand();}},   

    /**
     * Возвращает комманд страницы регистрации форма
     */
    REGFORM{{this.command = new RegFormCommand();}}, 

    /**
     * Возвращает комманд рагистрации
     */
    REGISTRATION{{this.command = new RegistrationCommand();}},

    /**
     * Возвращает комманд управление пользователем
     */
    USERMANAGEMENT{{this.command = new UserManagementCommand();}},

    /**
     * Возвращает комманд удаления пользователя
     */
    DELLUSER{{this.command = new DellUserCommand();}},

    /**
     * Возвращает комманд изменения пользователя
     */
    UPDATEUSER{{this.command = new UpdateUserCommand();}},

    /**
     * Возвращает комманд добавления комменравия
     */
    ADDCOMMENT{{this.command = new AddCommentCommand();}},

    /**
     * Возвращает комманд удаления комментария
     */
    DELLCOMMENT{{this.command = new DellCommentCommand();}},

    /**
     * Возвращает комманд страница изменения коссментария
     */
    UPDATECOMMENTFORM{{this.command = new UpdateCommentFormCommand();}},

    /**
     * Возвращает комманд изменения комментария
     */
    UPDATECOMMENT{{this.command = new UpdateCommentCommand();}},

    /**
     * Возвращает комманд страницы изменения файла
     */
    ADDFILE{{this.command = new UpdateFileFormCommand();}},

    /**
     * Возвращает комманд удаления картинки
     */
    DELLIMAGE{{this.command = new DellImageCommand();}},

    /**
     * Возвращает комманд страница изменения информации о пользователе
     */
    CHANGEINFORMATIONFORM{{this.command = new ChangeInformationFormCommand();}},

    /**
     * Возвращает комманд изменения информации о пользователе
     */
    CHANGEINFORMATION{{this.command = new ChangeInformationCommand();}},

    /**
     * Возвращает комманд показа статьи
     */
    VIEWARTICLES{{this.command = new ShowArticlesCommand();}},

    /**
     * Возвращает комманд поиска
     */
    SEARCH{{this.command = new SearchCommand();}};
    ActionCommand command;
    
    /**
     * Возврашает обьект класса созданный созданный командой command = ключ
     * @return Object
     */
    public ActionCommand getCurrentCommand(){
        
        return this.command;
    }
}