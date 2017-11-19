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
    ADDARTICLE{{this.command = new AddArticleCommand();}};
    ActionCommand command;
    
    /**
     * 
     * @return 
     * Возврашает класс назденный по значению,
     * знавчение берётся из ссылки значения command=ключ
     */
    public ActionCommand getCurrentCommand(){
        
        return this.command;
    }
}