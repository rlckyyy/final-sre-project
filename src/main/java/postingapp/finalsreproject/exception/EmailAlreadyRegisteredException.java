package postingapp.finalsreproject.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
