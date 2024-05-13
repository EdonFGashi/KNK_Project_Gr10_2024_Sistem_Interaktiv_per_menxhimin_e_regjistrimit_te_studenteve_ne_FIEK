package service.CustomExceptions;

public class InvalidEmail extends Exception{
    public InvalidEmail(String message){
        super(message);

    }
}
