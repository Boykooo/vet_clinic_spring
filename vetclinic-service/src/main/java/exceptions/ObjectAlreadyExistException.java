package exceptions;

public class ObjectAlreadyExistException extends Exception {
    public ObjectAlreadyExistException() {
        super("Object already exists");
    }
}
