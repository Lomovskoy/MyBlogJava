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
    PRODUCT{{this.command = new EmptyCommand();}};
    
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