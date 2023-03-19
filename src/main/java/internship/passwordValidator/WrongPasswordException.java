package internship.passwordValidator;

public class WrongPasswordException extends Exception {
    WrongPasswordException(){
    }
    WrongPasswordException(String message){
        super(message);
    }
}
