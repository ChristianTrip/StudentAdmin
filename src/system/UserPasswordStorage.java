package system;

import users.User;
import java.util.HashMap;
import java.util.Map;

public class UserPasswordStorage {

    // INSTANCES VARIABLES==============================================================================

    private FileHandling fileHandling = new FileHandling();

    // PUBLIC METHODS==============================================================================

    public boolean validatePassword(User user, String password){

        HashMap<String, String> currentPasswords = loadIDs();
        String userId = user.getID();
        String encryptedPassword = encrypt(user,password);

        for (Map.Entry<String, String> entry : currentPasswords.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (userId.equals(key) && encryptedPassword.equals(value)){
                return true;
            }
        }
        return false;
    }

    public void logUser(User user, String password){

        fileHandling.addUserToFile(user.getID(), encrypt(user, password));
    }

    public boolean validPassword(String password){

        if (hasUpperCase(password) && hasLowerCase(password)
            && containsNumbers(password) && password.length() >= 8){
            return true;
        }

        System.out.println("The password is not valid");
        System.out.println("It has to contain uppercase & lowercase letters");
        System.out.println("and numbers");
        return false;
    }

    // PRIVATE METHODS==============================================================================

    private HashMap<String, String> loadIDs(){

        return new HashMap<>(fileHandling.getLOGIN_FILE());
    }

    private boolean hasUpperCase(String password){
        for(int i = 0; i<password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))){
                return true;
            }
        }
        return false;

    }

    private boolean hasLowerCase(String password){
        for(int i  = 0; i<password.length(); i++){
            if(Character.isLowerCase(password.charAt(i))){
                return true;
            }
        }
        return false;
    }

    private boolean containsNumbers(String password){

        int counter = 0;

        for(int i = 0; i<password.length(); i++){
            if(Character.isDigit(password.charAt(i))){
                counter ++;
            }
        }
        if (counter >= 2){
            return true;
        }
        return false;
    }

    private String encrypt(User user, String password){

        String toEncrypt = password;
        char[] chars = toEncrypt.toCharArray();
        int shift = user.getKey();
        String encrypted = "";

        for (int i = 0; i < chars.length; i++) {

            chars[i] += shift;
            encrypted += Character.toString(chars[i]);
            shift --;
        }
        return encrypted;
    }

    private String decrypt(User user, String encrypted){
        String password = encrypted;
        char[] chars = password.toCharArray();
        int shift = user.getKey();
        String decrypted = "";

        for (int i = 0; i < password.length(); i++) {
            chars[i] -= shift;
            decrypted += Character.toString(chars[i]);
            shift --;
        }

        return decrypted;
    }

}
