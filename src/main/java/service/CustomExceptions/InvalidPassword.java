package service.CustomExceptions;

public class InvalidPassword extends Exception{
    public InvalidPassword(String message){
        super(message);

    }
}
