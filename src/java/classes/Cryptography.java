package classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Это класс с 3мя методами:<br>
 * 1 - Зашифровает пароль с полученной солью<br>
 * 2 - Отдаёт случайную соль<br> 
 * 3 - Сравнивает пароли
 * @author Lomovskoy
 */
public class Cryptography {
    

    /**
     * Зашифровает пароль с полученной солью
     * @param password
     * @param salts
     * @return String
     */
    public static String setEncriptPasssword(String password, String salts){
        try {
            password = salts + password;
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            m.update(password.getBytes(),0,password.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            return "Error Password";
        }
    }
    
    /**
     * Отдаёт случайную соль
     * @return String
     */
    public static String getSalts(){
        
        try {
            Date time = new Date();
            String s = time.toString();
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(),0,s.length());
            return new BigInteger(1,m.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cryptography.class.getName()).log(Level.SEVERE, null, ex);
            return "Error Salts";
        }
    }
    
    /**
     * Сравнивает пароли
     * @param passwordUser
     * @param passwordDb
     * @param salts
     * @return Boolean
     */
    public static Boolean comparePasssword(String passwordUser, String passwordDb, String salts){
        
        String passwordHash = setEncriptPasssword(passwordUser, salts);
        
        return passwordHash.equals(passwordDb);
    }

}
